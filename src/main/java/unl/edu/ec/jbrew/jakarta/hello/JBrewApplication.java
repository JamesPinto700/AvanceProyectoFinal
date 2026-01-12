package unl.edu.ec.jbrew.jakarta.hello;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class JBrewApplication extends Application {
    // Needed to enable Jakarta REST and specify path.
}
