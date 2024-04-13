package pollub.myplanszeo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import pollub.myplanszeo.flyweight.BoardGameListCache;
import pollub.myplanszeo.proxy.BoardGameListServiceProxy;
import pollub.myplanszeo.service.boardgamelist.BoardGameListService;
import pollub.myplanszeo.service.boardgamelist.BoardGameListServiceImpl;

public class BoardGameListServiceBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!(bean instanceof BoardGameListService)) {
            return bean;
        }

        if (bean instanceof BoardGameListServiceImpl) {
            return new BoardGameListCache((BoardGameListService) bean);
        }

        if (bean instanceof BoardGameListServiceProxy) {
            return bean;
        }

        return new BoardGameListServiceProxy((BoardGameListService) bean);
    }
}
