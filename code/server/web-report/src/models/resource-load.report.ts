export interface ResourceValue {
  average: number;
  minimum: number;
  maximum: number;
}

export interface SingleResourceReport {
  name: string;
  type: string;
  payloadSize: ResourceValue;
  requestSize: ResourceValue;
  requestTime: ResourceValue;
}

export interface ResourceLoadReport {
  resources: SingleResourceReport[];
}
