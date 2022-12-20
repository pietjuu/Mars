package be.howest.ti.mars.logic.domain.link;

import be.howest.ti.mars.logic.data.InMemoryRepository;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.location.TypeOfLocation;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;
import be.howest.ti.mars.logic.utils.MockInformation;
import org.jcodings.exception.TranscoderException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkTest {

    @Test
    void constructorDefault(){
        Link l1 = new Link(new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter1.thibo.cloud/"));

        assertEquals(LinkStatus.INITIALIZED, l1.getLinkStatus());
    }

    @Test
    void constructorImport(){
        InMemoryRepository inMemoryRepository = new InMemoryRepository();
        Link l1 = new Link("TL-T", inMemoryRepository.getUser("T-1"), inMemoryRepository.getUser("T-2"), inMemoryRepository.getTransporter("TT-4"), inMemoryRepository.getTransporter("TT-5"), LinkStatus.SENT, new Item("Apple", MockInformation.getMoleculesSummary()));

        assertEquals(LinkStatus.SENT, l1.getLinkStatus());
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
        User senderUser = inMemoryRepository.getUser("T-1");
        User receiverUser = inMemoryRepository.getUser("T-2");

        Transporter sender = inMemoryRepository.getTransporter("TT-4");
        Transporter receiver = inMemoryRepository.getTransporter("TT-5");

        Link link = new Link(sender);
        assertThrows(TranscoderException.class, () -> link.connectLink(senderUser, sender, receiverUser, receiver, "Apple", 1));

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
        assertThrows(TranscoderException.class, () -> link.connectLink(senderUser, sender, receiverUser, receiver, "Apple", 1));

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
        assertThrows(TranscoderException.class, () -> link.connectLink(senderUser, sender, receiverUser, receiver, "Apple", 1));

    }
}