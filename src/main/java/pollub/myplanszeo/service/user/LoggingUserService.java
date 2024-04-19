package pollub.myplanszeo.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pollub.myplanszeo.model.User;


//Tydzień 3, Wzorzec Decorator 1
//Klasa ta ma za zadanie logowanie czy użytkownik poprawnie zalogował się do serwisu
//Tydzień 7, Zasada Otwarty/Zamknięty 2
//Klasa implmentująca interfejs UserService
@RequiredArgsConstructor
@Slf4j
public class LoggingUserService implements UserService {

    private final UserService userService;

    @Override
    public User getUserById(Long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User addUser(User user) {
        User added = userService.addUser(user);
        log.info("User with email {} was added", user.getEmail());
        return added;
    }
}
//Koniec, Tydzień 7, Zasada Otwarty/Zamknięty 2
//Koniec, Tydzień 3, Wzorzec Decorator
