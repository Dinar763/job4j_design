package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
            .contains("second")
            .contains("first", Index.atIndex(0))
            .containsAnyOf("zero", "second", "six")
            .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
            .contains("four")
            .contains("five", Index.atIndex(4))
            .containsAnyOf("zero", "second", "six")
            .doesNotContain("five", Index.atIndex(0))
            .containsOnly("first", "second", "three", "four", "five")
            .allSatisfy(e -> {
                assertThat(e).isAlphabetic();
                assertThat(e).isLowerCase();
            });
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five", "first", "second");
        assertThat(set).hasSize(5)
            .containsOnly("first", "second", "three", "four", "five")
            .allSatisfy(e -> {
                assertThat(e).isAlphabetic();
                assertThat(e).isLowerCase();
            });
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five", "first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
            .containsOnlyKeys("first", "second", "three", "four", "five")
            .containsValues(0, 1, 2, 3, 4)
            .doesNotContainValue(5)
            .containsEntry("first", 0);
    }
}