package pollub.myplanszeo.command.boardgamelist;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.repository.BoardGameListRepository;

@RequiredArgsConstructor
public class RemoveBoardGameList implements BoardGameListCommand {

    private final BoardGameListRepository boardGameListRepository;
    private final Long boardGameListId;

    @Override
    public Object execute() {
        boardGameListRepository.deleteById(boardGameListId);
        return null;
    }
}
