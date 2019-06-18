export class AjaxResponse<T> {
    public body: T;
    public status: number;
    public headers: Map<string, string> = new Map<string, string>();

    public withBody(body: T): AjaxResponse<T> {
        this.body = body as T;
        return this;
    }

    public withStatus(status: number): AjaxResponse<T> {
        this.status = status;
        return this;
    }

    public withHeaders(headersString: string): AjaxResponse<T> {
        const headers = headersString.split("\r\n");
        headers.forEach((headerEntry: string) => {
            if (headerEntry && headerEntry !== "") {
                const headerParts = headerEntry.split(":");
                const headerName = headerParts[0].toLowerCase();
                headerParts.shift();
                const headerValue = headerParts.join(":").trim();
                this.headers.set(headerName, headerValue);
            }
        });
        return this;
    }
}
