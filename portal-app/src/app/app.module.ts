import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import {HttpClientModule} from "@angular/common/http";
import {TransactionInfoComponent} from "./transaction-info/transaction-info.component";
import {MapToIterable} from "./map-to-iterable.pipe";
import {TransactionInfoService} from "./transaction-info/transaction-info.service";

@NgModule({
  declarations: [
    AppComponent,
    TransactionInfoComponent,
    MapToIterable
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [TransactionInfoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
