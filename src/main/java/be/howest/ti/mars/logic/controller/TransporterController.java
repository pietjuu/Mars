package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.exceptions.TransporterException;
import be.howest.ti.mars.web.external.TransporterAPI;
import io.vertx.core.json.JsonObject;

public class TransporterController {

    public Coordinates getTransporterLocation(Transporter transporter){
        TransporterAPI transporterAPI = new TransporterAPI(transporter);
        JsonObject jsonObject = transporterAPI.getLocation();

        Coordinates coordinatesAPI = new Coordinates(jsonObject.getFloat("latitude"), jsonObject.getFloat("longitude"));
        Coordinates coordinatesDB = transporter.getBuilding().getCoordinates();

        if (!coordinatesDB.equals(coordinatesAPI)){
            throw new TransporterException("Transporter location not the same!");
        }

        return coordinatesDB;
    }

    public boolean getTransporterStatus(Transporter transporter){
        TransporterAPI transporterAPI = new TransporterAPI(transporter);
        JsonObject jsonObject = transporterAPI.getStatus();

        return jsonObject.getBoolean("ready");
    }

}
