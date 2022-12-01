package be.howest.ti.mars.web.external;

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
        InetAddress result;

        try {
            if (transporter.getIp().contains("https") || transporter.getIp().contains("http")){
                result = InetAddress.getByName(new URL(transporter.getIp()).getHost());
            } else {
                result = InetAddress.getByName(new URL("https://" + transporter.getIp()).getHost());
            }

            if (result.isReachable(1000)){
                throw new TransporterAPIException("Transporter is unavailable!");
            }

            this.ipAddress = result.toString();
        } catch (IOException e){
            throw new TransporterAPIException("Transporter is unavailable!");
        }
    }

    private JsonObject sendGET(String params) throws IOException {
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
            throw new TransporterAPIException("Transporter is unavailable!");
        }
        return null;
    }
}
