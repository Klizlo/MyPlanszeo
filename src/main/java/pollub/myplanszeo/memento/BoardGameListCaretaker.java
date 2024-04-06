package pollub.myplanszeo.memento;

import java.util.ArrayDeque;
import java.util.Deque;

//Tydzień 5, Wzorzec Memento 1
//Klasa ta przechowuje kolejne stany listy gier podczas jej edycji
//Dzięki temu użytkonik będzie mógł cofnąć wprowadzane zmiany
public class BoardGameListCaretaker {

    final Deque<BoardGameListDtoMemento> mementos = new ArrayDeque<>();

    public BoardGameListDtoMemento getMemento() {
        return mementos.pop();
    }

    public void addMemento(BoardGameListDtoMemento memento) {
        mementos.push(memento);
    }
}
//Koniec, Tydzień 5, Wzorzec Memento 1