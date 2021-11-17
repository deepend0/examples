package ctdi.daggermultiprofile.config.dev;

import ctdi.daggermultiprofile.config.ProfileScope;
import ctdi.daggermultiprofile.repository.Datasource;
import ctdi.daggermultiprofile.repository.InMemoryDbDatasource;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DevelopmentModule {
    @Provides
    @ProfileScope
    public static Datasource datasource() {
        return new InMemoryDbDatasource();
    }
}
