import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {InitInfo} from "../models/initInfo.model";
import {Data} from "@angular/router";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class TransactionInfoService {

  constructor(private http: HttpClient) {
  }

  //private userUrl = 'http://localhost:8080/user-portal/user';
  private userUrl = '/api';

  public getInitInfo() {
    //
    return this.http.get<InitInfo>(this.userUrl + '/initInfo');
  }

  public getTransactionInfo(requestModel) {
    //
    console.log(requestModel);

    let httpParams = new HttpParams();
    httpParams = httpParams.append("env", requestModel.env);
    httpParams = httpParams.append("flow", requestModel.flow);
    httpParams = httpParams.append("id", requestModel.id);
    console.log(httpParams);
    return this.http.post<Data>(this.userUrl + '/getTransactionInfo', requestModel,
      {headers: new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8')});
    // return this.http.get<Data>(this.userUrl + '/getTransactionInfo', requestModel);
  }


}
