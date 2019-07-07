import {SocketService} from "../socket";
import {execSessionFunc} from "../socket/exec.session.func";
import {PageLoadMessage} from "../socket/messages/session.socket.message.class";
import {UrlUtil} from "../utils/url.util";

export class PageLoadTracker {

    private static loadQueue: {[key: string]: {time: number, firstPage: boolean}} = {};

    private static firstPage = true;

    public static trackPageLoadStart(pageName: string) {
        PageLoadTracker.loadQueue[pageName] = {
            time: Date.now(),
            firstPage: PageLoadTracker.firstPage,
        };
        if (PageLoadTracker.firstPage) {
            PageLoadTracker.firstPage = false;
        }
    }

    public static trackPageLoadEnd(pageName: string) {
        const loadEnd = Date.now();
        if (PageLoadTracker.loadQueue[pageName]) {
            execSessionFunc(() => {
                const message = new PageLoadMessage();
                message.loadEnd = loadEnd;
                message.loadStart = PageLoadTracker.loadQueue[pageName].time;
                message.pathname = UrlUtil.getCurrentPathname();
                message.firstPage = PageLoadTracker.loadQueue[pageName].firstPage;
                delete PageLoadTracker.loadQueue[pageName];

                SocketService.sendMessage(message);
            });
        }
    }

}
