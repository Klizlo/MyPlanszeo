package pollub.myplanszeo.bridge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pollub.myplanszeo.model.BoardGameList;

import java.nio.charset.StandardCharsets;

//Tydzień 2, Wzorzec Bridge 1
//Konkretyzacja implemnetatora
//Klasa ta ma za zadnie przekonwertowanie listy gier na format JSON, a następnie zapisanie JSONa do tablicy bajtów.
public class HttpJsonFileProcessor implements HttpFileProcessor{

    @Override
    public byte[] getAsFile(BoardGameList boardGameList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        String json = objectMapper.writeValueAsString(boardGameList);
        return json.getBytes(StandardCharsets.UTF_8);
    }

}
//Koniec, Tydzień 2, Wzorzec Bridge 1
