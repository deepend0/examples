package ctdi.daggermultiprofile.repository;

public class DbmsServerDatasource implements Datasource{
    private String url;

    public DbmsServerDatasource(String url) {
        this.url = url;
    }

    @Override
    public String type() {
        return "dbms server";
    }
}
