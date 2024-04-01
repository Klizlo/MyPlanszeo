package pollub.myplanszeo.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import pollub.myplanszeo.bridge.BoardGameListHttpFileBridge;
import pollub.myplanszeo.bridge.HttpFileBridger;
import pollub.myplanszeo.bridge.HttpJsonFileProcessor;
import pollub.myplanszeo.model.BoardGameList;

//Tydzień 3, Wzorzec Adapter 1
//Klasa implementująca interfejs adaptera
public class FileAdapterImpl implements FileAdapter {

    private BoardGameList boardGameList;

    public FileAdapterImpl(BoardGameList boardGameList) {
        this.boardGameList = boardGameList;
    }

    @Override
    public byte[] getBoardGameListAsJSON() throws JsonProcessingException {
        HttpFileBridger httpFileBridger = new BoardGameListHttpFileBridge(new HttpJsonFileProcessor());
        return httpFileBridger.getData(boardGameList);
    }
}
//Koniec, Tydzień 3, Wzorzec Adapter 1
