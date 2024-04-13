package pollub.myplanszeo.unit.designPattern.behavioral;

import org.junit.jupiter.api.Test;
import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.memento.BoardGameListCaretaker;
import pollub.myplanszeo.memento.BoardGameListDtoMemento;
import pollub.myplanszeo.model.AgeRestriction;
import pollub.myplanszeo.model.BoardGame;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class MementoTest {

    @Test
    public void whenGetMemento_returnPreviousVersionOfList() {
        FullBoardGameListDto fullBoardGameListDto = new FullBoardGameListDto.Builder(1L, "Favorites", new HashSet<>())
                .setDescription("")
                .build();

        BoardGame munchkin = new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, null, new HashSet<>(), null, null);
        fullBoardGameListDto.getBoardGames().add(munchkin);

        BoardGameListCaretaker boardGameListCaretaker = new BoardGameListCaretaker();
        boardGameListCaretaker.addMemento(fullBoardGameListDto.saveToMemento());

        BoardGameListDtoMemento memento = boardGameListCaretaker.revert();
        fullBoardGameListDto.getBoardGames().add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, null, new HashSet<>(), null, null));
        BoardGameListDtoMemento memento1 = fullBoardGameListDto.saveToMemento();

        assertThat(memento).isNotEqualTo(fullBoardGameListDto);
    }

}
