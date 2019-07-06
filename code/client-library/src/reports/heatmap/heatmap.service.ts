import {AjaxResponse} from "../../classes";
import {AjaxCaller} from "../../classes/ajax";
import {ConfigService} from "../../configuration";
import {HEATMAP_REPORT_ENDPOINT} from "../../library.config";
import {HeatmapDrawUtil} from "./heatmap.draw.util";
import {HeatmapReport} from "./heatmap.report.class";

export class HeatmapService {

    public static drawHeatmap(): void {
        new AjaxCaller<void, HeatmapReport>({
            url: ConfigService.getConfig().serverUrl + HEATMAP_REPORT_ENDPOINT
                + "/" + ConfigService.getConfig().applicationName,
        }).invoke().then((res: AjaxResponse<HeatmapReport>) => {
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
