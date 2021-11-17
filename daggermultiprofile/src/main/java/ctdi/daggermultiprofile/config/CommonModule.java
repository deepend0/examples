package ctdi.daggermultiprofile.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ctdi.daggermultiprofile.ApplicationProperties;
import ctdi.daggermultiprofile.component.AuthenticationComponent;
import ctdi.daggermultiprofile.component.MetricsPublisher;
import ctdi.daggermultiprofile.component.StaticAuthenticationComponent;
import dagger.Binds;
import dagger.BindsOptionalOf;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CommonModule {
    @Provides
    @ProfileScope
    public static ApplicationProperties applicationProperties(String profile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            return objectMapper.readValue(ClassLoader.getSystemResource("application-" + profile + ".yaml"), ApplicationProperties.class);
        } catch (Exception e) {
            throw new IllegalStateException("Application config file couldn't be loaded");
        }
    }

    @Provides
    @ProfileScope
    public static StaticAuthenticationComponent staticAuthenticationComponent(ApplicationProperties applicationProperties) {
        return new StaticAuthenticationComponent(applicationProperties.getAuthInfo());
    }

    @Binds
    @ProfileScope
    public abstract AuthenticationComponent authenticationComponent(StaticAuthenticationComponent staticAuthenticationComponent);

    @BindsOptionalOf
    abstract MetricsPublisher metricsPublisherOpt();
}
