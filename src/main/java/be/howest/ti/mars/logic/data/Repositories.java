package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.exceptions.RepositoryException;
import io.vertx.core.json.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Repositories {
    private static MarsH2Repository h2Repo = null;
    private static InMemoryRepository memoryRepository = null;

    private Repositories() {
    }

    public static InMemoryRepository getInMemoryRepository(){
        if (memoryRepository == null){
            memoryRepository = new InMemoryRepository();
        }
        return memoryRepository;
    }

    public static MarsH2Repository getH2Repo(){
        if (h2Repo == null) {
            try {
                Path config = Paths.get("././././././././conf/config.json");
                String jsonStr = Files.readString(config.toAbsolutePath());
                JsonObject jsonObject = new JsonObject(jsonStr);

                configure(jsonObject.getJsonObject("db"));
            } catch (IOException e){
                throw new RepositoryException("Can't load config for DB");
            }

        }


        return h2Repo;
    }

    public static void configure(JsonObject dbProps) {
        h2Repo = new MarsH2Repository(dbProps.getString("url"),
                dbProps.getString("username"),
                dbProps.getString("password"),
                dbProps.getInteger("webconsole.port"));
    }

    public static void shutdown() {
        if (h2Repo != null)
            h2Repo.cleanUp();

        h2Repo = null;
    }
}
