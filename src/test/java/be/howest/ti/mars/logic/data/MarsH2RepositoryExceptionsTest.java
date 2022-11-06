package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.Quote;
import be.howest.ti.mars.logic.exceptions.RepositoryException;
import io.netty.util.internal.StringUtil;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Map;

class MarsH2RepositoryExceptionsTest {

    private static final String URL = "jdbc:h2:./db-05";

    @Test
    void getH2RepoWithNoDbFails() {
        // Arrange
        Repositories.shutdown();

        // Act + Assert
        Assertions.assertThrows(RepositoryException.class, Repositories::getH2Repo);
    }

    @Test
    void functionsWithSQLExceptionFailsNicely() {
        // Arrange
        int id = 1;
        JsonObject dbProperties = new JsonObject(Map.of("url",URL,
                "username", "",
                "password", "",
                "webconsole.port", 9000 ));
        Repositories.shutdown();
        Repositories.configure(dbProperties);
        MarsH2Repository repo = Repositories.getH2Repo();
        repo.cleanUp();

        // Act + Assert
        Assertions.assertThrows(RepositoryException.class, () -> repo.getQuote(id));
        Assertions.assertThrows(RepositoryException.class, () -> repo.deleteQuote(id));
        Assertions.assertThrows(RepositoryException.class, () -> repo.updateQuote(id, "update"));
    }


}
