import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";

import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {environment} from "../environments/environment";
import {API_URL} from "./config/injectables";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule
    ],
    providers: [
        {provide: API_URL, useValue: environment.apiUrl}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
