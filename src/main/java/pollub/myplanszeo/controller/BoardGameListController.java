package pollub.myplanszeo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pollub.myplanszeo.config.security.CustomUserDetails;
import pollub.myplanszeo.dto.BoardGameListDto;
import pollub.myplanszeo.dto.BoardGameListFactory;
import pollub.myplanszeo.dto.mapper.BoardGameListMapper;
import pollub.myplanszeo.facade.BoardGameListFacade;
import pollub.myplanszeo.flyweight.BoardGameCache;
import pollub.myplanszeo.model.BoardGameList;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardGameListController {

    private final BoardGameListFacade boardGameListFacade;
    private final BoardGameCache boardGameCache;

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
        System.out.println(boardGameList);
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
}
