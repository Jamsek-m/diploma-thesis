import {AfterViewInit, Component, OnInit} from "@angular/core";
import {MetricsMonitor} from "@mjamsek/metrics-monitor";
import {Router} from "@angular/router";

@Component({
    selector: "app-second-page",
    templateUrl: "./second-page.component.html",
    styleUrls: ["./second-page.component.scss"]
})
export class SecondPageComponent implements OnInit, AfterViewInit {

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    ngAfterViewInit(): void {
        MetricsMonitor.logPageLoadEnd(this.router.url);
    }

}
