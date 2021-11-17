package ctdi.daggermultiprofile.component;

import javax.inject.Inject;
import java.util.Map;
import java.util.Objects;

public class StaticAuthenticationComponent implements AuthenticationComponent{

    private Map<String, String> authInfo;

    @Inject
    public StaticAuthenticationComponent(Map<String, String> authInfo) {
        this.authInfo = authInfo;
    }

    @Override
    public boolean authenticate(String username, String password) {
        if(Objects.equals(authInfo.get(username), password)) {
            return true;
        }
        return false;
    }
}
