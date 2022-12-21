package be.howest.ti.mars.logic.domain.notifications;

import java.time.LocalDateTime;

public class Notification {

    private String title;
    private final LocalDateTime time;
    private final LocalDateTime expireTime;
    private String message;

    protected Notification(String title, int daysActive, String message) {
        this.title = title;
        this.time = LocalDateTime.now();
        this.expireTime = this.time.plusDays(daysActive);
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    @Override
    public String toString() {
        return "Notification: \n" +
                "title:" + title + "\n" +
                "time:" + time + "\n" +
                "message" + message + "\n";
    }
}

