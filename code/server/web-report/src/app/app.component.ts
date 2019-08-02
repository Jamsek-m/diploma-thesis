import {Component, OnInit} from "@angular/core";
import {ReportsService} from "./services";

@Component({
  selector: "mj-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent implements OnInit {

    constructor(private reportsService: ReportsService) {

    }

    ngOnInit(): void {
        this.reportsService.getAppStartupReport("angular-sample").subscribe(report => {
            console.log(report);
        });
        this.reportsService.getPageLoadReport("angular-sample").subscribe(report => {
            console.log(report);
        });
        this.reportsService.getResourceLoadReport("angular-sample").subscribe(report => {
            console.log(report);
        });
    }

}
