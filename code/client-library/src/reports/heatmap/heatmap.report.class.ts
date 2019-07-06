
export interface HeatRecord {
    mouseX: number;
    mouseY: number;
    heatLevel: number;
}

export interface HeatmapReport {
    recordsCount: number;
    records: HeatRecord[];
}
