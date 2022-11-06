package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.Quote;
import io.netty.util.internal.StringUtil;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

class MarsH2RepositoryTest {
    private static final String URL = "jdbc:h2:./db-05";

    @BeforeEach
    void setupTestSuite() {
        Repositories.shutdown();
        JsonObject dbProperties = new JsonObject(Map.of("url",URL,
                "username", "",
                "password", "",
                "webconsole.port", 9000 ));
        Repositories.configure(dbProperties);
    }

    @Test
    void getQuote() {
        // Arrange
        int id = 1;

        // Act
        Quote quote = Repositories.getH2Repo().getQuote(id);

        // Assert
        Assertions.assertTrue(quote != null && !StringUtil.isNullOrEmpty(quote.getValue()));
    }

    @Test
    void updateQuote() {
        // Arrange
        int id = 1;
        String quoteValue = "some value";

        // Act
        Quote quote = Repositories.getH2Repo().updateQuote(id, quoteValue);

        // Assert
        Assertions.assertNotNull(quote);
        Assertions.assertEquals(quoteValue, quote.getValue());
    }

    @Test
    void insertQuote() {
        // Arrange
        String quoteValue = "some value";

        // Act
        Quote quote = Repositories.getH2Repo().insertQuote(quoteValue);

        // Assert
        Assertions.assertNotNull(quote);
        Assertions.assertEquals(quoteValue, quote.getValue());
    }

    @Test
    void deleteQuote() {
        // Arrange
        int id = 1;

        // Act
        Repositories.getH2Repo().deleteQuote(id);

        // Assert
        Assertions.assertNull(Repositories.getH2Repo().getQuote(id));
    }

}
