package pollub.myplanszeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserDetailsServiceDecoratorBeanProcessor beanProcessor() {
        return new UserDetailsServiceDecoratorBeanProcessor();
    }

}
