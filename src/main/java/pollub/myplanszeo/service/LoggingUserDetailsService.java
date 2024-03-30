package pollub.myplanszeo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


//Tydzień 3, Wzorzec Decorator 1
//Klasa ta ma za zadanie logowanie czy użytkownik poprawnie zalogował się do serwisu
@RequiredArgsConstructor
@Slf4j
public class LoggingUserDetailsService implements UserDetailsService {

    private final UserDetailsService userDetailsService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        log.warn("User {} log in", userDetails.getUsername());

        return userDetails;
    }
}
//Koniec, Tydzień 3, Wzorzec Decorator
