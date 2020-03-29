package com.di.utils.bs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"९", "८", "७", "६", "५", "४", "३", "२", "१", "०", "९८७६५४३२१"})
    void isValidDnDigit(String dnDigit) {
        assertTrue(Utils.isValidDnDigit(dnDigit));
    }

    @ParameterizedTest
    @MethodSource("provideDnToIntArgs")
    void intToDnDigit(String expectedDnDigit, int intVal) {
        assertEquals(expectedDnDigit, Utils.intToDnDigit(intVal));
    }

    @ParameterizedTest
    @MethodSource("provideDnToIntArgs")
    void dnDigitToInt(String dnDigit, int expectedInt) {
        assertEquals(expectedInt, Utils.dnDigitToInt(dnDigit));
    }

    private static Stream<Arguments> provideDnToIntArgs() {
        return Stream.of(
                Arguments.of("९", 9),
                Arguments.of("८", 8),
                Arguments.of("७", 7),
                Arguments.of("६", 6),
                Arguments.of("५", 5),
                Arguments.of("४", 4),
                Arguments.of("३", 3),
                Arguments.of("२", 2),
                Arguments.of("१", 1),
                Arguments.of("०", 0),
                Arguments.of("९८७६५४३२१", 987654321)
        );
    }
}