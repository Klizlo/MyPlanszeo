package pollub.myplanszeo.dto.boardgamelist;

import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.visitor.OccurrenceCounterVisitor;
import pollub.myplanszeo.visitor.Visitor;

//Tydzień 2, Wzorzec Factory 1
// Wzorzec Factory służy do wybrania, który rodzaj listy gier należy zwrócić użytkownikowi
// Klasa posiada wewnętrzny typ wyliczeniowy, który sprawia, że kod jest bardziej czytelny
public class BoardGameListFactory {

    private final static Visitor VISITOR = new OccurrenceCounterVisitor();

    public static BoardGameListDto getBoardGameList(BoardGameList gameList, BoardGameListType type) {
        switch (type) {
            case Simple -> {
                return new SimpleBoardGameListDto.Builder(gameList.getId(), gameList.getName(), gameList.accept(VISITOR))
                        .setDescription(gameList.getDescription())
                        .setState(gameList.getState().getStateName())
                        .build();
            }
            case Full -> {
                return new FullBoardGameListDto.Builder(gameList.getId(), gameList.getName(), gameList.getBoardGames())
                        .setDescription(gameList.getDescription())
                        .setState(gameList.getState().getStateName())
                        .build();
            }
            default -> {
                return null;
            }
        }
    }

    public enum BoardGameListType {
        Simple,
        Full;
    }
}
// Koniec, Tydzień 2, Wzorzec Factory 1
