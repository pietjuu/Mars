package be.howest.ti.mars.web.external;

import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.web.exceptions.TransporterAPIException;

import java.io.IOException;
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
}
