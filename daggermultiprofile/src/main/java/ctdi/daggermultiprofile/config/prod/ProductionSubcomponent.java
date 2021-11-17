package ctdi.daggermultiprofile.config.prod;

import ctdi.daggermultiprofile.ConsoleCrudApplication;
import ctdi.daggermultiprofile.config.CommonModule;
import ctdi.daggermultiprofile.config.ProfileScope;
import dagger.Subcomponent;

@Subcomponent(modules = {CommonModule.class, ProductionModule.class})
@ProfileScope
public interface ProductionSubcomponent {
    ConsoleCrudApplication application();

    @Subcomponent.Builder
    interface Builder {
        ProductionSubcomponent build();
    }
}
