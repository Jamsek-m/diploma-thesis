import {Component, OnInit} from "@angular/core";
import {NavigationEnd, Router} from "@angular/router";
import {MetricsMonitor} from "@mjamsek/metrics-monitor";

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.scss"]
})
export class AppComponent implements OnInit {
    title = "angular-sample";

    constructor(private router: Router) {

    }

    ngOnInit(): void {
        this.router.events.subscribe(routerEvent => {
            if (routerEvent instanceof NavigationEnd) {
                MetricsMonitor.redrawHeatmap();
            }
        });
    }

}
