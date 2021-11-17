package ctdi.daggermultiprofile.component;

public class MetricsPublisher {
    private String metricsServerUrl;

    public MetricsPublisher(String metricsServerUrl) {
        this.metricsServerUrl = metricsServerUrl;
    }

    public void publish() {
        while(true) {
            System.out.println(String.format("Metrics published to %s", metricsServerUrl));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
