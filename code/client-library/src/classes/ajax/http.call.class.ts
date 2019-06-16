export class HttpCall<T> {
    public body: T;
    public headers: Map<string, string>;

    constructor() {
        this.headers = new Map<string, string>();
    }
}
