import {AfterViewInit, Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {MetricsMonitor} from "@mjamsek/metrics-monitor";

@Component({
    selector: "app-third-page",
    templateUrl: "./third-page.component.html",
    styleUrls: ["./third-page.component.scss"]
})
export class ThirdPageComponent implements OnInit, AfterViewInit {

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    ngAfterViewInit(): void {
        MetricsMonitor.logPageLoadEnd(this.router.url);
    }

}
