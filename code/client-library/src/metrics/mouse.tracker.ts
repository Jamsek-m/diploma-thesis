import {MouseRecord} from "../classes/mouse.record.interface";
import {SocketService} from "../socket";
import {MouseTrackMessage} from "../socket/messages/session.socket.message.class";

export class MouseTracker {

    private BUFFER: MouseRecord[];

    private BUFFER_LIMIT = 10;

    public registerMouseTracker(bufferLimit: number = 10) {
        this.BUFFER_LIMIT = bufferLimit;
        this.BUFFER = [];
        window.addEventListener("mousemove", this.onMouseTrack.bind(this));
    }

    private onMouseTrack(e: MouseEvent): void {
        if (this.BUFFER.length < this.BUFFER_LIMIT) {
            const record: MouseRecord = {
                pageX: e.pageX,
                pageY: e.pageY,
            };
            this.BUFFER.push(record);
        } else {
            const mouseTrackMessage = new MouseTrackMessage();
            mouseTrackMessage.coordinates = this.BUFFER;

            SocketService.sendMessage(mouseTrackMessage);

            this.BUFFER = [];
        }
    }

}
