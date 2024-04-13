package pollub.myplanszeo.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pollub.myplanszeo.model.BoardGameList;

//Tydzień 6, Wzorzec Template 1
//Klasa dziedzicząca po klasie NotificationService
//Klasa ta wysyła wiadomości email do użytkowników
@Service
public class EmailNotificationService extends NotificationService {

    @Autowired
    private JavaMailSender mailSender;
    private SimpleMailMessage message;
    private BoardGameList boardGameList;
    @Value("{spring.mail.username}")
    private String emailSender;


    @Override
    protected void prepareNotification() {
        message.setFrom(emailSender);
        message.setTo(boardGameList.getUser().getEmail());
        message.setText("Your list was edited");
        message.setSubject("Your list was edited");
    }

    @Override
    protected void sendNotification() {
        try{
            mailSender.send(message);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Tydzień 6, Wzorzec Observer 1
    //Po powiadomieniu o zmianie obiektu, wysyła powiadomienie
    @Override
    public void update(Object arg) {
        message = new SimpleMailMessage();
        boardGameList = (BoardGameList) arg;
        send();
    }
    //Koniec, Tydzień 6, Wzorzec Observer 1
}
//Koniec, Tydzień 6, Wzorzec Template 1