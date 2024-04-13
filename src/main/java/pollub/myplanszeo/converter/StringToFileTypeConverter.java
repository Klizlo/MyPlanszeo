package pollub.myplanszeo.converter;

import org.springframework.core.convert.converter.Converter;
import pollub.myplanszeo.service.FileService;

public class StringToFileTypeConverter implements Converter<String, FileService.FileType> {
    @Override
    public FileService.FileType convert(String source) {
        try {
            return FileService.FileType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
