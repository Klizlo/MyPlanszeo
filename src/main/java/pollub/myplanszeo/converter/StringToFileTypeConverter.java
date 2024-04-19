package pollub.myplanszeo.converter;

import org.springframework.core.convert.converter.Converter;
import pollub.myplanszeo.service.file.FileService;

//Tydzień 7, Zasada Pojedynczej Odpowiedzialności 2
//Klasa ma zadanie przekonwertować String na element Enum i na odwrót
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
//Koniec, Tydzień 7, Zasada Pojedynczej Odpowiedzialności 2