package pollub.myplanszeo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.myplanszeo.adapter.FileAdapter;
import pollub.myplanszeo.adapter.FileAdapterImpl;
import pollub.myplanszeo.exception.FileTypeNotFoundException;
import pollub.myplanszeo.model.BoardGameList;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public byte[] getBoardGameList(BoardGameList boardGameList, FileType fileType) throws JsonProcessingException {
        FileAdapter fileAdapter = new FileAdapterImpl(boardGameList);
        switch (fileType) {
            case JSON -> {
                return fileAdapter.getBoardGameListAsJSON();
            }
            default -> throw new FileTypeNotFoundException();
        }
    }
}
