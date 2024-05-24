package pollub.myplanszeo.adapter;

//Tydzień 3, Wzorzec Adapter 1
//Interfejs adaptera zmieniający obiekt klasy BoardGameList na tablicę bajtów z formatu JSON
public interface FileAdapter {
    byte[] getBoardGameListAsJSON();
}
//Koniec, Tydzień 3, Wzorzec Adapter 1