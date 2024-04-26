package pollub.myplanszeo.service.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import pollub.myplanszeo.adapter.FileAdapter;
import pollub.myplanszeo.adapter.FileAdapterImpl;
import pollub.myplanszeo.exception.FileTypeNotFoundException;
import pollub.myplanszeo.model.BoardGameList;

@Slf4j
@Service
@Qualifier("LoggingFileService")
public class LoggingFileService implements FileService{

    @Override
    public byte[] getBoardGameList(BoardGameList boardGameList, FileType fileType) {

        log.warn("Creating board game list {} to a file", boardGameList.getId());

        FileAdapter fileAdapter = new FileAdapterImpl(boardGameList);
        switch (fileType) {
            case JSON -> {
                return fileAdapter.getBoardGameListAsJSON();
            }
            default -> throw new FileTypeNotFoundException();
        }
    }

    @Override
    public void prepareFileType(FileType type, HttpHeaders headers, BoardGameList boardGameList) {
        log.warn("Preparing file type {} to a file", type);
        switch (type) {
            case JSON -> {
                headers.add("Content-Disposition", "attachment; filename=\"" + boardGameList.getName() + ".json\"");
                headers.add("Content-Type", "application/json");
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
