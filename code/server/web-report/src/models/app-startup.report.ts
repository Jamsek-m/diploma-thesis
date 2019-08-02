import {SinglePercentileReport} from "./common.types";

export interface AppStartupReport {
  minLoadTime: number;
  maxLoadTime: number;
  avgLoadTime: number;
  percentiles: SinglePercentileReport[];
}
