package be.howest.ti.mars.logic.domain.transporter;

import be.howest.ti.mars.logic.domain.location.Building;

import java.util.Objects;
import java.util.UUID;

public class Transporter {

    private final String id;
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public Building getBuilding() {
        return building;
    }

    public String getIp() {
        return ip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transporter that = (Transporter) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
