package ctdi.daggermultiprofile.config.dev;

import ctdi.daggermultiprofile.ConsoleCrudApplication;
import ctdi.daggermultiprofile.config.CommonModule;
import ctdi.daggermultiprofile.config.ProfileScope;
import dagger.Subcomponent;

@Subcomponent(modules = {CommonModule.class, DevelopmentModule.class})
@ProfileScope
public interface DevelopmentSubcomponent {
    ConsoleCrudApplication application();

    @Subcomponent.Builder
    interface Builder {
        DevelopmentSubcomponent build();
    }
}
