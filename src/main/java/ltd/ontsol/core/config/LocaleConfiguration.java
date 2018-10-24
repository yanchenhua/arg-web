package ltd.ontsol.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocaleConfiguration {
    private static Logger logger = LoggerFactory.getLogger(LocaleConfiguration.class);

    @Bean(name = "localeResolver")
    public LocaleResolver myLocaleResolver() {
        logger.info("#####cookieLocaleResolver---create");

        LocaleResolver myLocaleResolver = new LocaleResolver();
//        myLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        logger.info("#####cookieLocaleResolver:");
        return myLocaleResolver;

    }
}