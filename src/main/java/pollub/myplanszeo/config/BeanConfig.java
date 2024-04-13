package pollub.myplanszeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pollub.myplanszeo.service.boardgamelist.BoardGameListService;
import pollub.myplanszeo.service.boardgamelist.BoardGameListServiceImpl;
import pollub.myplanszeo.service.user.UserService;
import pollub.myplanszeo.service.user.UserServiceImpl;

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
