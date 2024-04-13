package pollub.myplanszeo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import pollub.myplanszeo.model.BoardGameList;

public interface FileService {

    byte[] getBoardGameList(BoardGameList boardGameList, FileType fileType);
    void prepareFileType(FileService.FileType type, HttpHeaders headers, BoardGameList boardGameList);

    enum FileType {
        JSON
    }

}
