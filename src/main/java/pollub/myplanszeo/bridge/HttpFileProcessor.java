package pollub.myplanszeo.bridge;

import com.fasterxml.jackson.core.JsonProcessingException;
import pollub.myplanszeo.model.BoardGameList;

//Tydzień 3, Wzorzec Bridge 1
//Implementator, który ma za zadanie przetworzenie listy gier na tablicę bajtów
//Pozwala to na zapisanie listy gier na tablicę bajtów w zależności od tego w jakiej postaci chcemy te dane zapisać (JSON, XML, CSV)
public interface HttpFileProcessor {

    public byte[] getAsFile(BoardGameList boardGameList) throws JsonProcessingException;

}
//Koniec, Tydzień 3, Wzorzec Bridge 1
