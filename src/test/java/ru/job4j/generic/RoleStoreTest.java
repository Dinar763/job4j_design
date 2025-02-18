package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsDinar() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dinar"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Dinar");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dinar"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsDinar() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dinar"));
        store.add(new Role("1", "Evgeni"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Dinar");
    }

    @Test
    void whenReplaceThenRolenameIsEvgeni() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dinar"));
        store.replace("1", new Role("1", "Evgeni"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Evgeni");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dinar"));
        store.replace("10", new Role("10", "Evgeni"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Dinar");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dinar"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsDinar() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dinar"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Dinar");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dinar"));
        boolean result = store.replace("1", new Role("1", "Evgeni"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dinar"));
        boolean result = store.replace("10", new Role("10", "Evgeni"));
        assertThat(result).isFalse();
    }

}