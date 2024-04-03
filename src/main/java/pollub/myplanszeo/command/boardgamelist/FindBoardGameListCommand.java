package pollub.myplanszeo.command.boardgamelist;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.exception.BoardGameListNotFoundException;
import pollub.myplanszeo.repository.BoardGameListRepository;

//Tydzień 5, Wzorzec Command 1
//Klasa o pojedyńczej odpowiedzialności, która implementuje interfejs Command
//Odpowiada za pobranie listy gier z bazy danych
@RequiredArgsConstructor
public class FindBoardGameListCommand implements BoardGameListCommand{

    private final BoardGameListRepository boardGameListRepository;
    private final Long boardGameListId;

    @Override
    public Object execute() {
        return boardGameListRepository.findById(boardGameListId)
                .orElseThrow(BoardGameListNotFoundException::new);
    }
}
//Koniec, Tydzień 5, Wzorzec Command 1