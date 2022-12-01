package be.howest.ti.mars.web.external;

import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.web.exceptions.TransporterAPIException;
import io.vertx.core.json.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
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

    public boolean isTransporterReady(){
        JsonObject result = sendGET("/api/status");

        assert result != null;
        return result.getBoolean("ready");
    }

    public Coordinates getTransporterCoordinates(){
        JsonObject result = sendGET("/api/location");

        assert result != null;
        return new Coordinates(result.getFloat("longitude"), result.getFloat("latitude"));
    }

    private JsonObject sendGET(String params){
        try {
            URL obj = new URL(ipAddress + params);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
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
            }
        } catch (IOException e){
            System.out.println(e);
            throw new TransporterAPIException("Transporter is unavailable!");
        }
        return null;
    }
}
