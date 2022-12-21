package be.howest.ti.mars.logic.domain.notifications;

import java.time.LocalDateTime;

public class SystemNotification extends Notification{
    public SystemNotification(String title, int daysActive, String message) {
        super(title, daysActive, message);
    }

    public SystemNotification(String title, LocalDateTime expireTime, String message, LocalDateTime created){
        super(title, expireTime, message, created);
    }
}
