package ctdi.daggermultiprofile;

import ctdi.daggermultiprofile.config.DaggerMainComponent;
import ctdi.daggermultiprofile.config.MainComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProfileResolver {
    private static Map<String, Supplier<ConsoleCrudApplication>> APPLICATION_SUPPLIER_MAP = new HashMap<>();
    private static MainComponent mainComponent = DaggerMainComponent.create();

    static {
        Supplier<ConsoleCrudApplication> developmentApplicationSupplier = () ->
                mainComponent.developmentComponentBuilder().build().application();
        Supplier<ConsoleCrudApplication> productionApplicationSupplier = () ->
                mainComponent.productionComponentBuilder().build().application();
        APPLICATION_SUPPLIER_MAP.put("development", developmentApplicationSupplier);
        APPLICATION_SUPPLIER_MAP.put("production", productionApplicationSupplier);
    }

    public static ConsoleCrudApplication getApplication() {
        String profile = mainComponent.profile();
        return APPLICATION_SUPPLIER_MAP.get(profile).get();
    }
}
