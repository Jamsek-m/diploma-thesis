import {SinglePercentileReport} from "./common.types";

export interface SinglePageReport {
  pathname: string;
  minLoadTime: number;
  maxLoadTime: number;
  avgLoadTime: number;
  pageHits: number;
  percentiles: SinglePercentileReport[];
}

export interface PageLoadReport {
  pages: SinglePageReport[];
  averagePageLoadTime: number;
}
