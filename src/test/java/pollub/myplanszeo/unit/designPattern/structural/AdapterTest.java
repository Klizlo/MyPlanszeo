package pollub.myplanszeo.unit.designPattern.structural;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pollub.myplanszeo.adapter.FileAdapter;
import pollub.myplanszeo.adapter.FileAdapterImpl;
import pollub.myplanszeo.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdapterTest {

    @Test
    public void givenBoardGameList_whenGetBoardGameListAsJSON_returnBoardGameAsByteArray() throws IOException {
        Category cardGame = new Category(1L, "Card Game", new ArrayList<>());
        Category cooperative = new Category(2L, "Cooperative", new ArrayList<>());
        Set<BoardGame> boardGames = new HashSet<>();
        boardGames.add(new BoardGame(1L, "Munchkin", AgeRestriction.PLUS_7, "", "BlackMonkGames", 2, 6, cardGame, new HashSet<>(), null, null));
        boardGames.add(new BoardGame(2L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, new HashSet<>(), null, null));

        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", boardGames, new User(1L, "adam.nowak@poczta.pl", "AFabcabcbahucyba", null));

        FileAdapter fileAdapter = new FileAdapterImpl(boardGameList);

        byte[] boardGameListAsJSON = fileAdapter.getBoardGameListAsJSON();
        ObjectMapper objectMapper = new ObjectMapper();
        BoardGameList boardGameListFromJson = objectMapper.readValue(boardGameListAsJSON, BoardGameList.class);

        assertTrue(boardGameListAsJSON.length > 0);
        assertEquals(boardGameList.getId(),     boardGameListFromJson.getId());
        assertEquals(boardGameList.getName(),   boardGameListFromJson.getName());
    }
}
