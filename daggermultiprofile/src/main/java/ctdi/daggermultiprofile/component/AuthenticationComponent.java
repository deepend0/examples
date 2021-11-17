package ctdi.daggermultiprofile.component;

public interface AuthenticationComponent {
    boolean authenticate(String username, String password);
}
