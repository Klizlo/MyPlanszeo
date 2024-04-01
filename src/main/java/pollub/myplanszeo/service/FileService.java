package pollub.myplanszeo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pollub.myplanszeo.model.BoardGameList;

public interface FileService {

    byte[] getBoardGameList(BoardGameList boardGameList, FileType fileType) throws JsonProcessingException;

    enum FileType {
        JSON
    }

}
