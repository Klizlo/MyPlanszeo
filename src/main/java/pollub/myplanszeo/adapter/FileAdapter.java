package pollub.myplanszeo.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;

//Tydzień 2, Wzorzec Adapter 1
//Interfejs adaptera zmieniający obiekt klasy BoardGameList na tablicę bajtów z formatu JSON
public interface FileAdapter {
    public byte[] getBoardGameListAsJSON() throws JsonProcessingException;
}
//Koniec, Tydzień 2, Wzorzec Adapter 1