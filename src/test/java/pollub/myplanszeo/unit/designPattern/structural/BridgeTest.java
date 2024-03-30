package pollub.myplanszeo.unit.designPattern.structural;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pollub.myplanszeo.bridge.BoardGameListHttpFileBridge;
import pollub.myplanszeo.bridge.HttpFileBridger;
import pollub.myplanszeo.bridge.HttpJsonFileProcessor;
import pollub.myplanszeo.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BridgeTest {

    @Test
    public void givenBoardGameList_whenGetAsFile_returnBytes() throws IOException {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        Set<BoardGame> boardGames = new HashSet<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, null, null, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, null, null, null));

        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", boardGames, new User(1L, "adam.nowak@poczta.pl", "AFabcabcbahucyba", null));

        HttpFileBridger httpFileBridger = new BoardGameListHttpFileBridge(new HttpJsonFileProcessor());
        byte[] data = httpFileBridger.getData(boardGameList);


        ObjectMapper objectMapper = new ObjectMapper();
        BoardGameList boardGameListFromJson = objectMapper.readValue(data, BoardGameList.class);

        assertTrue(data.length > 0);
        assertEquals(boardGameList.getId(),     boardGameListFromJson.getId());
        assertEquals(boardGameList.getName(),   boardGameListFromJson.getName());
    }
}
