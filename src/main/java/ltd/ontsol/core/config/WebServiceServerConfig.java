package ltd.ontsol.core.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import ltd.ontsol.web.soap.staff.StaffSoapWebService;

@Configuration
public class WebServiceServerConfig {

    @Bean(name = "servletRegistrationBean")
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public StaffSoapWebService staffSoapWebService() {
        return new StaffSoapWebService();
    }

    @DependsOn("servletRegistrationBean")
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(this.springBus(), this.staffSoapWebService());
        endpoint.publish("");
        return endpoint;
    }
}
