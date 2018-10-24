package ltd.ontsol.core.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by cn40580 at 2018-06-25 9:09 PM.
 */
@Component
public class ErrorPageRegister implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
        //illegalArg
        ErrorPage illegalArg = new ErrorPage(IllegalArgumentException.class, "/error/illegalArg.html");
        registry.addErrorPages(e404, e500, illegalArg);
    }
}