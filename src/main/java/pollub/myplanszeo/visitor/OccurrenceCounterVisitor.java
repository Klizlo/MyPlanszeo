package pollub.myplanszeo.visitor;

import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;

//Tydzień 6, Wzorzec Visitor 1
//Visitor, który ma za zadanie zliczyć ile użytkowników ma zapisaną daną grę na jakiej kolwiek swojej liście
//oraz który ma za zadanie zliczyć liczbę gier znajdujących się na liście
public class OccurrenceCounterVisitor implements Visitor{
    @Override
    public int visit(BoardGame boardGame) {
        return (int) boardGame
                .getBoardGameLists()
                .stream().map(BoardGameList::getUser)
                .distinct()
                .count();
    }

    @Override
    public int visit(BoardGameList boardGameList) {
        return boardGameList
                .getBoardGames()
                .size();
    }
}
//Koniec, Tydzień 6, Wzorzec Visitor 1