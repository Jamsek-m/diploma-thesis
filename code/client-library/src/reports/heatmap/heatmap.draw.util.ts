import {HeatmapReport, HeatRecord} from "./heatmap.report.class";

export class HeatmapDrawUtil {

    public static drawHeatmap(report: HeatmapReport): void {
        report.records.forEach((record: HeatRecord) => {
            const span = HeatmapDrawUtil.createHeatElement(record);
            document.body.appendChild(span);
        });
    }

    private static createHeatElement(record: HeatRecord): HTMLSpanElement {
        const span: HTMLSpanElement = document.createElement("SPAN") as HTMLSpanElement;

        const color = HeatmapDrawUtil.determineColor(record.heatLevel);

        span.classList.add("heatmap-node", `heatmap-node-${color}`);

        span.style.top = `${record.mouseY * 10}px`;
        span.style.left = `${record.mouseX * 10}px`;

        return span;
    }

    private static determineColor(heatLevel: number): string {
        if (heatLevel > 0 && heatLevel <= 5) {
            return "green";
        } else if (heatLevel > 5 && heatLevel <= 10) {
            return "yellow";
        } else if (heatLevel > 10) {
            return "red";
        }
        return "transparent";
    }

}
