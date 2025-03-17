package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Dinar");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        UnsupportedOperationException exception = assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    config.value("name");
                });
        assertThat(exception.getMessage()).isEqualTo("Don't find this key: name");
    }

    @Test
    void whenPairWithEmptyLine() {
        String path = "./data/pair_with_emptyLine.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Dinar");
    }

    @Test
    void whenPairWithTwoSymbolEquals() {
        String path = "./data/pair_with_twoSumbolEquals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Dinar=Vova");
    }

    @Test
    void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                config::load);
        assertThat(exception.getMessage()).isEqualTo("Bad line: =Dinar");
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                config::load);
        assertThat(exception.getMessage()).isEqualTo("Bad line: name=");
    }

    @Test
    void whenPairWithoutEquals() {
        String path = "./data/pair_without_equals.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                config::load);
        assertThat(exception.getMessage()).isEqualTo("Bad line: name dinar");
    }
}