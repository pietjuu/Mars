import EventBus from "@/assets/js/libs/vertx-eventbus"

const CHANNEL_TO_SERVER = "events.to.server";
const EVENTBUS_PATH = "http://localhost:8080/events";

function openSocket() {
    let bus = new EventBus(EVENTBUS_PATH);

    function sendToServer(message) {
        bus.send(CHANNEL_TO_SERVER, message);
    }

    return sendToServer;
}