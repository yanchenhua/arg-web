package ltd.ontsol.core;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@ComponentScan("ltd.ontsol")
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements InitializingBean {

    private static Application instance;
    private final Logger LOG = LoggerFactory.getLogger(Application.class);
    @Inject
    private Environment env;

    /**
     * The main method starts the spring application.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {
        SpringApplication app = new SpringApplication(Application.class) {

        };
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);

        // Check if the selected profile has been set as argument.
        // if not the development profile will be added
        addDefaultProfile(app, source);
        app.run(args);
    }


    private static Optional<Application> get() {
        return Optional.ofNullable(instance);
    }

    /**
     * Set a default profile if it has not been set.
     *
     * @param app    the app
     * @param source the source
     */
    private static void addDefaultProfile(final SpringApplication app, final SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active")) {
//            app.setAdditionalProfiles(Profiles.LOCAL);
        }
    }

    public void init() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            LOG.warn("No Spring profile configured, running with default configuration");
        } else {
            LOG.info(String.format("Running with Spring profile(s) : %s", Arrays.toString(env.getActiveProfiles())));
        }
        Application.instance = this;
    }

    @Override
    public void afterPropertiesSet() throws IOException {
        init();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
