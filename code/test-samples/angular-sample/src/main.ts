import {enableProdMode} from "@angular/core";
import {platformBrowserDynamic} from "@angular/platform-browser-dynamic";

import {AppModule} from "./app/app.module";
import {environment} from "./environments/environment";
import {MetricsMonitor} from "@mjamsek/metrics-monitor";

if (environment.production) {
    enableProdMode();
}

MetricsMonitor.init({
    applicationName: "angular-sample",
    mode: "heatmap",
    serverUrl: "http://localhost:8080",
    log: "debug"
}).then(() => {
    platformBrowserDynamic().bootstrapModule(AppModule)
        .catch(err => console.error(err));
}).catch((err: Error) => {
    console.error(err);
});
