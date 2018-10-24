package ltd.ontsol.core.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cn40580 at 2018-07-03 1:16 PM.
 */
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("800MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("1000MB");
        return factory.createMultipartConfig();
    }

}
