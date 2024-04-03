package pollub.myplanszeo.command.boardgamelist;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.repository.BoardGameListRepository;

//Tydzień 5, Wzorzec Command 1
//Klasa o pojedyńczwj odpowiedzialności, która implementuje interfejs Command
//Odpowiada za dodanie listy gier do bazy danych
@RequiredArgsConstructor
public class AddBoardGameListCommand implements BoardGameListCommand{

    private final BoardGameListRepository boardGameListRepository;
    private final BoardGameList boardGameList;

    @Override
    public Object execute() {
        return boardGameListRepository.save(boardGameList);
    }
}
//Koniec, Tydzień 5, Wzorzec Command 1
