package com.di.utils.bs;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;

import static com.di.utils.bs.BsAdConverter.localDateToBs;
import static com.di.utils.bs.Utils.dnDigitToInt;
import static com.di.utils.bs.Utils.isValidDnDigit;

public final class BikramSambat implements Comparable<BikramSambat> {
    private static final HashMap<String, Integer> BS_MONTH_TO_INT_LOOKUP = new HashMap<String, Integer>() {{
        put("बैशाख", 1);
        put("वैशाख", 1);
        put("जेठ", 2);
        put("जेष्ठ", 2);
        put("असार", 3);
        put("आषाढ", 3);
        put("अषाढ", 3);
        put("श्रावण", 4);
        put("साउन", 4);
        put("भदौ", 5);
        put("भाद्र", 5);
        put("आश्विन", 6);
        put("आस्विन", 6);
        put("असोज", 6);
        put("अशोज", 6);
        put("कार्तिक", 7);
        put("कात्तिक", 7);
        put("मंसिर", 8);
        put("मङ्सिर", 8);
        put("मङि्सर", 8);
        put("पुष", 9);
        put("पुस", 9);
        put("पौष", 9);
        put("माघ", 10);
        put("फाल्गुण", 11);
        put("फाल्गुन", 11);
        put("फागुन", 11);
        put("चैत्र", 12);
        put("चैत", 12);
    }};

    private final int year;
    private final int month;
    private final int dayOfMonth;

    // properties to hold values in Devanagari script
    private final String dnYear;
    private final String dnMonth;
    private final String dnDayOfMonth;
    private final String dnString;

    private BikramSambat(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;

        this.dnYear = Utils.intToDnDigit(year);
        this.dnMonth = BSMonth.fromIntValue(month).getNepName();
        this.dnDayOfMonth = Utils.intToDnDigit(dayOfMonth);
        this.dnString = Utils.intToDnDigit(dayOfMonth) + " " + BSMonth.fromIntValue(month).getNepName() + ", " + Utils.intToDnDigit(year);
    }

    public static BikramSambat of(int year, int month, int day) {
        return new BikramSambat(year, month, day);
    }

    public static BikramSambat of(String dnYear, String dnMonth, String dnDayOfMonth) {
        if (!isValidDnDigit(dnYear)) {
            throw new RuntimeException(dnYear + " is not a valid Nepali digit.");
        }

        if (!isValidDnDigit(dnDayOfMonth)) {
            throw new RuntimeException(dnDayOfMonth + " is not a valid Nepali digit.");
        }

        boolean validBsMonth = BS_MONTH_TO_INT_LOOKUP.get(dnMonth) != null || isValidDnDigit(dnMonth);
        if (!validBsMonth) {
            throw new RuntimeException(dnMonth + " is not a valid Nepali month.");
        }

        int y = dnDigitToInt(dnYear);
        int m = isValidDnDigit(dnMonth) ? dnDigitToInt(dnMonth) : BS_MONTH_TO_INT_LOOKUP.get(dnMonth);
        int d = dnDigitToInt(dnDayOfMonth);

        return BikramSambat.of(y, m, d);
    }

    public static BikramSambat from(LocalDate localDate) {
        return localDateToBs(localDate);
    }

    public LocalDate toLocalDate() {
        return BsAdConverter.bsToLocalDate(this);
    }

    public static BikramSambat today() {
        Instant now = Instant.now();
        ZonedDateTime nepaliTime = now.atZone(ZoneId.of("GMT+05:45"));

        BikramSambat bikramSambat = localDateToBs(nepaliTime.toLocalDate());

        return bikramSambat;
    }

    public static BikramSambat yesterday() {
        Instant now = Instant.now();
        ZonedDateTime nepaliTime = now.atZone(ZoneId.of("GMT+05:45"));
        ZonedDateTime yesterday = nepaliTime.minusDays(1);
        BikramSambat bsYesterday = localDateToBs(yesterday.toLocalDate());
        return bsYesterday;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public String getDnYear() {
        return dnYear;
    }

    public String getDnMonth() {
        return dnMonth;
    }

    public String getDnDayOfMonth() {
        return dnDayOfMonth;
    }

    @Override
    public String toString() {
        return "BikramSambat{" +
                "year=" + year +
                ", month=" + month +
                ", dayOfMonth=" + dayOfMonth +
                ", dnYear='" + dnYear + '\'' +
                ", dnMonth='" + dnMonth + '\'' +
                ", dnDayOfMonth='" + dnDayOfMonth + '\'' +
                '}';
    }

    public String toDnString() {
        return dnString;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BikramSambat nepDate = (BikramSambat) obj;
        return this.intValue() == nepDate.intValue();
    }

    @Override
    public int hashCode() {
        return intValue();
    }

    @Override
    public int compareTo(BikramSambat other) {
        return Integer.compare(this.intValue(), other.intValue());
    }

    private int intValue() {
        String str = "" + year + (month < 10 ? "0" + month : month) + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        // create BS date from Devanagari inputs
        BikramSambat bs = BikramSambat.of("२०७६", "चैत", "१३");
        System.out.println(bs); // BikramSambat{year=2076, month=12, dayOfMonth=13, dnYear='२०७६', dnMonth='चैत', dnDayOfMonth='१३'}
        System.out.println(bs.toDnString()); // १३ चैत, २०७६

        // convert BS into AD
        LocalDate localDateFromBs = bs.toLocalDate();
        System.out.println(localDateFromBs); // 2020-03-26

        BikramSambat bs1 = BikramSambat.of("२०७६", "१२", "१३");
        System.out.println(bs1); // BikramSambat{year=2076, month=12, dayOfMonth=13, dnYear='२०७६', dnMonth='चैत', dnDayOfMonth='१३'}
        System.out.println(bs1.toDnString()); // १३ चैत, २०७६

        // create BS date from LocalDate
        BikramSambat bsFromLocalDate = BikramSambat.from(LocalDate.of(2020, 3, 26));
        System.out.println(bsFromLocalDate); // BikramSambat{year=2076, month=12, dayOfMonth=13, dnYear='२०७६', dnMonth='चैत', dnDayOfMonth='१३'}
        System.out.println(bsFromLocalDate.toDnString()); // १३ चैत, २०७६
    }
}