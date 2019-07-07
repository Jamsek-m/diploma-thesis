import {AjaxResponse} from "../../classes";
import {AjaxCaller} from "../../classes/ajax";
import {ConfigService} from "../../configuration";
import {HEATMAP_REPORT_ENDPOINT} from "../../library.config";
import {UrlUtil} from "../../utils/url.util";
import {HeatmapDrawUtil} from "./heatmap.draw.util";
import {HeatmapReport} from "./heatmap.report.class";

export class HeatmapService {

    public static drawHeatmap(): void {

        const request: any = {
            applicationName: ConfigService.getConfig().applicationName,
            pathname: UrlUtil.getCurrentPathname(),
        };
        new AjaxCaller<any, HeatmapReport>({
            method: "POST",
            url: ConfigService.getConfig().serverUrl + HEATMAP_REPORT_ENDPOINT,
        }).setHeaders(new Map<string, string>(Object.entries({"Content-type": "application/json"})))
            .setBody(request).invoke().then((res: AjaxResponse<HeatmapReport>) => {
            if (res.status === 200) {
                HeatmapDrawUtil.drawHeatmap(res.body);
            } else {
                console.error(res);
            }
        }).catch((err) => {
            console.error(err);
        });
    }

}
