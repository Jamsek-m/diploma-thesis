import {AjaxOptions} from "./ajax.options.class";
import {AjaxResponse} from "./ajax.response.class";
import {HttpCall} from "./http.call.class";

export class AjaxCaller<REQ, RES> {

    private readonly method: string;
    private readonly url: string;
    private request: HttpCall<REQ>;
    private response: HttpCall<RES>;

    constructor(options: AjaxOptions) {
        this.url = options.url;
        this.method = options.method || "GET";
        this.response = null;
        this.request = new HttpCall<any>();
        if (options && options.headers) {
            this.request.headers = options.headers;
        }
    }

    public setHeaders(headers: Map<string, string>): AjaxCaller<REQ, RES> {
        this.request.headers = headers;
        return this;
    }

    public setBody(body?: REQ): AjaxCaller<REQ, RES> {
        this.request.body = body;
        return this;
    }

    public invoke(): Promise<AjaxResponse<RES>> {
        return new Promise<AjaxResponse<RES>>((resolve, reject) => {
            const req = new XMLHttpRequest();
            req.open(this.method, this.url, true);
            this.request.headers.forEach((val: string, key: string) => {
                req.setRequestHeader(key, val);
            });
            req.addEventListener("load", ($event: ProgressEvent) => {
                try {
                    const responseJSON = JSON.parse(req.responseText);
                    const response = new AjaxResponse<RES>()
                        .withStatus(req.status)
                        .withHeaders(req.getAllResponseHeaders())
                        .withBody(responseJSON);
                    resolve(response);
                } catch (parsingError) {
                    reject(new TypeError("Error parsing response body! Currently only JSON format is supported."));
                }
            });
            req.addEventListener("error", ($event: ProgressEvent) => {
                reject(new Error("Error when performing ajax request!"));
            });
            if (this.request.body) {
                req.send(JSON.stringify(this.request.body));
            } else {
                req.send();
            }
        });
    }
}
