package pollub.myplanszeo.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pollub.myplanszeo.config.security.CustomUserDetails;
import pollub.myplanszeo.model.User;

@Slf4j
@Aspect
@Component
public class UserControllerLogger {

    @Pointcut("execution(* pollub.myplanszeo.controller.UserController.*(..))")
    private void userEndpoints() {}

    @Before("userEndpoints()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        log.warn("User with ip address {} has sent a request to {}", request.getRemoteAddr(), methodName);
    }

    @AfterThrowing(pointcut = "execution(* org.springframework.security.authentication.AuthenticationManager.authenticate(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, BadCredentialsException ex) {
        String login = "";
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            Authentication authentication = (Authentication) args[0];
            login = (String) authentication.getPrincipal();
        }
        log.error("Failed login attempt for account {}", login);

        throw ex;
    }

}
