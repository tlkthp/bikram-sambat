package com.di.utils.bs;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BikramSambatTest {

    @Test
    void of_en_digit() {
        BikramSambat bs = BikramSambat.of(2076, 12, 16);
        assertEquals("२०७६", bs.getDnYear());
        assertEquals("१२", bs.getDnMonth());
        assertEquals("१६", bs.getDnDayOfMonth());
    }

    @Test
    void of_dn_digit() {
        BikramSambat bs = BikramSambat.of("२०७६", "१२", "१६");
        assertEquals(2076, bs.getYear());
        assertEquals(12, bs.getMonth());
        assertEquals(16, bs.getDayOfMonth());
    }

    @Test
    void of_dn_text() {
        BikramSambat bs = BikramSambat.of("२०७६", "चैत", "१६");
        assertEquals(2076, bs.getYear());
        assertEquals(12, bs.getMonth());
        assertEquals(16, bs.getDayOfMonth());
    }

    @Test
    void from_localDate() {
        BikramSambat bsFromLocalDate = BikramSambat.from(LocalDate.of(2020, 3, 29));
        assertEquals(BikramSambat.of("२०७६", "१२", "१६"), bsFromLocalDate);
    }

    @Test
    void toLocalDate() {
        LocalDate localDateFromBs = BikramSambat.of("२०७६", "१२", "१६").toLocalDate();
        assertEquals(LocalDate.of(2020, 3, 29), localDateFromBs);
    }
}