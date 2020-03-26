package com.di.utils.bs;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BsAdConverterTest {

    @Test
    void localDateToBs_sanity_check() {

        BsAdConverter.BS_YR_TO_AD_NEW_YEAR_DAY_MAP.entrySet()
                                                  .stream()
                                                  .forEach(e -> assertEquals(BikramSambat.of(e.getKey(), 1, 1), BsAdConverter.localDateToBs(e.getValue())));

        BsAdConverter.BS_YR_TO_AD_NEW_YEAR_DAY_MAP.entrySet()
                                                  .stream()
                                                  .forEach(e -> assertEquals(BikramSambat.of(e.getKey(), 1, 2), BsAdConverter.localDateToBs(e.getValue().plusDays(1))));

    }

    @Test
    void localDateToBs_1() {
        BikramSambat bs4 = BikramSambat.from(LocalDate.of(1997, 2, 12));
        assertEquals(BikramSambat.of(2053, 11, 1), bs4);

        BikramSambat bs5 = BikramSambat.from(LocalDate.of(1991, 6, 16));
        assertEquals(BikramSambat.of(2048, 3, 2), bs5);
    }
}