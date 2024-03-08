package pollub.myplanszeo.dto;

import pollub.myplanszeo.model.BoardGameList;

//Tydzień 2, Wzorzec Factory
// Wzorzec Factory służy do wybrania, który rodzaj listy gier należy zwrócić użytkownikowi
// Klasa posiada wewnętrzny typ wyliczeniowy, który sprawia, że kod jest bardziej czytelny
public class BoardGameListFactory {

    public static BoardGameListDto getBoardGameList(BoardGameList gameList, BoardGameListType type) {
        switch (type) {
            case Simple -> {
                return new SimpleBoardGameListDto.Builder(gameList.getId(), gameList.getName(), gameList.getBoardGames().size())
                        .setDescription(gameList.getDescription())
                        .build();
            }
            case Full -> {
                return new FullBoardGameListDto.Builder(gameList.getId(), gameList.getName(), gameList.getBoardGames())
                        .setDescription(gameList.getDescription())
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
