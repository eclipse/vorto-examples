import { delay, map, retry, catchError, retryWhen, mergeMap } from 'rxjs/operators';
import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'
import { of, EMPTY, Observable, throwError } from 'rxjs';


// export const apiBaseURL = 'http://localhost:8080/api/v1'
export const apiBaseURL = 'http://vorto-normalizer-dev.eu-central-1.elasticbeanstalk.com:8080/api/v1'


const username = 'admin'
const password = 'secret'

const DEFAULT_MAX_RETRIES = 5

const getErrorMessage = (maxRetry: number) =>
'Tried to load plugins for ${maxRetry} times without success.'


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

//try to reconnect on fail
export function delayedRetry(delayMs: number, maxRetry = DEFAULT_MAX_RETRIES){
let retries = maxRetry

return (src: Observable<any>) =>
src.pipe(retryWhen((errors: Observable<any>) => errors.pipe(
  delay(delayMs),
  mergeMap(error => retries --> 0 ? of(error) : throwError(getErrorMessage(maxRetry)))
)))
}

@Injectable({ providedIn: 'root' })
export class APIService {

  constructor(private http: HttpClient) { }

  public getPlugins() {
    return this.http
      .get(apiBaseURL + '/plugins', httpOptions)
      .pipe(  
        delayedRetry(1000, 3),
        catchError(error =>{
          console.error(error);
          return EMPTY
        }),
        map((res: any) => {
        return res
      }))
  }


  public getMappings() {
    return this.http
      .get(apiBaseURL + '/mappings', httpOptions)
      .pipe(  
        delayedRetry(1000, 3),
        catchError(error =>{
          console.error(error);
          return EMPTY
        }),
        map((res: any) => {
        return res
      }))
  }




}
