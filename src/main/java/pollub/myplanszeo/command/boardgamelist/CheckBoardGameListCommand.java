package pollub.myplanszeo.command.boardgamelist;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.repository.BoardGameListRepository;

//Tydzień 5, Wzorzec Command 1
//Klasa o pojedyńczej odpowiedzialności, która implementuje interfejs Command
//Odpowiada za pobranie listy gier z bazy danych
@RequiredArgsConstructor
public class CheckBoardGameListCommand implements BoardGameListCommand{

    private final BoardGameListRepository boardGameListRepository;
    private final Long boardGameListId;
    private final Long userId;

    @Override
    public Object execute() {
        return boardGameListRepository.existsByIdAndUser_Id(boardGameListId, userId);
    }
}
//Koniec, Tydzień 5, Wzorzec Command 1