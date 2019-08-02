import {Inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppStartupReport, PageLoadReport, ResourceLoadReport} from "../../models";
import {map} from "rxjs/operators";
import {API_URL} from "../config/injectables";

@Injectable({
    providedIn: "root"
})
export class ReportsService {

    constructor(private http: HttpClient, @Inject(API_URL) private apiUrl: string) {

    }

    public getAppStartupReport(applicationName: string): Observable<AppStartupReport> {
        const url = `${this.apiUrl}/metrics/app-startup/${applicationName}`;
        return this.http.get(url).pipe(map(res => res as AppStartupReport));
    }

    public getPageLoadReport(applicationName: string): Observable<PageLoadReport> {
        const url = `${this.apiUrl}/metrics/page-load/${applicationName}`;
        return this.http.get(url).pipe(map(res => res as PageLoadReport));
    }

    public getResourceLoadReport(applicationName: string): Observable<ResourceLoadReport> {
        const url = `${this.apiUrl}/metrics/resource-load/${applicationName}`;
        const params = {
            ignoreCached: "true"
        };
        return this.http.get(url, {params}).pipe(map(res => res as ResourceLoadReport));
    }
}
