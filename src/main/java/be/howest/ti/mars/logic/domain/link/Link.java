package be.howest.ti.mars.logic.domain.link;

import be.howest.ti.mars.logic.controller.TransporterController;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;
import be.howest.ti.mars.logic.exceptions.TransporterException;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

public class Link {

    private final String id;
    private User senderUser;
    private User receiverUser;
    private Transporter sender;
    private Transporter receiver;
    private LinkStatus linkStatus;
    private final TransporterController transporterController = new TransporterController();
    private Item item;

    public Link(Transporter sender) {
        this.id = UUID.randomUUID().toString();
        this.sender = sender;
        this.linkStatus = LinkStatus.INITIALIZED;
    }

    public Link(String uuid, Transporter sender, Transporter receiver, LinkStatus linkStatus, Item item){
        this.id = uuid;
        this.sender = sender;
        this.receiver = receiver;
        this.linkStatus = linkStatus;
        this.item = item;
    }

    public void connectLink(User senderUser, Transporter sender,  User receiverUser, Transporter receiver, String itemName, int linksSent){
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
        this.sender = sender;
        this.receiver = receiver;
        this.item = new Item(itemName);

        // Get scan from transporter
        this.item.setMolecules(transporterController.getTransporterScan(sender));

        if (linksSent >= senderUser.getPricePlan().getMaxItems()){
            throw new TransporterException("Sender max items / day is reached! Please upgrade...");
        }

        if (!this.receiver.itemFits(this.item) && !this.sender.itemFits(this.item)){
            throw new TransporterException("Item doesn't fit in transporter!!");
        }

        this.linkStatus = LinkStatus.LINKED;
    }

    public void sendLink(){
        if (this.sender.equals(this.receiver)){
            throw new TransporterException("Receiver and sender can't be the same!");
        }
        if (itemsSet()){
            if (transporterController.getTransporterStatus(this.receiver)){
                transporterController.sendItemInTransporter(this.sender);
                this.linkStatus = LinkStatus.SENT;
            } else {
                throw new TransporterException("Destination unavailable!");
            }
        } else {
            throw new TransporterException("Destination unavailable!");
        }
    }

    public String getId(){
        return this.id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Transporter getSender() {
        return sender;
    }

    public LinkStatus getLinkStatus() {
        return linkStatus;
    }

    public User getSenderUser() {
        return senderUser;
    }

    private boolean itemsSet(){
        return !Stream.of( this.senderUser, this.receiverUser, this.sender, this.receiver, this.linkStatus, this.item).allMatch(Objects::isNull);
    }
}
