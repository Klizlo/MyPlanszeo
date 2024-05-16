package pollub.myplanszeo.unit.designPattern.structural;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pollub.myplanszeo.adapter.FileAdapter;
import pollub.myplanszeo.adapter.FileAdapterImpl;
import pollub.myplanszeo.dto.boardgamelist.BoardGameListFactory;
import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.facade.BoardGameListFacadeImpl;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.model.User;
import pollub.myplanszeo.service.boardgamelist.BoardGameListService;
import pollub.myplanszeo.service.file.FileService;
import pollub.myplanszeo.state.BoardGameListActiveState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacadeTest {

    @Mock
    private BoardGameListService boardGameListService;
    @Mock
    private FileService fileService;

    @InjectMocks
    private BoardGameListFacadeImpl boardGameListFacade;


    @Test
    public void givenUserId_whenGetAllBoardGameListsByUserId_returnBoardGameLists() {
        User user = new User(1L, "adam.nowak@poczta.pl", "Adqu28qtyubahhj1!?q", new ArrayList<>());
        List<BoardGameList> boardGameLists = Lists.newArrayList(
                new BoardGameList(1L, "Favorite", "", BoardGameListActiveState.instance(), null, user),
                new BoardGameList(2L, "To Play", "", BoardGameListActiveState.instance(), null, user)
        );
        user.setBoardGameLists(boardGameLists);

        when(boardGameListService.getAllBoardGameListByUserId(user.getId()))
                .thenReturn(boardGameLists);

        List<BoardGameList> boardGameListsFromFacade = boardGameListFacade.getAllBoardGameListsByUserId(1L);

        assertThat(boardGameListsFromFacade.size()).isEqualTo(2);
    }

    @Test
    public void givenUserIdAndBoardGameId_whenGetBoardGameListByIdAndUserId_returnBoardGameList() {
        User user = new User(1L, "adam.nowak@poczta.pl", "Adqu28qtyubahhj1!?q", new ArrayList<>());
        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "",BoardGameListActiveState.instance(),  null, user);
        user.getBoardGameLists().add(boardGameList);

        when(boardGameListService.getBoardGameListByIdAndUserId(boardGameList.getId(), user.getId()))
                .thenReturn(boardGameList);

        BoardGameList boardGameListFromFacade = boardGameListFacade.getBoardGameListByIdAndUserId(1L, 1L);

        assertThat(boardGameListFromFacade).isEqualTo(boardGameList);
    }

    @Test
    public void givenBoardGameListAndUserId_whenAddBoardGameList_returnBoardGameList() {
        User user = new User(1L, "adam.nowak@poczta.pl", "Adqu28qtyubahhj1!?q", new ArrayList<>());
        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "",BoardGameListActiveState.instance(),  null, user);
        user.getBoardGameLists().add(boardGameList);

        when(boardGameListService.addBoardGameList(boardGameList, user.getId()))
                .thenReturn(boardGameList);

        BoardGameList addedBoardGameList = boardGameListFacade.addBoardGameList(boardGameList, user.getId());
        assertThat(addedBoardGameList).isEqualTo(boardGameList);
    }

    @Test
    public void givenBoardGameListAndFullBoardGameListDto_whenEditBoardGameList_thenReturnBoardGameList() {
        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", BoardGameListActiveState.instance(), new HashSet<>(), null);
        FullBoardGameListDto fullBoardGameListDto = (FullBoardGameListDto) BoardGameListFactory.getBoardGameList(boardGameList, BoardGameListFactory.BoardGameListType.Full);
        fullBoardGameListDto.setName("Favorite 2");

        BoardGameList editedBoardGameList = new BoardGameList(boardGameList.getId(), fullBoardGameListDto.getName(), boardGameList.getDescription(), BoardGameListActiveState.instance(), null, null);

        when(boardGameListService.editBoardGameList(any(), any()))
                .thenReturn(editedBoardGameList);

        BoardGameList boardGameListAfterEdit = boardGameListFacade.editBoardGameList(boardGameList, fullBoardGameListDto);
        assertThat(boardGameListAfterEdit.getName()).isEqualTo(fullBoardGameListDto.getName());
    }

    @Test
    public void givenUserIdAndBoardGameIdAndFileType_whenGetBoardGameListAsFile_returnBoardGameListAsByteArray() throws IOException {
        User user = new User(1L, "adam.nowak@poczta.pl", "Adqu28qtyubahhj1!?q", new ArrayList<>());
        BoardGameList boardGameList = new BoardGameList(1L, "Favorite", "", BoardGameListActiveState.instance(), null, user);
        user.getBoardGameLists().add(boardGameList);
        FileService.FileType fileType = FileService.FileType.JSON;

        when(boardGameListService.getBoardGameListByIdAndUserId(boardGameList.getId(), user.getId()))
                .thenReturn(boardGameList);
        when(fileService.getBoardGameList(boardGameList, fileType))
                .thenReturn(getByteArray(boardGameList));

        byte[] file = boardGameListFacade.getBoardGameListAsFile(1L, 1L, FileService.FileType.JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        BoardGameList boardGameListFromJson = objectMapper.readValue(file, BoardGameList.class);

        assertThat(file.length).isGreaterThan(0);
        assertThat(boardGameListFromJson.getId()).isEqualTo(boardGameList.getId());
        assertThat(boardGameListFromJson.getName()).isEqualTo(boardGameList.getName());
    }

    public byte[] getByteArray(BoardGameList boardGameList) throws JsonProcessingException {
        FileAdapter fileAdapter = new FileAdapterImpl(boardGameList);
        return fileAdapter.getBoardGameListAsJSON();
    }


}
