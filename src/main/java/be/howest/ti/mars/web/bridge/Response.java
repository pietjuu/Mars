package be.howest.ti.mars.web.bridge;

import be.howest.ti.mars.logic.domain.transporter.Transporter;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

/**
 * The Response class is responsible for translating the result of the controller into
 * JSON responses with an appropriate HTTP code.
 */
public class Response {

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
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setStatusCode(statusCode)
                .end(Json.encodePrettily(response));
    }

    public static void sendItems(RoutingContext ctx, Object response){
        sendOkJsonResponse(ctx,  new JsonObject().put("items", response));
    }

    public static void sendTransporters(RoutingContext ctx, List<Transporter> list){
        JsonArray jsonArray = new JsonArray();
        for (Transporter transporter : list){
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
            jsonArray.add(transporterJson);
        }

        ctx.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(jsonArray));
    }

    public static void sendFailure(RoutingContext ctx, int code, String quote) {
        sendJsonResponse(ctx, code, new JsonObject()
                .put("failure", code)
                .put("cause", quote));
    }
}
