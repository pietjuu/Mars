package be.howest.ti.mars.logic.domain.notifications;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    @Test
    void testToString() {
        Notification notification = new Notification("Hey", LocalDateTime.of(2011,10,1, 10 , 5), "hey",  LocalDateTime.of(2011,11,1, 10 , 5));

        String result =  "Notification: \n" +
               "title:" + "Hey" + "\n" +
               "time:" + LocalDateTime.of(2011,11,1, 10 , 5) + "\n" +
               "message" + "hey" + "\n";

        assertEquals(result, notification.toString());
    }

    @Test
    void testSystemNotification(){
        SystemNotification systemNotification = new SystemNotification("hey", 20, "hallo");

        assertEquals("hallo", systemNotification.getMessage());
    }
}