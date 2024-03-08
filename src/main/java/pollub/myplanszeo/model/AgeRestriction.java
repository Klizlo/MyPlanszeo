package pollub.myplanszeo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Tydzień 2, Wzorzec Singleton
// Wzorzec Singleton został zaimplementowany za pomocą typu wyliczeniowego
// Typ wyliczeniowy przechowuje informacje o odpowiednim wieku, aby zagrać w grę
@RequiredArgsConstructor
@Getter
public enum AgeRestriction {
    PLUS_3 ("3+"),
    PLUS_7 ("7+"),
    PLUS_12 ("12+"),
    PLUS_16 ("16+");

    private final String age;
}
//Koniec, Tydzień 2, Wzorzec Singleton