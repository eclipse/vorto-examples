import { delay, map, catchError, retryWhen, mergeMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { of, EMPTY, Observable, throwError, BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';

export const BASE_URL = environment.apiBaseUrl

const API_PATH = '/api/v1'


const DEFAULT_MAX_RETRIES = 5

export enum LoginState {
  AUTHORIZED = "AUTHORIZED",
  ERROR = "ERROR",
  UNAUTHORIZED = "UNAUTHORIZED"
}

export enum PollingState {
  POLLING = "POLLING",
  EMPTY = "EMPTY",
  ERROR = "ERROR",
  SUCCESS = "SUCCESS"
}

const getErrorMessage = (maxRetry: number) =>
  'Tried to load ' + maxRetry + ' times without success.'


const httpOptions = {
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

  private _installedMappingsList = new BehaviorSubject<any[]>([])
  readonly installedMappingsList = this._installedMappingsList.asObservable()

  private _discoverMappingsList = new BehaviorSubject<any[]>([])
  readonly discoverMappingsList = this._discoverMappingsList.asObservable()

  private _mappingPollingState = new BehaviorSubject<PollingState>(PollingState.EMPTY)
  readonly mappingPollingState = this._mappingPollingState.asObservable()

  private _loginState = new BehaviorSubject<LoginState>(LoginState.UNAUTHORIZED)
  readonly loginState = this._loginState.asObservable()

  private _authorizedUserId = new BehaviorSubject<String>("")
  readonly authorizedUserId = this._authorizedUserId.asObservable()

  public getPlugins() {
    return this.http
      .get(BASE_URL + API_PATH + '/plugins', httpOptions)
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


  public getInstalledMappings() {
    this._mappingPollingState.next(PollingState.POLLING)
    return this.http
      .get(BASE_URL + API_PATH + '/mappings/installed', httpOptions)
      .pipe(
        delayedRetry(1000, 3),
        catchError(error => {
          console.error("Error receiving Mappings: ", error);
          this._mappingPollingState.next(PollingState.ERROR)
          return EMPTY
        }),
        map((res: any) => {
          if (res.length != 0) {
            this._mappingPollingState.next(PollingState.SUCCESS)
          } else if (res.length === 0) {
            this._mappingPollingState.next(PollingState.EMPTY)
          }
          this._installedMappingsList.next(res)
        }))
  }

  public getDiscoveredMappings() {
    this._mappingPollingState.next(PollingState.POLLING)
    return this.http
      .get(BASE_URL + API_PATH + '/mappings/discovered', httpOptions)
      .pipe(
        delayedRetry(1000, 3),
        catchError(error => {
          console.error("Error receiving Mappings: ", error);
          this._mappingPollingState.next(PollingState.ERROR)
          return EMPTY
        }),
        map((res: any) => {
          if (res.length != 0) {
            this._mappingPollingState.next(PollingState.SUCCESS)
          } else if (res.length === 0) {
            this._mappingPollingState.next(PollingState.EMPTY)
          }
          this._discoverMappingsList.next(res)
        }))
  }

  public getResolvedMapping(modelId){
    return this.http
      .get(BASE_URL + API_PATH + '/mappings/'+ modelId + '/resolve', httpOptions)
      .pipe(
        catchError(error => {
          console.error("Error resolving mapping with modelId: ", modelId,"Error: ", error);
          return EMPTY
        }),
        map((res: any) => {
            console.log(res)
        }))
  }



  // install or uninstall mapping
  public updateMappingInstallState(modelId, install) {
    var actionSelector = install ? '/install' : '/uninstall'


    this.http.put(BASE_URL + API_PATH + '/mappings/' + modelId + actionSelector, httpOptions,
      { observe: 'response' })
      .subscribe(res => {
        if (res.status === 200) {
          this.getInstalledMappings().subscribe()
          this.getDiscoveredMappings().subscribe()
        }
      })
  }


  public getUser() {
    return this.http
      .get(BASE_URL + API_PATH + '/context/user', httpOptions)
      .pipe(
        catchError(error => {
          switch (error.status) {
            case 401:
              this._loginState.next(LoginState.UNAUTHORIZED)
              this._authorizedUserId.next("")
              console.log("Unauthorized: ", error);
              break;
            default:
              this._loginState.next(LoginState.ERROR)
              this._authorizedUserId.next("")
              console.log("(Login State) Error: ", error);
              break;
          }
          return EMPTY
        }),
        map((res: any) => {
          if (res.userId !== null) {
            this._loginState.next(LoginState.AUTHORIZED)
            this._authorizedUserId.next(res.userId)
            console.log("received " + res.userId)
          }
        }))
  }


  public login(){
    window.location.replace(BASE_URL + '/login/github')
 }
 public logout(){
   window.location.replace(BASE_URL + '/logout')
}
}

