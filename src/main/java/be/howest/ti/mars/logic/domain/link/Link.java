package be.howest.ti.mars.logic.domain.link;

import be.howest.ti.mars.logic.controller.TransporterController;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.UUID;

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

    public void connectLink(User senderUser, Transporter sender,  User receiverUser, Transporter receiver, String itemName){
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
        this.sender = sender;
        this.receiver = receiver;
        this.item = new Item(itemName);

        // Get scan from transporter
        this.item.setMolecules(transporterController.getTransporterScan(sender));

        this.linkStatus = LinkStatus.LINKED;
    }

    public Transporter getSender() {
        return sender;
    }

    public void setSender(Transporter sender) {
        this.sender = sender;
    }

    public Transporter getReceiver() {
        return receiver;
    }

    public void setReceiver(Transporter receiver) {
        this.receiver = receiver;
    }

    public LinkStatus getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(LinkStatus linkStatus) {
        this.linkStatus = linkStatus;
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
}
