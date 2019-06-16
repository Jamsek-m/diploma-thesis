export class AjaxOptions {
    // url to be called with ajax
    public url: string = null;
    // HTTP method. Defaults to 'GET'
    public method?: string = "GET";
    // Map of request headers
    public headers?: Map<string, string> = null;
}
