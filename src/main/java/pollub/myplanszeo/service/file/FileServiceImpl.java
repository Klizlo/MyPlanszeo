package pollub.myplanszeo.service.file;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import pollub.myplanszeo.adapter.FileAdapter;
import pollub.myplanszeo.adapter.FileAdapterImpl;
import pollub.myplanszeo.exception.FileTypeNotFoundException;
import pollub.myplanszeo.model.BoardGameList;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public byte[] getBoardGameList(BoardGameList boardGameList, FileType fileType) {
        FileAdapter fileAdapter = new FileAdapterImpl(boardGameList);
        switch (fileType) {
            case JSON -> {
                return fileAdapter.getBoardGameListAsJSON();
            }
            default -> throw new FileTypeNotFoundException();
        }
    }

    @Override
    public void prepareFileType(FileService.FileType type, HttpHeaders headers, BoardGameList boardGameList) {
        switch (type) {
            case JSON -> {
                headers.add("Content-Disposition", "attachment; filename=\"" + boardGameList.getName() + ".json\"");
                headers.add("Content-Type", "application/json");
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
