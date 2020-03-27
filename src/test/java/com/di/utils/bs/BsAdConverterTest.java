package com.di.utils.bs;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.di.utils.bs.BsAdConverter.BS_YR_TO_AD_NEW_YEAR_DAY_MAP;
import static com.di.utils.bs.BsAdConverter.localDateToBs;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BsAdConverterTest {

    @Test
    void localDateToBs_sanity_check() {
        BS_YR_TO_AD_NEW_YEAR_DAY_MAP.forEach((key, value) -> assertEquals(BikramSambat.of(key, 1, 1), localDateToBs(value)));
        BS_YR_TO_AD_NEW_YEAR_DAY_MAP.forEach((key, value) -> assertEquals(BikramSambat.of(key, 1, 2), localDateToBs(value.plusDays(1))));
    }

    @Test
    void localDateToBs_1() {
        BikramSambat bs4 = BikramSambat.from(LocalDate.of(1997, 2, 12));
        assertEquals(BikramSambat.of(2053, 11, 1), bs4);

        BikramSambat bs5 = BikramSambat.from(LocalDate.of(1991, 6, 16));
        assertEquals(BikramSambat.of(2048, 3, 2), bs5);
    }

    @Test
    void bsToLocalDate_sanity_check() {
        BS_YR_TO_AD_NEW_YEAR_DAY_MAP.forEach((key1, value1) -> assertEquals(value1, BsAdConverter.bsToLocalDate(BikramSambat.of(key1, 1, 1))));
        BS_YR_TO_AD_NEW_YEAR_DAY_MAP.forEach((key, value) -> assertEquals(value.plusDays(5), BsAdConverter.bsToLocalDate(BikramSambat.of(key, 1, 6))));
    }
}