package be.howest.ti.mars.logic.domain.notifications;

import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;

public class ShipNotification extends Notification{

    private final User sender;
    private final User receiver;
    private final Transporter receiverTransporter;

    public ShipNotification(String title, int daysActive, String message, User sender, User receiver, Transporter receiverTransporter) {
        super(title, daysActive, message);
        this.sender = sender;
        this.receiver = receiver;
        this.receiverTransporter = receiverTransporter;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public Transporter getReceiverTransporter() {
        return receiverTransporter;
    }
}
