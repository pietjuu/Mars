const CHNL_TO_SERVER = "events.to.server";
const CHNL_TO_CLIENTS_BROADCAST = "events.to.clients";
const CHNL_TO_CLIENT_UNICAST = "events.to.client.";
const EVENTBUS_PATH = "https://project-ii.ti.howest.be/mars-05/events";

function openSocket() {
	let bus = new WebSocket(EVENTBUS_PATH);

	// save and create client id to local storage
	clientId = localStorage.getItem("clientId");
    if (clientId === null) {
        clientId = uuidv4();
        localStorage.setItem('clientId', clientId);
    }

	function sendToServer(message) {
		// append client id to the data we send to the server
		message.clientId = clientId;
		bus.send(CHNL_TO_SERVER, message);
	}

    bus.onopen = function() {
        bus.registerHandler(CHNL_TO_CLIENTS_BROADCAST, onMessage);

		/* If a message is send on the address events.to.client.clientId, the client with id clientId will also call the onMessage method.
		Clients that don't have that id will not call onMessage when something is sent on that address. Now it is possible for our server to send a targetted message. */
		bus.registerHandler(CHNL_TO_CLIENT_UNICAST + clientId, onMessage);
    };


	return sendToServer;
}