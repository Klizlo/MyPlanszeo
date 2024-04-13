package pollub.myplanszeo.command.boardgamelist;

import lombok.RequiredArgsConstructor;
import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.repository.BoardGameListRepository;

@RequiredArgsConstructor
public class EditBoardGameListCommand implements BoardGameListCommand {
    private final BoardGameListRepository boardGameListRepository;
    private final BoardGameList boardGameList;
    private final FullBoardGameListDto boardGameListDto;

    @Override
    public Object execute() {
        boardGameList.setName(boardGameListDto.getName());
        boardGameList.setDescription(boardGameList.getDescription());
        return boardGameListRepository.save(boardGameList);
    }
}
