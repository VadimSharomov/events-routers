package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan("dao,model,service,configuration,controller")
@PropertySource(value = {"classpath:application.properties"})
@EnableWebMvc
public class AppConfig{

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setPrefix("/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(1);
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }
}
