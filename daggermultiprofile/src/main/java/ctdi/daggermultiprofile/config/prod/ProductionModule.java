package ctdi.daggermultiprofile.config.prod;

import ctdi.daggermultiprofile.ApplicationProperties;
import ctdi.daggermultiprofile.component.MetricsPublisher;
import ctdi.daggermultiprofile.config.ProfileScope;
import ctdi.daggermultiprofile.repository.Datasource;
import ctdi.daggermultiprofile.repository.DbmsServerDatasource;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ProductionModule {
    @Provides
    @ProfileScope
    public static Datasource datasource(ApplicationProperties applicationProperties) {
        return new DbmsServerDatasource(applicationProperties.getDatabaseUrl());
    }

    @Provides
    @ProfileScope
    public static MetricsPublisher metricsPublisher(ApplicationProperties applicationProperties) {
        return new MetricsPublisher(applicationProperties.getMetricsServerUrl());
    }
}
