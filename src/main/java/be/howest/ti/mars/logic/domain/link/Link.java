package be.howest.ti.mars.logic.domain.link;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.transporter.Transporter;

import java.util.UUID;

public class Link {

    private final String id;
    private Transporter sender;
    private Transporter receiver;
    private LinkStatus linkStatus;
    private Item item;

    public Link(Transporter sender) {
        this.id = UUID.randomUUID().toString();
        this.sender = sender;
    }

    public Link(String uuid, Transporter sender, Transporter receiver, LinkStatus linkStatus, Item item){
        this.id = uuid;
        this.sender = sender;
        this.receiver = receiver;
        this.linkStatus = linkStatus;
        this.item = item;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
