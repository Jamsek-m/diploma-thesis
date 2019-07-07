import {AfterViewInit, Component, OnInit} from "@angular/core";
import {NavigationEnd, NavigationStart, Router} from "@angular/router";
import {MetricsMonitor} from "@mjamsek/metrics-monitor";

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.scss"]
})
export class AppComponent implements OnInit, AfterViewInit {
    title = "angular-sample";

    constructor(private router: Router) {

    }

    ngOnInit(): void {
        MetricsMonitor.logApplicationStartup();
        this.router.events.subscribe(routerEvent => {
            if (routerEvent instanceof NavigationEnd) {
                MetricsMonitor.redrawHeatmap();
            } else if (routerEvent instanceof NavigationStart) {
                MetricsMonitor.logPageLoadStart(routerEvent.url);
            }
        });
    }

    ngAfterViewInit(): void {

    }

}
