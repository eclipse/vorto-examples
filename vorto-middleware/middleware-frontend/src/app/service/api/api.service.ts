import { delay, map, catchError, retryWhen, mergeMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { of, EMPTY, Observable, throwError, BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';


export const API_BASE_URL = environment.apiBaseUrl
const API_PATH = '/api/v1'


const DEFAULT_MAX_RETRIES = 5


const getErrorMessage = (maxRetry: number) =>
  'Tried to load '+ maxRetry + ' times without success.'


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

const httpOptionsUser = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}


//try to reconnect on fail
export function delayedRetry(delayMs: number, maxRetry = DEFAULT_MAX_RETRIES) {
  let retries = maxRetry

  return (src: Observable<any>) =>
    src.pipe(retryWhen((errors: Observable<any>) => errors.pipe(
      delay(delayMs),
      mergeMap(error => retries-- > 0 ? of(error) : throwError(getErrorMessage(maxRetry)))
    )))
}

@Injectable({ providedIn: 'root' })
export class APIService {

  constructor(private http: HttpClient) { }


  private _pluginList = new BehaviorSubject<any[]>([])
  readonly pluginList = this._pluginList.asObservable()

  private _mappingsList = new BehaviorSubject<any[]>([])
  readonly mappingsList = this._mappingsList.asObservable()

  public getPlugins() {
    return this.http
      .get(API_BASE_URL + API_PATH + '/plugins', httpOptions)
      .pipe(
        delayedRetry(1000, 3),
        catchError(error => {
          console.error(error);
          return EMPTY
        }),
        map((res: any) => {
          this._pluginList.next(res)
        }))
  }


  public getMappings() {
    return this.http
      .get(API_BASE_URL + API_PATH + '/mappings', httpOptions)
      .pipe(
        delayedRetry(1000, 3),
        catchError(error => {
          console.error(error);
          return EMPTY
        }),
        map((res: any) => {
          this._mappingsList.next(res)
        }))
  }

  // install or uninstall mapping
  public updateMappingInstallState(modelId, install) {
    var actionSelector = install ? '/install' : '/uninstall'
    this.http
      .put(API_BASE_URL + API_PATH + '/mappings/' + modelId + actionSelector, httpOptions).subscribe(
        res => {
          console.log("Updating " + modelId);
        },
        catchError(error => {
          console.error(error);
          return EMPTY
        }))
  }


  


  public getUser() {
    return this.http
      .get(API_BASE_URL + API_PATH + '/context/user', httpOptions)
      .pipe(
        catchError(error => {
          switch (error.status) {
            case 401:
              //todo handle
              console.log("An error occured: Unauthorized: ", error);
              break;
            default:
                console.log("(Login State) Error: ", error);
              break;
          }
          return EMPTY
        }),
        map((res: any) => {
          console.log("received "+res)
          // this._mappingsList.next(res)
        }))
  }
}

