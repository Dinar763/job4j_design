package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("no data");
    }

    @Test
    void checkParseWithLengthParametersIsZero() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkParseWhenParameterIsWithoutSymbolEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Key:Value"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("this name: Key:Value does not contain the symbol '='");
    }

    @Test
    void checkParseWhenParameterIsStartingSymbolEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=Key=Value"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("this name: =Key=Value does not contain a key");
    }

    @Test
    void checkParseWhenParameterIsLastSymbolEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Key-Value="))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("this name: Key-Value= does not contain a value");
    }
}