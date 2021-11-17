package ctdi.daggermultiprofile.config;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {
    @Provides
    @MainScope
    public static String profile() {
        return System.getenv("profile");
    }
}
