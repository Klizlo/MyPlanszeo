package pollub.myplanszeo.bridge;

import pollub.myplanszeo.model.BoardGameList;

//Tydzień 3, Wzorzec Bridge 1
//Implementator, który ma za zadanie przetworzenie listy gier na tablicę bajtów
//Pozwala to na zapisanie listy gier na tablicę bajtów w zależności od tego w jakiej postaci chcemy te dane zapisać (JSON, XML, CSV)
public interface HttpFileProcessor {

    byte[] getAsFile(BoardGameList boardGameList);

}
//Koniec, Tydzień 3, Wzorzec Bridge 1
