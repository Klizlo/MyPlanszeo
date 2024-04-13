package pollub.myplanszeo.observer;

import pollub.myplanszeo.model.BoardGameList;

//Tydzień 6, Wzorzec Observer 1
// Interfejs dla klas które są obserwoowalne
public interface Observable {
    void notifyAllObservers(BoardGameList boardGameList);
}
//Koniec, Tydzień 6, Wzorzec Observer 1