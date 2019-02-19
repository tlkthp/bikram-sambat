package nepalidate;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;

public final class BikramSambat implements Comparable {

    private final int year;
    private final int month;
    private final int day;
    private final int dayOfWeek;

    // Devnagari
    private String nepaliString;
    private String dnYear;
    private String dnMonth;
    private String dnDay;
    private String dnDayOfWeek;

    public static final HashMap<String, Integer> monthLookup = new HashMap<String, Integer>() {{
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

    private static final HashMap<String, Integer> dayOfWeekLookup = new HashMap<String, Integer>() {{
        put("आइतवार", 1);
        put("आईतवार", 1);
        put("आइतबार", 1);
        put("सोमवार", 2);
        put("सोमबार", 2);
        put("मङ्गलवार", 3);
        put("मंगलवार", 3);
        put("मंगलबार", 3);
        put("मङ्गलबार", 3);
        put("बुधवार", 4);
        put("बुधबार", 4);
        put("बिहिवार", 5);
        put("बिहिबार", 5);
        put("बिहीवार", 5);
        put("बिहीबार", 5);
        put("शुक्रवार", 6);
        put("शुक्रबार", 6);
        put("शनिवार", 7);
        put("शनिबार", 7);
    }};

    private BikramSambat(int year, int month, int day) {
        this(year, month, day, 0);
    }

    private BikramSambat(int year, int month, int day, int dayOfWeek) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;

        this.dnYear = Utils.intToNepaliDigit(year);
        this.dnMonth = NepaliMonth.fromIntValue(month).getNepName();
        this.dnDay = Utils.intToNepaliDigit(day);
        this.dnDayOfWeek = dayOfWeek > 0 ? NepaliDayOfWeek.fromIntValue(dayOfWeek).getNepName() : "";
        this.nepaliString = (dayOfWeek > 0 ? NepaliDayOfWeek.fromIntValue(dayOfWeek).getNepName() + ", " : "") + Utils.intToNepaliDigit(day) + " " + NepaliMonth.fromIntValue(month).getNepName() + ", " + Utils.intToNepaliDigit(year);
    }

    // +++++ factory methods ++++++++
    public static BikramSambat of(int year, int month, int day) {
        return new BikramSambat(year, month, day);
    }

    public static BikramSambat of(int year, int month, int day, int dayOfWeek) {
        return new BikramSambat(year, month, day, dayOfWeek);
    }

    public static BikramSambat today() {
        Instant now = Instant.now();
        ZonedDateTime nepaliTime = now.atZone(ZoneId.of("GMT+05:45"));

        BikramSambat bikramSambat = BsAdConverter.localDateIntoBs(nepaliTime);

        return bikramSambat;
    }

    public static BikramSambat yesterday() {
        Instant now = Instant.now();
        ZonedDateTime nepaliTime = now.atZone(ZoneId.of("GMT+05:45"));
        ZonedDateTime yesterday = nepaliTime.minusDays(1);
        BikramSambat bsYesterday = BsAdConverter.localDateIntoBs(yesterday);
        return bsYesterday;
    }

    /**
     * Factory method to create {@link BikramSambat} object from nepali dates.
     *
     * @param yrInNepaliDigit       nepali year e.g. "२०७५"
     * @param mthInNepali           nepali month e.g. "जेठ"
     * @param dayOfMthInNepaliDigit nepali date e.g. "१३"
     * @return
     */
    public static BikramSambat of(String yrInNepaliDigit, String mthInNepali, String dayOfMthInNepaliDigit) {
        if (!Utils.isValidNepaliDigit(yrInNepaliDigit)) {
            throw new RuntimeException(yrInNepaliDigit + " is not a valid Nepali digit.");
        }

        if (!Utils.isValidNepaliDigit(dayOfMthInNepaliDigit)) {
            throw new RuntimeException(dayOfMthInNepaliDigit + " is not a valid Nepali digit.");
        }

        if (monthLookup.get(mthInNepali) == null) {
            throw new RuntimeException(mthInNepali + " is not a valid Nepali month.");
        }

        int y = Utils.nepaliDigitToInt(yrInNepaliDigit);
        int m = monthLookup.get(mthInNepali);
        int d = Utils.nepaliDigitToInt(dayOfMthInNepaliDigit);

        return BikramSambat.of(y, m, d);
    }

    /**
     * Factory method to create {@link BikramSambat} object from nepali dates.
     *
     * @param yrInNepaliDigit       nepali year e.g. "२०७५"
     * @param mthInNepali           nepali month e.g. "जेठ"
     * @param dayOfMthInNepaliDigit nepali date e.g. "१३"
     * @param dayOfWeekInNepali     nepali day of week e.g. "आइतवार"
     * @return
     */
    public static BikramSambat of(String yrInNepaliDigit, String mthInNepali, String dayOfMthInNepaliDigit, String dayOfWeekInNepali) {
        if (!Utils.isValidNepaliDigit(yrInNepaliDigit)) {
            throw new RuntimeException(yrInNepaliDigit + " is not a valid Nepali digit.");
        }

        if (!Utils.isValidNepaliDigit(dayOfMthInNepaliDigit)) {
            throw new RuntimeException(dayOfMthInNepaliDigit + " is not a valid Nepali digit.");
        }

        if (monthLookup.get(mthInNepali) == null) {
            throw new RuntimeException(mthInNepali + " is not a valid Nepali month.");
        }

        if (dayOfWeekLookup.get(dayOfWeekInNepali) == null) {
            throw new RuntimeException(dayOfWeekInNepali + " is not a valid Nepali day of the week.");
        }

        int y = Utils.nepaliDigitToInt(yrInNepaliDigit);
        int m = monthLookup.get(mthInNepali);
        int d = Utils.nepaliDigitToInt(dayOfMthInNepaliDigit);
        int dd = dayOfWeekLookup.getOrDefault(dayOfWeekInNepali, 0);

        return BikramSambat.of(y, m, d, dd);
    }

    public static BikramSambat from(LocalDate localDate) {
        return BsAdConverter.localDateIntoBs(localDate);
    }

    // +++++ factory methods ends here ++++++++

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public String getNepaliString() {
        return nepaliString;
    }

    public String getDnYear() {
        return dnYear;
    }

    public String getDnMonth() {
        return dnMonth;
    }

    public String getDnDay() {
        return dnDay;
    }

    public String getDnDayOfWeek() {
        return dnDayOfWeek;
    }

    @Override
    public String toString() {
        return "BikramSambat{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", dayOfWeek=" + dayOfWeek +
                ", dnYear='" + dnYear + '\'' +
                ", dnMonth='" + dnMonth + '\'' +
                ", dnDay='" + dnDay + '\'' +
                ", dnDayOfWeek='" + dnDayOfWeek + '\'' +
                '}';
    }

    public String toNepaliString() {
        return nepaliString;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BikramSambat nepDate = (BikramSambat) obj;
        return this.intVal() == nepDate.intVal();
    }

    @Override
    public int hashCode() {
        return intVal();
    }

    @Override
    public int compareTo(Object o) {
        BikramSambat other = null;
        if (o instanceof BikramSambat) {
            other = (BikramSambat) o;
        } else {
            throw new RuntimeException("o is not an instance of BikramSambat.");
        }
        int val = this.intVal();
        int otherVal = other.intVal();

        return val < otherVal ? -1 : (val == otherVal ? 0 : 1);
    }

    private int intVal() {
        String str = "" + year + (month < 10 ? "0" + month : month) + (day < 10 ? "0" + day : day);
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
//        System.out.println(BikramSambat.of("२०७५", "जेठ", "१३", "आइतवार").toNepaliString());
//        System.out.println(BikramSambat.today().toNepaliString());

//        Instant now = Instant.now();
//        ZonedDateTime nepaliTime = now.atZone(ZoneId.of("GMT+05:45"));
//        System.out.println(nepaliTime.getDayOfWeek());


//        Instant now = Instant.now();
//        ZonedDateTime nepaliTime = now.atZone(ZoneId.of("GMT+05:45"));
//        ZonedDateTime nepaliTimeYesterday = nepaliTime.minusDays(1);
//        System.out.println(BsAdConverter.localDateIntoBs(nepaliTimeYesterday));

        System.out.println(BikramSambat.yesterday());
    }
}