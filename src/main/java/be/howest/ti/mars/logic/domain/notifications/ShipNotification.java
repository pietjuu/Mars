package be.howest.ti.mars.logic.domain.notifications;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;

import java.time.LocalDateTime;

public class ShipNotification extends Notification{

    private final User receiver;

    public ShipNotification(User sender, User receiver, Transporter receiverTransporter, Item item) {
        super("", 1, "");
        super.setMessage(generateMessage(sender, receiver, receiverTransporter));
        super.setTitle(generateTitle(sender, receiver, item));
        this.receiver = receiver;
    }

    public ShipNotification(String title, LocalDateTime expireTime, String message, LocalDateTime created, User receiver){
        super(title, expireTime, message, created);
        this.receiver = receiver;
    }

    public User getReceiver() {
        return receiver;
    }

    private String generateTitle(User sender, User receiver, Item item){
        return item.getName() + " from " + sender.getFirstname() + " " + sender.getLastname() + " sent to " + receiver.getFirstname() + " " + receiver.getLastname();
    }

    private String generateMessage(User sender, User receiver, Transporter receiverTransporter){
        return "Status: DELIVERED \nSent to " + receiverTransporter.getName() + " for " + receiver.getFirstname() + " " + receiver.getLastname() + "\n " + "Sent from " + receiverTransporter.getName() + " by " + sender.getFirstname() + " " + sender.getLastname();
    }
}
