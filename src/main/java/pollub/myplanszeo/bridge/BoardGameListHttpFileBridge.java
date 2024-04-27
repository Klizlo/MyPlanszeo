package pollub.myplanszeo.bridge;

import pollub.myplanszeo.model.BoardGameList;

//Tydzień 3, Wzorzec Bridge 1
//Klasa dziedzicząca po abstrakcyjnej klasie Bridge
//Klasa ta ma zadanie przetwarzanie listy gier planszowych jako tablica bajtów
public class BoardGameListHttpFileBridge extends HttpFileBridger {

    private HttpFileProcessor httpFileProcessor;

    public BoardGameListHttpFileBridge(HttpFileProcessor httpFileProcessor) {
        this.httpFileProcessor = httpFileProcessor;
    }

    @Override
    public byte[] getData(Object dataObject) {
        return httpFileProcessor.getAsFile((BoardGameList) dataObject);
    }
}
//Koniec, Tydzień 3, Wzorzec Bridge 1
