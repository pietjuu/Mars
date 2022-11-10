package be.howest.ti.mars.logic.domain.items;

import be.howest.ti.mars.logic.domain.transporter.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public class Item {

    private final String id;
    private final String name;
    private final Size size;
    private ItemStatus status;
    private LocalDateTime sendTime;
    private LocalDateTime receivedTime;
    private int atoms;

    public Item(String name, Size size) {
        this.id = UUID.randomUUID().toString();
        this.status = ItemStatus.UNDEFINED;
        this.name = name;
        this.size = size;
    }

    public Item(String id, String name, Size size){
        this.id = id;
        this.status = ItemStatus.UNDEFINED;
        this.name = name;
        this.size = size;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public void setReceivedTime(LocalDateTime receivedTime) {
        this.receivedTime = receivedTime;
    }

    public void setAtoms(int atoms) {
        this.atoms = atoms;
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

    public ItemStatus getStatus() {
        return status;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public LocalDateTime getReceivedTime() {
        return receivedTime;
    }

    public int getAtoms() {
        return atoms;
    }
}
