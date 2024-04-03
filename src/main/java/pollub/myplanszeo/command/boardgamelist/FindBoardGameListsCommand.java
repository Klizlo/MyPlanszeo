package pollub.myplanszeo.command.boardgamelist;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.repository.BoardGameListRepository;

//Tydzień 5, Wzorzec Command 1
//Klasa o pojedyńczej odpowiedzialności, która implementuje interfejs Command
//Odpowiada za pobranie wszytskich list gier danego użytkownika z bazy danych
@RequiredArgsConstructor
public class FindBoardGameListsCommand implements BoardGameListCommand{

    private final BoardGameListRepository boardGameListRepository;
    private final Long userId;

    @Override
    public Object execute() {
        return boardGameListRepository.findAllByUser_Id(userId);
    }
}
//Koniec, Tydzień 5, Wzorzec Command 1