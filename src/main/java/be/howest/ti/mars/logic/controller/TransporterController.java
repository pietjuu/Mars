package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.transporter.Molecule;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.exceptions.TransporterException;
import be.howest.ti.mars.web.external.TransporterAPI;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    private Set<Molecule> getAllMolecules(JsonObject jsonObject){
        Set<Molecule> set = new HashSet<>();

        for (int i = 0; i < jsonObject.getJsonArray("molecules").size(); i++){
            JsonObject moleculeJSON = jsonObject.getJsonArray("molecules").getJsonObject(i);
            JsonObject moleculeJSONPosition = moleculeJSON.getJsonObject("position");
            Molecule molecule = new Molecule(moleculeJSON.getInteger("id"), moleculeJSON.getString("molecule"), moleculeJSONPosition.getInteger("x"), moleculeJSONPosition.getInteger("y"));
            set.add(molecule);
        }

        return set;
    }

    private Map<String , Integer> getSummaryMolecules(JsonObject jsonObject){
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < jsonObject.getJsonArray("summary").size(); i++){
            JsonObject moleculeJSON = jsonObject.getJsonArray("summary").getJsonObject(i);
            map.put(moleculeJSON.getString("molecule"), moleculeJSON.getInteger("count"));
        }

        return map;
    }

}
