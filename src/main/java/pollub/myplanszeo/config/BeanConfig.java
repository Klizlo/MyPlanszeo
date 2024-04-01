package pollub.myplanszeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserDetailsServiceDecoratorBeanProcessor userDetailsServiceDecoratorBeanProcessor() {
        return new UserDetailsServiceDecoratorBeanProcessor();
    }

    @Bean
    public BoardGameListServiceBeanProcessor boardGameListServiceBeanProcessor() {
        return new BoardGameListServiceBeanProcessor();
    }

}
