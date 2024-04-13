package pollub.myplanszeo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pollub.myplanszeo.config.security.CustomUserDetails;
import pollub.myplanszeo.dto.boardgamelist.BoardGameListDto;
import pollub.myplanszeo.dto.boardgamelist.BoardGameListFactory;
import pollub.myplanszeo.dto.boardgamelist.FullBoardGameListDto;
import pollub.myplanszeo.dto.mapper.BoardGameListMapper;
import pollub.myplanszeo.facade.BoardGameListFacade;
import pollub.myplanszeo.memento.BoardGameListCaretaker;
import pollub.myplanszeo.memento.BoardGameListDtoMemento;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.service.file.FileService;
import pollub.myplanszeo.state.BoardGameListEditState;
import pollub.myplanszeo.state.BoardGameListState;

import java.util.HashSet;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardGameListController {

    private final BoardGameListFacade boardGameListFacade;

    @GetMapping("/boardgamelists")
    public String getAllBoardGameLists(Model model, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        List<BoardGameListDto> boardGameListDtos = BoardGameListMapper
                .mapToDtos(boardGameListFacade.getAllBoardGameListsByUserId(principal.getId()),
                        BoardGameListFactory.BoardGameListType.Simple);
        model.addAttribute("lists", boardGameListDtos);

        return "boardgamelist/boardgamelists";
    }

    @GetMapping("/boardgamelists/{id}")
    public String getBoardGameList(@PathVariable Long id, Model model, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        BoardGameList boardGameList = boardGameListFacade.getBoardGameListByIdAndUserId(id, principal.getId());
        model.addAttribute("list", BoardGameListMapper.mapToDto(boardGameList, BoardGameListFactory.BoardGameListType.Full));
        return "boardgamelist/boardgamelist";
    }

    @GetMapping("/boardgamelists/modal/game/{id}")
    public String getBoardGameListModal(@PathVariable Long gameId, Model model, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        List<BoardGameListDto> boardGameListDtos = BoardGameListMapper
                .mapToDtos(boardGameListFacade.getAllBoardGameListsByUserId(principal.getId()),
                        BoardGameListFactory.BoardGameListType.Simple);
        model.addAttribute("lists", boardGameListDtos);
        model.addAttribute("gameId", gameId);

        return "boardgamelist/modal";
    }

    @GetMapping("/boardgamelists/add")
    public String getAddBoardGameListForm(Model model) {
        model.addAttribute("list", new BoardGameList());

        return "boardgamelist/add_boardgamelist_form";
    }

    @PostMapping("/boardgamelists/process_add")
    public String processAddBoardGameListForm(@ModelAttribute("list") BoardGameList boardGameList, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        boardGameListFacade.addBoardGameList(boardGameList, principal.getId());

        return "redirect:/boardgamelists";
    }

    @PostMapping("/boardgamelists/add_game/{gameId}")
    public String addBoardGameToLists(@PathVariable Long gameId, @RequestParam(value = "lists", required = false) List<Long> selected, Authentication authentication, HttpServletRequest request) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        boardGameListFacade.modifyBoardGameInBoardGameLists(gameId, selected, principal.getId());
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/boardgamelists/{id}/edit")
    public String getUpdateBoardGameListForm(@PathVariable Long id, Model model, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        BoardGameList boardGameList = boardGameListFacade.getBoardGameListByIdAndUserId(id, principal.getId());
        if (boardGameList.getState().equals(BoardGameListEditState.instance())) {
            return "boardgamelist/boardgamelists";
        }
        boardGameList.setState(BoardGameListEditState.instance());
        model.addAttribute("list", (FullBoardGameListDto) BoardGameListMapper.mapToDto(boardGameList, BoardGameListFactory.BoardGameListType.Full));

        return "boardgamelist/update_boardgamelist_form";
    }

    @PostMapping("/boardgamelists/{id}/state")
    public String setBoardGameListState(@PathVariable Long boardGameListId, Authentication authentication, HttpServletRequest request) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        boardGameListFacade.changeBoardGameListState(boardGameListId, principal.getId());

        return "redirect:/boardgamelists";
    }

    @PostMapping("/boardgamelists/{id}/edit")
    public String updateBoardGameList(@PathVariable Long id, Model model, @ModelAttribute("list") FullBoardGameListDto boardGameList, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        BoardGameListCaretaker boardGameListCaretaker = new BoardGameListCaretaker();
        BoardGameList boardGameListToEdit = boardGameListFacade.getBoardGameListByIdAndUserId(id, principal.getId());
        boardGameListCaretaker.addMemento(((FullBoardGameListDto)BoardGameListMapper.mapToDto(boardGameListToEdit, BoardGameListFactory.BoardGameListType.Full)).saveToMemento());
        model.addAttribute("caretaker", boardGameListCaretaker);
        BoardGameListDtoMemento lastMemento = boardGameListCaretaker.getLastMemento();
        model.addAttribute("memento", lastMemento);

        boardGameListFacade.editBoardGameList(boardGameListToEdit, boardGameList);

        model.addAttribute("lists", BoardGameListMapper
                .mapToDtos(boardGameListFacade.getAllBoardGameListsByUserId(principal.getId()),
                        BoardGameListFactory.BoardGameListType.Simple));
        return "boardgamelist/boardgamelists";
    }

    @PostMapping("/boardgamelists/undo")
    public String undoBoardGameListChanges(@ModelAttribute("memento") BoardGameListDtoMemento boardGameListDtoMemento, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        FullBoardGameListDto boardGameListDto = new FullBoardGameListDto.Builder(null, null, new HashSet<>()).build();
        boardGameListDto.undoFromMemento(boardGameListDtoMemento);
        System.out.println(boardGameListDtoMemento.getBoardGames().size());
        BoardGameList boardGameList = boardGameListFacade.getBoardGameListByIdAndUserId(boardGameListDto.getId(), principal.getId());
        boardGameListFacade.editBoardGameList(boardGameList, boardGameListDto);

        return "redirect:/boardgamelists";
    }

    @GetMapping("/boardgamelists/{id}/copy")
    public String getCopyBoardGameListForm(@PathVariable Long id, Model model, Authentication authentication) throws CloneNotSupportedException {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        BoardGameList boardGameList = boardGameListFacade.getBoardGameListByIdAndUserId(id, principal.getId());
        model.addAttribute("list", boardGameList.clone());

        return "boardgamelist/copy_boardgamelist_form";
    }

    @PostMapping("/boardgamelists/copy")
    public String copyBoardGameList(@ModelAttribute("list") BoardGameList boardGameList, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        boardGameListFacade.addBoardGameList(boardGameList, principal.getId());

        return "redirect:/boardgamelists";
    }

    @GetMapping("/boardgamelists/{id}/delete")
    public String removeBoardGameList(@PathVariable Long id, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        boardGameListFacade.removeBoardGameList(id, principal.getId());

        return "redirect:/boardgamelists";
    }

    @GetMapping("/boardgamelists/{id}/download/{type}")
    public ResponseEntity<byte[]> downloadBoardGameList(@PathVariable("id") Long id, @PathVariable("type") FileService.FileType type, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        BoardGameList boardGameList = boardGameListFacade.getBoardGameListByIdAndUserId(id, principal.getId());
        HttpHeaders headers = new HttpHeaders();
        boardGameListFacade.prepareFileType(type, headers, boardGameList);
        byte[] file = boardGameListFacade.getBoardGameListAsFile(id, principal.getId(), type);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length)
                .body(file);
    }

}
