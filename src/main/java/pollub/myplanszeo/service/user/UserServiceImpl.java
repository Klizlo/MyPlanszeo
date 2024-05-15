package pollub.myplanszeo.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pollub.myplanszeo.model.User;
import pollub.myplanszeo.repository.UserRepository;

//Tydzień 7, Zasada Otwarty/Zamknięty 2
//Klasa implmentująca interfejs UserService
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
//Koniec, Tydzień 7, Zasada Otwarty/Zamknięty 2