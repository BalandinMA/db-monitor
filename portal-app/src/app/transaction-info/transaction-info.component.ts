import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {InitInfo} from "../models/initInfo.model";
import {RequestModel} from "../models/request.model";
import {TransactionInfoService} from "./transaction-info.service";

@Component({
  selector: 'transaction-info',
  templateUrl: './transaction-info.component.html',
  styleUrls: ["./transaction-info.component.css"]
})
export class TransactionInfoComponent implements OnInit {

  data: object;
  // = {
  //   "INBOUND": [
  //     {
  //       "INBD_CHANNEL_ID": "666",
  //       "INBD_TIME": "2018-09-05 17:36:26.649",
  //       "INBD_STATUS": "22",
  //       "INBD_ID": "QQQ"
  //     }
  //   ],
  //   "PREPROCESSING": [
  //     {
  //       "PREPROC_FILE_TYPE": "TXT_FILE",
  //       "PREPROC_FILE_SZE": "1 MB",
  //       "PREPROC_ID": "2"
  //     },
  //     {
  //       "PREPROC_FILE_TYPE": "TXT_FILE1",
  //       "PREPROC_FILE_SZE": "1 MB",
  //       "PREPROC_ID": "21"
  //     },
  //     {
  //       "PREPROC_FILE_TYPE": "TXT_FILE2",
  //       "PREPROC_FILE_SZE": "1 MB",
  //       "PREPROC_ID": "22"
  //     },
  //     {
  //       "PREPROC_FILE_TYPE": "TXT_FILE3",
  //       "PREPROC_FILE_SZE": "1 MB",
  //       "PREPROC_ID": "23"
  //     },
  //     {
  //       "PREPROC_FILE_TYPE": "TXT_FILE4",
  //       "PREPROC_FILE_SZE": "1 MB",
  //       "PREPROC_ID": "24"
  //     },
  //     {
  //       "PREPROC_FILE_TYPE": "TXT_FILE5",
  //       "PREPROC_FILE_SZE": "1 MB",
  //       "PREPROC_ID": "25"
  //     }
  //   ],
  //   "PROCESSING": [
  //     {
  //       "PROC_CUSTOMER_ID": "CUST_ID",
  //       "PROC_COUNTERPARTY_ID": "CTRPARTY_ID",
  //       "PROC_TYPE": "234",
  //       "PROC_BUSINESS_TYPE": "BIG_DEAL",
  //       "PROC_STATUS": "333",
  //       "PROC_ID": "3"
  //     }
  //   ],
  //   "POSTPROCESSING": [
  //     {
  //       "POSTPROC_TYPE": "post processing",
  //       "POSTPROC_FORMAT": "XML",
  //       "POSTPROC_ID": "4"
  //     }
  //   ],
  //   "OUTBOUND": [
  //     {
  //       "OUTBOUND_ID": "5",
  //       "OUTBOUND_CHANNEL_ID": "OUT_CHANNEL_ID_ROW",
  //       "OUTBOUND_STATUS": "5"
  //     }
  //   ]
  // };

  initInfo: InitInfo;
  //   = {
  //   flows: ["Full way", "modelNames2", "modelNames3"],
  //   environments: ["e2e", "environmentNames2", "environmentNames3"],
  //   colors:{"PREPROCESSING.PREPROC_ID.23":"rgba(255, 0, 0, 0.04)"}
  // };

  requestModel: RequestModel;

  constructor(private router: Router, private transactionInfoService: TransactionInfoService) {
  }

  ngOnInit() {
    // this.requestModel = new requestModel();
    this.requestModel = {
      env: '',
      flow: '',
      id: ''
    };
    // this.dataKeys =

    // this.initInfo = {
    //   flows: ["modelNames1", "modelNames2", "modelNames3"],
    //   environments: ["environmentNames1", "environmentNames2", "environmentNames3"]
    // }


    this.transactionInfoService.getInitInfo()
      .subscribe(data => {
        this.initInfo = data;
      });
  };

  getTransactionInfo(): void {
    this.transactionInfoService.getTransactionInfo(this.requestModel)
      .subscribe(data => {
        this.data = data;
      })
  };

  getColor(tableName: string, tableRow: {}): string {
    for (var key in tableRow) {
      if (tableRow.hasOwnProperty(key) && this.initInfo.colors.hasOwnProperty(tableName + '.' + key + '.' + tableRow[key])) {
        if (this.initInfo.colors[tableName + '.' + key + '.' + tableRow[key]])
          return this.initInfo.colors[tableName + '.' + key + '.' + tableRow[key]];
      }
    }
    return 'rgba(29, 255, 1, 0.06)';
  }
}


