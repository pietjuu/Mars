package be.howest.ti.mars.logic.domain.transporter;

import be.howest.ti.mars.logic.domain.location.Building;

import java.net.Inet4Address;
import java.util.UUID;

public class Transporter {

    private String id;
    private String name;
    private Size size;
    private Building building;
    private String ip;

    public Transporter(String name, Size size, Building building, String ipAddress){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.size = size;
        this.building = building;
        this.ip = ipAddress;
    }

    public Transporter(String id, String name, Size size, Building building, String ip) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.building = building;
        this.ip = ip;
    }
}
