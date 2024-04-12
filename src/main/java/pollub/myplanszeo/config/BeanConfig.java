package pollub.myplanszeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import pollub.myplanszeo.service.BoardGameListService;
import pollub.myplanszeo.service.BoardGameListServiceImpl;
import pollub.myplanszeo.service.UserService;
import pollub.myplanszeo.service.UserServiceImpl;

@Configuration
public class BeanConfig {

    @Bean
    public UserService userDetailsServiceDelegate() {
        return new UserServiceImpl();
    }

    @Bean
    public UserServiceDecoratorBeanProcessor userServiceDecoratorBeanProcessor() {
        return new UserServiceDecoratorBeanProcessor();
    }

    @Bean
    public BoardGameListService boardGameListServiceDelegate() {
        return new BoardGameListServiceImpl();
    }

    @Bean
    public BoardGameListServiceBeanProcessor boardGameListServiceBeanProcessor() {
        return new BoardGameListServiceBeanProcessor();
    }

}
