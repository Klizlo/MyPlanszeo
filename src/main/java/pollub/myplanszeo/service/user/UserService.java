package pollub.myplanszeo.service.user;

import pollub.myplanszeo.model.User;

//Tydzień 7, Zasada Otwarty/Zamknięty 2
//Interfejs dla klas serwisowych dla zarządzaniem użytkownikami
public interface UserService {

    User getUserById(Long userId);
    User addUser(User user);

}
//Koniec, Tydzień 7, Zasada Otwarty/Zamknięty 2