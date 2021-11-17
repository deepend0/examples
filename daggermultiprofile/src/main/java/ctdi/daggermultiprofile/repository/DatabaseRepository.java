package ctdi.daggermultiprofile.repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class DatabaseRepository {
    private Datasource datasource;

    private final Map<String, Map<String, String>> db = new HashMap<>();

    @Inject
    public DatabaseRepository(Datasource datasource) {
        this.datasource = datasource;
    }

    public void save(String id, Map<String, String> values) {
        System.out.println(String.format("Saving to %s database", datasource.type()));
        db.put(id, values);
    }

    public boolean delete(String id) {
        System.out.println(String.format("Deleting from %s database", datasource.type()));
        if(db.containsKey(id)) {
            db.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public Map<String, String> get(String id) {
        System.out.println(String.format("Retrieving from %s database", datasource.type()));
        return db.get(id);
    }
}
