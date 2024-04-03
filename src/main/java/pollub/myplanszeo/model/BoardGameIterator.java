package pollub.myplanszeo.model;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// Tydzień 5, Wzorzec Iterator 1
// Klasa implementująca interfejs iterator,
// Pozwala na przechodzenie po liście gier plaszowych
@RequiredArgsConstructor
public class BoardGameIterator implements Iterator<BoardGame> {

    private int currentIndex = 0;
    private final List<BoardGame> boardGames;

    @Override
    public boolean hasNext() {
        return currentIndex < boardGames.size();
    }

    @Override
    public BoardGame next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return boardGames.get(currentIndex++);
    }
}
// Koniec, Tydzień 5, Wzorzec Iterator