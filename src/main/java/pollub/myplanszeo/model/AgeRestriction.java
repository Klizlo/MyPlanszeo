package pollub.myplanszeo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AgeRestriction {
    PLUS_3 ("3+"),
    PLUS_7 ("7+"),
    PLUS_12 ("12+"),
    PLUS_16 ("16+");

    private final String age;
}
