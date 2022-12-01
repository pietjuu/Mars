package be.howest.ti.mars.web.external;

import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.web.exceptions.TransporterAPIException;
import io.vertx.core.json.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TransporterAPI {

    private final String ipAddress;

    public TransporterAPI(Transporter transporter){
        String ip = transporter.getIp();

        if (!ip.contains("https") && !ip.contains("http")){
            ip = "https://" + ip;
        }

        this.ipAddress = ip;
    }

    public JsonObject getLocation(){
        return callAPI("/api/location", "GET");
    }

    public JsonObject getStatus(){
        return callAPI("/api/status", "GET");
    }

    public JsonObject getScan(){
        return callAPI("/api/scan", "GET");
    }

    public JsonObject addSend(){
        return callAPI("/api/send", "POST");
    }

    private JsonObject callAPI(String params, String method){
        try {
            URL obj = new URL(ipAddress + params);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return new JsonObject(response.toString());
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                throw new TransporterAPIException(responseCode, new JsonObject(response.toString()).getString("description"));
            }
        } catch (IOException e){
            throw new TransporterAPIException(404, "Transporter is unavailable!");
        }
    }
}
