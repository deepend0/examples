package ctdi.daggermultiprofile.repository;

public class InMemoryDbDatasource implements Datasource {
    @Override
    public String type() {
        return "in-memory";
    }
}
