package pollub.myplanszeo.command.boardgamelist;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.repository.BoardGameListRepository;

import java.util.List;

@RequiredArgsConstructor
public class RemoveBoardGameFromBoardGameLists implements BoardGameListCommand {
    private final BoardGameListRepository boardGameListRepository;
    private final List<BoardGameList> boardGameLists;
    private final BoardGame boardGame;

    @Override
    public Object execute() {
        for (BoardGameList boardGameList : boardGameLists) {
            boardGameList.getBoardGames().remove(boardGame);
            boardGame.getBoardGameLists().remove(boardGameList);
        }
        return boardGameListRepository.saveAll(boardGameLists);
    }
}
