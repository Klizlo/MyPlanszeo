package pollub.myplanszeo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.core.userdetails.UserDetailsService;
import pollub.myplanszeo.service.LoggingUserDetailsService;

public class UserDetailsServiceDecoratorBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof UserDetailsService)) {
            return bean;
        }

        if (bean instanceof UserDetailsService) {
            return bean;
        }

        return new LoggingUserDetailsService((UserDetailsService) bean);
    }
}
