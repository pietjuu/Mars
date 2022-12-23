package be.howest.ti.mars.logic.domain.link;

import be.howest.ti.mars.logic.controller.TransporterController;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.items.ItemStatus;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;
import be.howest.ti.mars.logic.exceptions.TransporterException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Link class
 */
public class Link {

    private final String id;
    private User senderUser;
    private User receiverUser;
    private Transporter sender;
    private Transporter receiver;
    private LinkStatus linkStatus;
    private final TransporterController transporterController = new TransporterController();
    private Item item;

    /**
     * Default constructor
     * @param sender {@link Transporter}
     */
    public Link(Transporter sender) {
        this.id = UUID.randomUUID().toString();
        this.sender = sender;
        this.linkStatus = LinkStatus.INITIALIZED;
    }

    /**
     * Import constructor
     * @param uuid String
     * @param senderUser {@link User} sender
     * @param receiverUser {@link User} receiver
     * @param sender {@link Transporter} sender
     * @param receiver {@link Transporter} receiver
     * @param linkStatus {@link LinkStatus} enum
     * @param item {@link Item} item
     */
    public Link(String uuid, User senderUser, User receiverUser, Transporter sender, Transporter receiver, LinkStatus linkStatus, Item item){
        this.id = uuid;
        this.sender = sender;
        this.receiver = receiver;
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
        this.linkStatus = linkStatus;
        this.item = item;
    }

    /**
     * Connect the link with the receiver transporter.
     * This will check if the sender {@link User} not has reached his daily maximum
     * Also will check if the item fits in both {@link Transporter}
     * @param senderUser {@link User} sender
     * @param sender {@link Transporter} sender
     * @param receiverUser {@link User} receiver
     * @param receiver {@link Transporter} transporter
     * @param itemName {@link Item} item
     * @param linksSent amount of items already sent by the user
     */
    public void connectLink(User senderUser, Transporter sender,  User receiverUser, Transporter receiver, String itemName, int linksSent){
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
        this.sender = sender;
        this.receiver = receiver;
        this.item = new Item(itemName);

        // Get scan from transporter
        this.item.setMolecules(transporterController.getTransporterScan(sender));
        if (linksSent >= senderUser.getPricePlan().getMaxItems() && senderUser.getPricePlan().getMaxItems() != -1){
            throw new TransporterException("Sender max items / day is reached! Please upgrade...");
        }

        if (!this.receiver.itemFits(this.item) || !this.sender.itemFits(this.item)){
            throw new TransporterException("Item doesn't fit in transporter!!");
        }

        this.linkStatus = LinkStatus.LINKED;
    }

    /**
     * Send the item in the transporter.
     * Checks if all params are set, and if receiver transporter is ready.
     */
    public void sendLink(){
        if (this.sender.equals(this.receiver)){
            throw new TransporterException("Receiver and sender can't be the same!");
        }
        if (!itemsSet()){
            if (transporterController.getTransporterStatus(this.receiver)){
                transporterController.sendItemInTransporter(this.sender);

                this.setSendTime(LocalDateTime.now());

                this.linkStatus = LinkStatus.SENT;
                this.getItem().setStatus(ItemStatus.COMPLETED);
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

    public Transporter getReceiver() {
        return receiver;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public void setSendTime(LocalDateTime localDateTime){
        // Send and received time is the same because IOT Device doesn't provide a reception time, because it's out of scope.
        this.item.setSendTime(localDateTime);
        this.item.setReceivedTime(localDateTime);
    }

    /**
     * Check if all params are set
     * @return boolean
     */
    private boolean itemsSet(){
        return !Stream.of( this.senderUser, this.receiverUser, this.sender, this.receiver, this.linkStatus, this.item).allMatch(Objects::nonNull);
    }
}
