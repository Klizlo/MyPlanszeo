package pollub.myplanszeo.service.notification;

import pollub.myplanszeo.observer.Observer;

//Tydzień 6, Wzorzec Template 1, Wzorzec Observer 1
//Szablon do tworzenia i wysyłania powiadomień dla użytkownika
//Klas implementuje również intefejs Observer, obserwuje obiekt
// i w razie poinformowaia go o zmianie obiektu, wysyła powiadomienie
public abstract class NotificationService implements Observer {
    protected void send(){
        prepareNotification();
        sendNotification();
    }

    protected abstract void prepareNotification();
    protected abstract void sendNotification();
}
//Koniec, Tydzień 6, Wzorzec Template 1, Wzorzec Observer 1