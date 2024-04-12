package pollub.myplanszeo.memento;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayDeque;
import java.util.Deque;

//Tydzień 5, Wzorzec Memento 1
//Klasa ta przechowuje kolejne stany listy gier podczas jej edycji
//Dzięki temu użytkonik będzie mógł cofnąć wprowadzane zmiany
@AllArgsConstructor
@NoArgsConstructor
public class BoardGameListCaretaker {

    private BoardGameListDtoMemento lastMemento;
    final Deque<BoardGameListDtoMemento> mementos = new ArrayDeque<>();

    public BoardGameListDtoMemento getLastMemento() {
        return lastMemento;
    }

    public BoardGameListDtoMemento revert() {
        lastMemento = mementos.pop();
        return lastMemento;
    }

    public void addMemento(BoardGameListDtoMemento memento) {
        mementos.push(memento);
        lastMemento = memento;
    }

    public int mementoSize() {
        return mementos.size();
    }
}
//Koniec, Tydzień 5, Wzorzec Memento 1