package pollub.myplanszeo.bridge;

import com.fasterxml.jackson.core.JsonProcessingException;

//Tydzień 3, Wzorzec Bridge 1
// Abstrakcyjna klasa Bridge do pozyskania danych w postaci bajtowej,
// a co za tym idzie możliwej do zapisania lub przesłania w formie pliku
abstract public class HttpFileBridger {
    public abstract byte[] getData(Object dataObject) throws JsonProcessingException;
}
//Koniec, Tydzień 3, Wzorzec Bridge 1