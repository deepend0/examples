package ctdi.daggermultiprofile;

import java.util.Map;

public class ApplicationProperties {
    private Map<String, String> authInfo;

    private String databaseServer;

    private String databaseUrl;

    private String metricsServerUrl;

    public Map<String, String> getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(Map<String, String> authInfo) {
        this.authInfo = authInfo;
    }

    public String getDatabaseServer() {
        return databaseServer;
    }

    public void setDatabaseServer(String databaseServer) {
        this.databaseServer = databaseServer;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getMetricsServerUrl() {
        return metricsServerUrl;
    }

    public void setMetricsServerUrl(String metricsServerUrl) {
        this.metricsServerUrl = metricsServerUrl;
    }
}
