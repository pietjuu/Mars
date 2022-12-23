package be.howest.ti.mars.logic.domain.link;

import be.howest.ti.mars.logic.data.InMemoryRepository;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.location.TypeOfLocation;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;
import be.howest.ti.mars.logic.exceptions.TransporterException;
import be.howest.ti.mars.logic.utils.MockInformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkTest {

    @Test
    void constructorDefault(){
        Link l1 = new Link(new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter1.thibo.cloud/"));

        assertEquals(LinkStatus.INITIALIZED, l1.getLinkStatus());
        assertNotNull(l1.getId());
    }

    @Test
    void constructorImport(){
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        Link l1 = new Link("TL-T", inMemoryRepository.getUser("T-1"), inMemoryRepository.getUser("T-2"), inMemoryRepository.getTransporter("TT-4"), inMemoryRepository.getTransporter("TT-5"), LinkStatus.SENT, new Item("Apple", MockInformation.getMoleculesSummary()));

        assertEquals(LinkStatus.SENT, l1.getLinkStatus());
        assertEquals("Apple", l1.getItem().getName());
        assertEquals("T-1", l1.getSenderUser().getId());
        assertEquals("TT-4", l1.getSender().getId());
    }

    @Test
    void connectLink(){
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        User senderUser = inMemoryRepository.getUser("T-1");
        User receiverUser = inMemoryRepository.getUser("T-2");

        Transporter sender = inMemoryRepository.getTransporter("TT-4");
        Transporter receiver = inMemoryRepository.getTransporter("TT-5");

        Link link = new Link(sender);
        link.connectLink(senderUser, sender, receiverUser, receiver, "Apple", senderUser.getPricePlan().getMaxItems());

        assertEquals(LinkStatus.LINKED, link.getLinkStatus());
    }

    @Test
    void connectLinkMaxItems(){
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        User senderUser = inMemoryRepository.getUser("T-4");
        User receiverUser = inMemoryRepository.getUser("T-2");

        Transporter sender = inMemoryRepository.getTransporter("TT-4");
        Transporter receiver = inMemoryRepository.getTransporter("TT-5");

        Link link = new Link(sender);
        assertThrows(TransporterException.class, () -> link.connectLink(senderUser, sender, receiverUser, receiver, "Apple", 15));

    }

    @Test
    void connectLinkFits(){
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        User senderUser = inMemoryRepository.getUser("T-1");
        User receiverUser = inMemoryRepository.getUser("T-2");

        Transporter sender = inMemoryRepository.getTransporter("TT-4");
        sender.setSize(new Size(0.1f, 0.1f, 0.1f));

        Transporter receiver = inMemoryRepository.getTransporter("TT-5");

        Link link = new Link(sender);
        assertThrows(TransporterException.class, () -> link.connectLink(senderUser, sender, receiverUser, receiver, "Apple", 1));

    }

    @Test
    void connectLinkFits2(){
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        User senderUser = inMemoryRepository.getUser("T-1");
        User receiverUser = inMemoryRepository.getUser("T-2");

        Transporter sender = inMemoryRepository.getTransporter("TT-4");
        Transporter receiver = inMemoryRepository.getTransporter("TT-5");
        receiver.setSize(new Size(0.1f, 0.1f, 0.1f));

        Link link = new Link(sender);
        assertThrows(TransporterException.class, () -> link.connectLink(senderUser, sender, receiverUser, receiver, "Apple", 1));

    }

    @Test
    void sendLink(){
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        User senderUser = inMemoryRepository.getUser("T-1");
        User receiverUser = inMemoryRepository.getUser("T-2");

        Transporter sender = inMemoryRepository.getTransporter("TT-4");
        Transporter receiver = inMemoryRepository.getTransporter("TT-5");

        Link link = new Link(sender);
        link.connectLink(senderUser, sender, receiverUser, receiver, "Apple", senderUser.getPricePlan().getMaxItems());

        assertEquals(LinkStatus.LINKED, link.getLinkStatus());

        link.sendLink();
    }

    @Test
    void sendLinkErrorSame(){
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        User senderUser = inMemoryRepository.getUser("T-1");

        Transporter sender = inMemoryRepository.getTransporter("TT-4");

        Link link = new Link(sender);
        link.connectLink(senderUser, sender, senderUser, sender, "Apple", senderUser.getPricePlan().getMaxItems());

        assertEquals(LinkStatus.LINKED, link.getLinkStatus());

        assertThrows(TransporterException.class, link::sendLink);
    }

    @Test
    void sendLinkErrorDestinationUnavailable(){
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        User senderUser = inMemoryRepository.getUser("T-1");

        Transporter sender = inMemoryRepository.getTransporter("TT-4");
        Transporter receiver = inMemoryRepository.getTransporter("TT-5");

        Link link = new Link(sender);
        link.connectLink(senderUser, sender, senderUser, receiver, "Apple", senderUser.getPricePlan().getMaxItems());

        assertEquals(LinkStatus.LINKED, link.getLinkStatus());

        link.setItem(null);

        assertThrows(TransporterException.class, link::sendLink);
    }

    @Test
    void sendLinkErrorDestinationUnavailable2(){
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        User senderUser = inMemoryRepository.getUser("T-1");

        Transporter sender = inMemoryRepository.getTransporter("TT-4");
        Transporter receiver = inMemoryRepository.getTransporter("TT-2");
        receiver.setSize(new Size(100f, 100f, 100f));

        Link link = new Link(sender);
        assertThrows(TransporterException.class, () -> link.connectLink(senderUser, sender, senderUser, receiver, "Apple", senderUser.getPricePlan().getMaxItems()));
    }
}