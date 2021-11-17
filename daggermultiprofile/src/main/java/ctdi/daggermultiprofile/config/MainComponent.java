package ctdi.daggermultiprofile.config;

import ctdi.daggermultiprofile.config.dev.DevelopmentSubcomponent;
import ctdi.daggermultiprofile.config.dev.DevelopmentSubModule;
import ctdi.daggermultiprofile.config.prod.ProductionSubModule;
import ctdi.daggermultiprofile.config.prod.ProductionSubcomponent;
import dagger.Component;

@Component(modules = {ProfileModule.class, DevelopmentSubModule.class, ProductionSubModule.class})
@MainScope
public interface MainComponent {
    String profile();

    DevelopmentSubcomponent.Builder developmentComponentBuilder();

    ProductionSubcomponent.Builder productionComponentBuilder();
}
