package pollub.myplanszeo.bridge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pollub.myplanszeo.model.BoardGameList;

import java.nio.charset.StandardCharsets;

//Tydzień 3, Wzorzec Bridge 1
//Konkretyzacja implemnetatora
//Klasa ta ma za zadnie przekonwertowanie listy gier na format JSON, a następnie zapisanie JSONa do tablicy bajtów.
public class HttpJsonFileProcessor implements HttpFileProcessor{

    @Override
    public byte[] getAsFile(BoardGameList boardGameList) {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        try {
            String json = objectMapper.writeValueAsString(boardGameList);
            return json.getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
//Koniec, Tydzień 3, Wzorzec Bridge 1
