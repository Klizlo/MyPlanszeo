package pollub.myplanszeo.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pollub.myplanszeo.model.AgeRestriction;

import java.util.stream.Stream;

//Tydzień 7, Zasada Pojedynczej Odpowiedzialności 1
//Klasa ma za zadanie przekonwertować String do klasy Enum i na odwrót
@Converter(autoApply = true)
public class AgeRestrictionConverter implements AttributeConverter<AgeRestriction, String> {
    @Override
    public String convertToDatabaseColumn(AgeRestriction ageRestriction) {
        if (ageRestriction == null) {
            return null;
        }
        return ageRestriction.getAge();
    }

    @Override
    public AgeRestriction convertToEntityAttribute(String age) {
        if (age == null) {
            return null;
        }

        return Stream.of(AgeRestriction.values())
                .filter(ageRestriction -> ageRestriction.getAge().equals(age))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
//Koniec, Tydzień 7, Zasada Pojedynczej Odpowiedzialności 1