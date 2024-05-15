package pollub.myplanszeo.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pollub.myplanszeo.exception.UnauthorizedException;

@Slf4j
@Aspect
@Component
public class BoardGameListControllerLogger {

    @Pointcut("execution(* pollub.myplanszeo.controller.BoardGameListController.*(..))")
    private void boardGameListEndpoints(){}

    @Before("boardGameListEndpoints()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        log.warn("User with ip address {} has sent a request to {}", request.getRemoteAddr(), methodName);
    }

    @AfterThrowing(value = "boardGameListEndpoints()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        if (ex instanceof UnauthorizedException) {
            log.error("Unauthorized attempt to access to {} by {}", methodName, request.getRemoteAddr());
        }

        throw ex;
    }

}
