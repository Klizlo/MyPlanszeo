package pollub.myplanszeo.visitor;

import pollub.myplanszeo.functional.Counter;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;

//Tydzień 6, Wzorzec Visitor 1
//Visitor, który ma za zadanie zliczyć ile użytkowników ma zapisaną daną grę na jakiej kolwiek swojej liście
//oraz który ma za zadanie zliczyć liczbę gier znajdujących się na liście
public class OccurrenceCounterVisitor implements Visitor{
    @Override
    public long visit(BoardGame boardGame) {
        return count(() -> boardGame
                .getBoardGameLists()
                .stream().map(BoardGameList::getUser)
                .distinct()
                .count());
    }

    @Override
    public long visit(BoardGameList boardGameList) {
        return count(() -> boardGameList
                .getBoardGames()
                .size());
    }

    //Tydzień 10, programowanie funkcyjne 3
    //Wykorzystanie interfejsu funkcyjnego do policzenia wystąpień
    private long count(Counter counter) {
        return counter.count();
    }
    //Koniec, Tydzień 10, programowanie funkcyjne 3
}
//Koniec, Tydzień 6, Wzorzec Visitor 1