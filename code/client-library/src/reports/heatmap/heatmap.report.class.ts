
export interface HeatRecord {
    mouseX: number;
    mouseY: number;
    heatLevel: number;
}

export interface HeatmapReport {
    mouseBoxSize: number;
    recordsCount: number;
    records: HeatRecord[];
}
