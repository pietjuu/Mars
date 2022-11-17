package be.howest.ti.mars.logic.domain.transporter;

import be.howest.ti.mars.logic.domain.location.Building;

import java.net.Inet4Address;

public class Transporter {

    private String id;
    private String name;
    private Size size;
    private Building building;
    private Inet4Address ip;

    public Transporter(String id, String name, Size size, Building building, Inet4Address ip) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.building = building;
        this.ip = ip;
    }
}
