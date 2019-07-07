import {MouseRecord} from "../classes/mouse.record.interface";
import {SocketService} from "../socket";
import {MouseTrackMessage} from "../socket/messages/session.socket.message.class";
import {UrlUtil} from "../utils/url.util";

export class MouseTracker {

    private static BUFFER: MouseRecord[];

    private static BUFFER_LIMIT = 10;

    public static registerMouseTracker(bufferLimit: number = 10) {
        MouseTracker.BUFFER_LIMIT = bufferLimit;
        MouseTracker.BUFFER = [];
        window.addEventListener("mousemove", MouseTracker.onMouseTrack);
    }

    private static onMouseTrack(e: MouseEvent): void {
        if (MouseTracker.BUFFER.length < MouseTracker.BUFFER_LIMIT) {
            const record: MouseRecord = {
                pageX: e.pageX,
                pageY: e.pageY,
                pathname: UrlUtil.getCurrentPathname(),
            };
            MouseTracker.BUFFER.push(record);
        } else {
            const mouseTrackMessage = new MouseTrackMessage();
            mouseTrackMessage.coordinates = MouseTracker.BUFFER;

            SocketService.sendMessage(mouseTrackMessage);

            MouseTracker.BUFFER = [];
        }
    }

}
