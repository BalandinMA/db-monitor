import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {InitInfo} from "../models/initInfo.model";
import {Data} from "@angular/router";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class TransactionInfoService {

  constructor(private http: HttpClient) {
  }

  private baseUrl = '/api';

  public getInitInfo() {
    return this.http.post<InitInfo>(this.baseUrl + '/initInfo', {});
  }

  public getTransactionInfo(requestModel) {
    return this.http.post<Data>(this.baseUrl + '/transactionInfo', requestModel,
      {headers: new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8')});
  }


}
