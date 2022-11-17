package be.howest.ti.mars.logic.domain.items;

import be.howest.ti.mars.logic.domain.transporter.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Item class
 */
public class Item {

    private final String id;
    private final String name;
    private final Size size;
    private ItemStatus status;
    private LocalDateTime sendTime;
    private LocalDateTime receivedTime;
    private int atoms = 0;

    /**
     * Constructor
     * @param name name of item
     */
    public Item(String name){
        this.id = UUID.randomUUID().toString();
        this.status = ItemStatus.UNDEFINED;
        this.size = new Size(0,0,0);
        this.name = name;
    }

    /**
     * Constructor
     * @param name name of item
     * @param size size of item
     */
    public Item(String name, Size size) {
        this.id = UUID.randomUUID().toString();
        this.status = ItemStatus.UNDEFINED;
        this.name = name;
        this.size = size;
    }

    /**
     * Constructor - this should only be used when importing.
     * @param id uuid
     * @param name name of item
     * @param size size of item
     */
    public Item(String id, String name, Size size){
        this.id = id;
        this.status = ItemStatus.UNDEFINED;
        this.name = name;
        this.size = size;
    }

    public void setStatus(ItemStatus status) {

        if (status.equals(ItemStatus.COMPLETED) && (Objects.isNull(this.receivedTime) || Objects.isNull(this.sendTime))){
            throw new IllegalStateException("There is no received time or send time set.");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + ": \n" +
                "id: " + id + " \n" +
                "size: " + size + " \n" +
                "status: " + status + " \n" +
                "SendTime: " + sendTime + " \n" +
                "ReceivedTime: " + receivedTime + " \n" +
                "Atoms: " + atoms;
    }
}
