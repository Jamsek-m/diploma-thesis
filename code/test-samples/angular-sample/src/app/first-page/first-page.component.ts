import {AfterViewInit, Component, OnInit} from "@angular/core";
import {MetricsMonitor} from "@mjamsek/metrics-monitor";
import {Router} from "@angular/router";

@Component({
    selector: "app-first-page",
    templateUrl: "./first-page.component.html",
    styleUrls: ["./first-page.component.scss"]
})
export class FirstPageComponent implements OnInit, AfterViewInit {

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    ngAfterViewInit(): void {
        MetricsMonitor.logPageLoadEnd(this.router.url);
    }

}
