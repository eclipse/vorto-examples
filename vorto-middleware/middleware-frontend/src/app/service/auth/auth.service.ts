import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpRequest, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { headersToString } from 'selenium-webdriver/http';
import { Options } from 'selenium-webdriver/chrome';




export const BASE_URL = environment.apiBaseUrl



@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(){
     window.open(BASE_URL+ '/login/github');

 
  }


}
