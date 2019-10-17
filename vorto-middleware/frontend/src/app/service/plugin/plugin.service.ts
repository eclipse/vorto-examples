import { map } from 'rxjs/operators';
import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Plugin } from 'src/app/model/plugin';

const apiBaseURL: string = "http://localhost:8080/api/v1"
const username: string = "admin"
const password: string = "secret"

@Injectable({ providedIn: 'root' })
export class PluginService implements OnInit {

  constructor(private http: HttpClient) { }

  public getPlugins() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }

    return this.http
      .get(apiBaseURL + "/plugins", httpOptions)
      .pipe(map((res: any) => {
        return res
      }))
  }



  ngOnInit(): void {
    throw new Error("Method not implemented.");
  }

}
