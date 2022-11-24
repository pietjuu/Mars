package be.howest.ti.mars.web;

import be.howest.ti.mars.logic.controller.MockMarsController;
import be.howest.ti.mars.logic.data.Repositories;
import be.howest.ti.mars.web.bridge.MarsOpenApiBridge;
import be.howest.ti.mars.web.bridge.MarsRtcBridge;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(VertxExtension.class)
@SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert","PMD.AvoidDuplicateLiterals"})
/*
 * PMD.JUnitTestsShouldIncludeAssert: VertxExtension style asserts are marked as false positives.
 * PMD.AvoidDuplicateLiterals: Should all be part of the spec (e.g., urls and names of req/res body properties, ...)
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OpenAPITest {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";
    public static final String MSG_200_EXPECTED = "If all goes right, we expect a 200 status";
    public static final String MSG_201_EXPECTED = "If a resource is successfully created.";
    public static final String MSG_202_EXPECTED = "If a resource is successfully deleted";
    private Vertx vertx;
    private WebClient webClient;

    @BeforeAll
    void deploy(final VertxTestContext testContext) {
        Repositories.shutdown();
        vertx = Vertx.vertx();

        WebServer webServer = new WebServer(new MarsOpenApiBridge(new MockMarsController()), new MarsRtcBridge());
        vertx.deployVerticle(
                webServer,
                testContext.succeedingThenComplete()
        );
        webClient = WebClient.create(vertx);
    }

    @AfterAll
    void close(final VertxTestContext testContext) {
        vertx.close(testContext.succeedingThenComplete());
        webClient.close();
        Repositories.shutdown();
    }

    @Test
    void getInformation(final VertxTestContext testContext){
        // Using of standard controller for testing that constructor
        WebServer webServer = new WebServer(new MarsOpenApiBridge(), new MarsRtcBridge());
        vertx.deployVerticle(
                webServer,
                testContext.succeedingThenComplete()
        );
        WebClient webClient2 = WebClient.create(vertx);

        webClient2.get(PORT, HOST, "/").send()
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    assertEquals(200, response.statusCode(), MSG_200_EXPECTED);
                    assertEquals(1, response.bodyAsJsonObject().getInteger("version"), "Version doesn't match");
                    testContext.completeNow();
                }));
    }

    @Test
    void getUsers(final VertxTestContext testContext){
        webClient.get(PORT, HOST, "/api/users").send()
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    assertEquals(200, response.statusCode(), MSG_200_EXPECTED);
                    assertEquals(2, response.bodyAsJsonArray().size());
                    List<String> stringList = new ArrayList<>();
                    stringList.add(response.bodyAsJsonArray().getJsonObject(0).getString("firstname"));
                    stringList.add(response.bodyAsJsonArray().getJsonObject(1).getString("firstname"));

                    assertTrue(stringList.contains("Thibo"));
                    assertTrue(stringList.contains("Glenn"));
                    testContext.completeNow();
                }));
    }

    @Test
    void createUsers(final VertxTestContext testContext){
        webClient.post(PORT, HOST, "/api/users").sendJsonObject(createUser())
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    assertEquals(201, response.statusCode(), MSG_201_EXPECTED);
                    assertEquals("testID01", response.bodyAsJsonObject().getString("id"));
                    testContext.completeNow();
                }));
    }

    @Test
    void getUser(final VertxTestContext testContext){
        webClient.get(PORT, HOST, "/api/users/0155").bearerTokenAuthentication("0155").send()
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() ->  {
                    assertEquals(200, response.statusCode(), MSG_200_EXPECTED);
                    assertEquals("0155", response.bodyAsJsonObject().getString("id"));
                    assertEquals("Glenn", response.bodyAsJsonObject().getString("firstname"));
                    testContext.completeNow();
                }));
    }

    @Test
    void deleteUser(final VertxTestContext testContext){
        webClient.delete(PORT, HOST, "/api/users/0155").bearerTokenAuthentication("0155").send()
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() ->  {
                    assertEquals(202, response.statusCode(), MSG_202_EXPECTED);
                    testContext.completeNow();
                }));
    }

    @Test
    void getShippertBlackList(final VertxTestContext testContext){
        webClient.get(PORT, HOST, "/api/blacklist").send()
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    assertEquals(200, response.statusCode());
                    assertEquals("Gun", response.bodyAsJsonObject().getJsonArray("items").getString(0));
                    testContext.completeNow();
                }));
    }

    @Test
    void getUserBlackList(final VertxTestContext testContext){
        webClient.get(PORT, HOST, "/api/users/te-1/blacklist").bearerTokenAuthentication("te-1").send()
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    assertEquals(200, response.statusCode());
                    assertEquals("Bananas", response.bodyAsJsonObject().getJsonArray("items").getString(0));
                    testContext.completeNow();
                }));
    }

    @Test
    void addItemToUserBlacklist(final VertxTestContext testContext){
        webClient.post(PORT, HOST, "/api/users/te-1/blacklist").bearerTokenAuthentication("te-1").sendJsonObject(new JsonObject().put("itemName", "cheese"))
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    assertEquals(201, response.statusCode());
                    testContext.completeNow();
                }));
    }

    @Test
    void deleteItemToUserBlacklist(final VertxTestContext testContext){
        webClient.delete(PORT, HOST, "/api/users/te-1/blacklist/cheese").bearerTokenAuthentication("te-1").send()
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    assertEquals(202, response.statusCode());
                    testContext.completeNow();
                }));
    }

    @Test
    void getTransporters(final VertxTestContext testContext){
        webClient.get(PORT, HOST, "/api/transporters").send()
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    assertEquals(200, response.statusCode());
                    assertEquals(1, response.bodyAsJsonArray().size());
                    assertEquals("TTT-1", response.bodyAsJsonArray().getJsonObject(0).getString("id"));
                    testContext.completeNow();
                }));
    }

    @Test
    void getTransporter(final VertxTestContext testContext){
        webClient.get(PORT, HOST, "/api/transporter/testingTransporter").send()
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    assertEquals(200, response.statusCode());
                    assertEquals("Kitchen", response.bodyAsJsonObject().getString("name"));
                    testContext.completeNow();
                }));
    }

    @Test
    void createUserErrors(final VertxTestContext testContext){
        webClient.post(PORT, HOST, "/api/users").sendJsonObject(new JsonObject().put("a", 1))
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    assertEquals(400, response.statusCode());
                    testContext.completeNow();
                }));
    }

    @Test
    void createTokenErrors(final VertxTestContext testContext){
        webClient.get(PORT, HOST, "/api/users/0154").bearerTokenAuthentication("0155").send()
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() ->  {
                    assertEquals(401, response.statusCode(), MSG_200_EXPECTED);
                    testContext.completeNow();
                }));
    }

    private JsonObject createUser(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("firstname", "bob");
        jsonObject.put("lastname", "bob");
        jsonObject.put("subscription", "STANDARD");

        return jsonObject;
    }
}