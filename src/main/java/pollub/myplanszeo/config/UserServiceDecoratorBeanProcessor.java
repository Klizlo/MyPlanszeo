package pollub.myplanszeo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import pollub.myplanszeo.service.LoggingUserService;
import pollub.myplanszeo.service.UserService;

public class UserServiceDecoratorBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof UserService)) {
            return bean;
        }

        if (bean instanceof LoggingUserService) {
            return bean;
        }

        return new LoggingUserService((UserService) bean);
    }
}
