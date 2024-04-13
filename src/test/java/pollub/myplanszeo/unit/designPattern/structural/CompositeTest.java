package pollub.myplanszeo.unit.designPattern.structural;

import org.junit.jupiter.api.Test;
import pollub.myplanszeo.dto.boardgame.BaseBoardGameDto;
import pollub.myplanszeo.dto.boardgame.BoardGameDto;
import pollub.myplanszeo.model.AgeRestriction;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.Category;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class CompositeTest {

    @Test
    public void givenBoardGameWithExpansions_whenUseBaseBoardGameDtoBuilder_returnComposite() {

        Category cooperative = new Category(1L, "Cooperative", null);
        BoardGame mansionOfMadness = new BoardGame(1L, "Mansion of Madness", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, null, null, new ArrayList<>());
        BoardGame mmStreetsOfArkham = new BoardGame(2L, "Mansion of Madness: Streets of Arkham", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, null, mansionOfMadness, null);
        BoardGame mmHorrificJourneys = new BoardGame(3L, "Mansion of Madness: Horrific Journeys", AgeRestriction.PLUS_12, "", "FFG", 1, 5, cooperative, null, mansionOfMadness, null);
        mansionOfMadness.getDlcs().addAll(List.of(mmStreetsOfArkham, mmHorrificJourneys));

        BoardGameDto mansionOfMadnessDto = new BaseBoardGameDto.Builder(
                mansionOfMadness.getId(),
                mansionOfMadness.getName(),
                mansionOfMadness.getProducer(),
                mansionOfMadness.getCategory(),
                mansionOfMadness.getDlcs())
                .setAgeRestriction(mansionOfMadness.getAgeRestriction())
                .setDescription(mansionOfMadness.getDescription())
                .setMinNumOfPlayers(mansionOfMadness.getMinNumOfPlayers())
                .setMaxNumOfPlayers(mansionOfMadness.getMaxNumOfPlayers())
                .build();

        assertInstanceOf(BaseBoardGameDto.class,     mansionOfMadnessDto);
        assertEquals( 2,                    ((BaseBoardGameDto) mansionOfMadnessDto).getExpansions().size());

    }

}
