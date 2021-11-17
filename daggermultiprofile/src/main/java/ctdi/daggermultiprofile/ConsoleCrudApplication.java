package ctdi.daggermultiprofile;

import ctdi.daggermultiprofile.component.AuthenticationComponent;
import ctdi.daggermultiprofile.component.MetricsPublisher;
import ctdi.daggermultiprofile.repository.DatabaseRepository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleCrudApplication {
    private AuthenticationComponent authenticationComponent;
    private DatabaseRepository databaseRepository;
    private Optional<MetricsPublisher> metricsPublisher;

    @Inject
    public ConsoleCrudApplication(AuthenticationComponent authenticationComponent, DatabaseRepository databaseRepository, Optional<MetricsPublisher> metricsPublisher) {
        this.authenticationComponent = authenticationComponent;
        this.metricsPublisher = metricsPublisher;
        this.databaseRepository = databaseRepository;
    }

    public static void main(String[] args) {
        ProfileResolver.getApplication().run();
    }

    public void run() {
        if (metricsPublisher.isPresent()) {
            new Thread(metricsPublisher.get()::publish).start();
        } else {
           System.out.println("Metrics publisher is not available.");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        boolean authenticationResult = authenticationComponent.authenticate(username, password);
        if(!authenticationResult) {
            System.out.println("Authentication failed.");
            return;
        }
        System.out.println("Commands:\nsave <record> (record: id:1,field1:a,field2,b)\ndelete <id>\nget <id>\nexit");
        while(true) {
            System.out.println("Enter your command:");
            String input = scanner.nextLine();
            String [] parts = input.split(" ");
            if("save".equals(parts[0])) {
                save(parts[1]);
            } else if("delete".equals(parts[0])) {
                delete(parts[1]);
            } else if("get".equals(parts[0])) {
                System.out.println(get(parts[1]));
            } else if("exit".equals(parts[0])) {
                System.out.println("Quitting the app.");
                break;
            } else {
                System.out.println(("Unknown command"));
            }
        }
    }

    public String get(String id) {
        Map<String, String> values = databaseRepository.get(id);
        if(values == null) {
            System.out.println(String.format("Record with id %s not found.", id));
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for(String key : values.keySet()) {
            if(isFirst) {
                isFirst = false;
            } else {
                sb.append(",");
            }
            sb.append(key + ":" + values.get(key));
        }
        return sb.toString();
    }

    public void save(String valuesStr) {
        String [] fields = valuesStr.split(",");
        Map<String, String> values = new HashMap<>();
        for(String field : fields) {
            String [] parts = field.split(":");
            values.put(parts[0], parts[1]);
        }
        String id = values.get("id");
        if(id == null) {
            System.out.println("ERROR: Id field is missing.");
            return;
        }
        databaseRepository.save(id, values);
        System.out.println(String.format("Record with id %s saved.", id));
    }

    public void delete(String id) {
        boolean result = databaseRepository.delete(id);
        if(result) {
            System.out.println(String.format("Record with id %s deleted.", id));
        } else {
            System.out.println(String.format("Record with id %s not found.", id));
        }
    }
}
