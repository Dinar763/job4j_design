package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(12, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void checkGetAreaSphereTest() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(1256.63d, withPrecision(0.06d));
    }

    @Test
    void checkGetAreaTetrahedronTest() {
        Box box = new Box(4, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(173.20d, withPrecision(0.06d));
    }

    @Test
    void checkGetAreaCubeTest() {
        Box box = new Box(8, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(600.00d, withPrecision(0.06d));
    }

    @Test
    void checkIsExistSpherePozitive() {
        Box box = new Box(0, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void checkIsExistSphereNegative() {
        Box box = new Box(1, 10);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void checkIsExistTetrahedronPozitive() {
        Box box = new Box(4, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void checkIsExistTetrahedronNegative() {
        Box box = new Box(5, 10);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void checkIsExistCubePozitive() {
        Box box = new Box(8, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void checkIsExistCubeNegative() {
        Box box = new Box(9, 10);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void checkGetNumberOfVerticesBySphere() {
        Box box = new Box(0, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void checkGetNumberOfVerticesByTetrahedron() {
        Box box = new Box(4, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(4);
    }

    @Test
    void checkGetNumberOfVerticesByCube() {
        Box box = new Box(8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8);
    }
}