package be.howest.ti.mars.web.bridge;

import be.howest.ti.mars.logic.domain.link.Link;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The Response class is responsible for translating the result of the controller into
 * JSON responses with an appropriate HTTP code.
 */
public class Response {

    public static final String APPLICATION_JSON = "application/json";

    private Response() { }

    private static void sendOkJsonResponse(RoutingContext ctx, JsonObject response) {
        sendJsonResponse(ctx, 200, response);
    }

    public static void sendEmptyResponse(RoutingContext ctx, int statusCode) {
        ctx.response()
                .setStatusCode(statusCode)
                .end();
    }

    public static void sendInformationResponse(RoutingContext ctx, int version){
        sendOkJsonResponse(ctx,  new JsonObject()
                .put("version", version));
    }

    public static void sendJsonResponse(RoutingContext ctx, int statusCode, Object response) {
        ctx.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                .setStatusCode(statusCode)
                .end(Json.encodePrettily(response));
    }

    public static void sendItems(RoutingContext ctx, Object response){
        sendOkJsonResponse(ctx,  new JsonObject().put("items", response));
    }

    public static void sendPrice(RoutingContext ctx, double price){
        sendJsonResponse(ctx, 201, new JsonObject().put("price", price));
    }

    public static void sendDetailUser(RoutingContext ctx, User user, int totalSent, int totalReceived, int reached){
        JsonObject jsonObject = JsonObject.mapFrom(user);
        jsonObject.put("totalSent", totalSent);
        jsonObject.put("totalReceived", totalReceived);

        JsonObject limit = new JsonObject();
        limit.put("reached", reached);
        limit.put("max", user.getPricePlan().getMaxItems());

        jsonObject.put("limit", limit);

        sendJsonResponse(ctx, 200, jsonObject);
    }

    public static void sendTransporters(RoutingContext ctx, int statusCode, List<Transporter> list){
        JsonArray jsonArray = new JsonArray();
        for (Transporter transporter : list){
            jsonArray.add(getTransporterInJsonObject(transporter, false));
        }

        ctx.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                .setStatusCode(statusCode)
                .end(Json.encodePrettily(jsonArray));
    }

    public static void sendTransporter(RoutingContext ctx, int statusCode, Transporter transporter){
        ctx.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                .setStatusCode(statusCode)
                .end(Json.encodePrettily(getTransporterInJsonObject(transporter, true)));
    }

    public static void sendCreateTransporter(RoutingContext ctx, String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("id", id);

        ctx.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                .setStatusCode(201)
                .end(Json.encodePrettily(jsonObject));
    }
    public static void sendItemsHistory(RoutingContext ctx, List<Link> object){
        JsonArray result = new JsonArray();
        for (Link link : object){
            result.add(getItemInJsonObject(link));
        }

        ctx.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                .setStatusCode(200)
                .end(Json.encodePrettily(result));
    }

    public static void sendItemHistory(RoutingContext ctx, Link object){
        sendOkJsonResponse(ctx, getItemInJsonObject(object));
    }

    private static JsonObject getItemInJsonObject(Link object){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("id", object.getItem().getId());
        jsonObject.put("name", object.getItem().getName());
        jsonObject.put("action", object.getItem().getStatus());
        jsonObject.put("timeSent", object.getItem().getSendTime().format(DateTimeFormatter.ofPattern("yyy-MM-dd H:m")));
        jsonObject.put("timeReceived", object.getItem().getReceivedTime().format(DateTimeFormatter.ofPattern("yyy-MM-dd H:m")));
        jsonObject.put("receiver", object.getReceiverUser().getId());
        jsonObject.put("origin", object.getSender().getId());
        jsonObject.put("sender", object.getReceiver().getId());

        return jsonObject;
    }

    private static JsonObject getTransporterInJsonObject(Transporter transporter, boolean addIP){
        JsonObject transporterJson = new JsonObject();
        transporterJson.put("id", transporter.getId());
        transporterJson.put("name", transporter.getName());
        transporterJson.put("size", transporter.getSize());

        JsonObject locationJson = new JsonObject();
        locationJson.put("coordinates", transporter.getBuilding().getCoordinates());

        JsonObject buildingJson = new JsonObject();
        buildingJson.put("typeOfBuilding", transporter.getBuilding().getTypeOfLocation());
        buildingJson.put("id", transporter.getBuilding().getId());

        locationJson.put("building", buildingJson);
        transporterJson.put("location", locationJson);

        if (addIP){
            transporterJson.put("ipAddress", transporter.getIp());
        }

        return transporterJson;
    }

    public static void sendFailure(RoutingContext ctx, int code, String quote) {
        sendJsonResponse(ctx, code, new JsonObject()
                .put("failure", code)
                .put("cause", quote));
    }
}