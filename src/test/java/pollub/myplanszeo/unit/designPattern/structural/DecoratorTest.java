package pollub.myplanszeo.unit.designPattern.structural;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.springframework.security.core.userdetails.UserDetails;
import pollub.myplanszeo.config.security.CustomUserDetails;
import pollub.myplanszeo.config.test.MemoryLogger;
import pollub.myplanszeo.model.User;
import pollub.myplanszeo.service.LoggingUserService;
import pollub.myplanszeo.service.UserService;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DecoratorTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private LoggingUserService loggingUserService;

    private static MemoryLogger memoryLogger;

    @BeforeEach
    public void setup() {
        Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        memoryLogger = new MemoryLogger();
        memoryLogger.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(memoryLogger);
        memoryLogger.start();
    }

    @AfterEach
    public void cleanUp() {
        memoryLogger.reset();
        memoryLogger.stop();
    }

    @Test
    public void givenUser_whenAddUser_DisplayLog() {

        User user = new User(1L, "adam.nowak@poczta.pl", "Asy827b%e36uq@", null);

        when(userService.addUser(user)).thenReturn(user);

        loggingUserService.addUser(user);

        assertThat(memoryLogger.search("User with email " + user.getEmail() + " was added", Level.INFO).size()).isEqualTo(1);

    }

}
