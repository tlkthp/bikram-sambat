package nepalidate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

class BsAdConverter {

    private static Map<Integer, Integer[]> bsMonthsDaysLookup = new HashMap<Integer, Integer[]>() {{
        put(1970, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1971, new Integer[]{31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        put(1972, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        put(1973, new Integer[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        put(1974, new Integer[]{31, 31, 32, 30, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1975, new Integer[]{31, 31, 32, 32, 30, 31, 30, 29, 30, 29, 30, 30});
        put(1976, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(1977, new Integer[]{30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31});
        put(1978, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1979, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(1980, new Integer[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(1981, new Integer[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        put(1982, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1983, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(1984, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(1985, new Integer[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        put(1986, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1987, new Integer[]{31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(1988, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(1989, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1990, new Integer[]{30, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1991, new Integer[]{31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(1992, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 30});
        put(1993, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1994, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1995, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1996, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1997, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1998, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(1999, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2000, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 29, 31});
        put(2001, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2002, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2003, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2004, new Integer[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        put(2005, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2006, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2007, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2008, new Integer[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        put(2009, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2010, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2011, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2012, new Integer[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        put(2013, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2014, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2015, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2016, new Integer[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        put(2017, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2018, new Integer[]{31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2019, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        put(2020, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2021, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2022, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        put(2023, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        put(2024, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2025, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2026, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2027, new Integer[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        put(2028, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2029, new Integer[]{31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        put(2030, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2031, new Integer[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        put(2032, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2033, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2034, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2035, new Integer[]{30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        put(2036, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2037, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2038, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2039, new Integer[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        put(2040, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2041, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2042, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2043, new Integer[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        put(2044, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2045, new Integer[]{31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2046, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2047, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2048, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2049, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        put(2050, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        put(2051, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2052, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2053, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        put(2054, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        put(2055, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2056, new Integer[]{31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        put(2057, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2058, new Integer[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        put(2059, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2060, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2061, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2062, new Integer[]{30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31});
        put(2063, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2064, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2065, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2066, new Integer[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        put(2067, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2068, new Integer[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2069, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2070, new Integer[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        put(2071, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2072, new Integer[]{31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2073, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        put(2074, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2075, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2076, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        put(2077, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        put(2078, new Integer[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2079, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        put(2080, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        put(2081, new Integer[]{31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2082, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2083, new Integer[]{31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2084, new Integer[]{31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2085, new Integer[]{31, 32, 31, 32, 31, 31, 30, 30, 29, 30, 30, 30});
        put(2086, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2087, new Integer[]{31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30});
        put(2088, new Integer[]{30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30});
        put(2089, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2090, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2091, new Integer[]{31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30});
        put(2092, new Integer[]{31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2093, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2094, new Integer[]{31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2095, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 30, 30, 30});
        put(2096, new Integer[]{30, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        put(2097, new Integer[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        put(2098, new Integer[]{31, 31, 32, 31, 31, 31, 29, 30, 29, 30, 30, 31});
        put(2099, new Integer[]{31, 31, 32, 31, 31, 31, 30, 29, 29, 30, 30, 30});
        put(2100, new Integer[]{31, 32, 31, 32, 30, 31, 30, 29, 30, 29, 30, 30});
    }};

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private static Map<Integer, LocalDate> bsToAdNewYearDateLookup = new HashMap<Integer, LocalDate>() {{
        put(1970, LocalDate.parse("13-Apr-1913", FORMATTER));
        put(1971, LocalDate.parse("13-Apr-1914", FORMATTER));
        put(1972, LocalDate.parse("13-Apr-1915", FORMATTER));
        put(1973, LocalDate.parse("13-Apr-1916", FORMATTER));
        put(1974, LocalDate.parse("13-Apr-1917", FORMATTER));
        put(1975, LocalDate.parse("12-Apr-1918", FORMATTER));
        put(1976, LocalDate.parse("13-Apr-1919", FORMATTER));
        put(1977, LocalDate.parse("13-Apr-1920", FORMATTER));
        put(1978, LocalDate.parse("13-Apr-1921", FORMATTER));
        put(1979, LocalDate.parse("13-Apr-1922", FORMATTER));
        put(1980, LocalDate.parse("13-Apr-1923", FORMATTER));
        put(1981, LocalDate.parse("13-Apr-1924", FORMATTER));
        put(1982, LocalDate.parse("13-Apr-1925", FORMATTER));
        put(1983, LocalDate.parse("13-Apr-1926", FORMATTER));
        put(1984, LocalDate.parse("13-Apr-1927", FORMATTER));
        put(1985, LocalDate.parse("13-Apr-1928", FORMATTER));
        put(1986, LocalDate.parse("13-Apr-1929", FORMATTER));
        put(1987, LocalDate.parse("13-Apr-1930", FORMATTER));
        put(1988, LocalDate.parse("13-Apr-1931", FORMATTER));
        put(1989, LocalDate.parse("13-Apr-1932", FORMATTER));
        put(1990, LocalDate.parse("13-Apr-1933", FORMATTER));
        put(1991, LocalDate.parse("13-Apr-1934", FORMATTER));
        put(1992, LocalDate.parse("13-Apr-1935", FORMATTER));
        put(1993, LocalDate.parse("13-Apr-1936", FORMATTER));
        put(1994, LocalDate.parse("13-Apr-1937", FORMATTER));
        put(1995, LocalDate.parse("13-Apr-1938", FORMATTER));
        put(1996, LocalDate.parse("13-Apr-1939", FORMATTER));
        put(1997, LocalDate.parse("13-Apr-1940", FORMATTER));
        put(1998, LocalDate.parse("13-Apr-1941", FORMATTER));
        put(1999, LocalDate.parse("13-Apr-1942", FORMATTER));
        put(2000, LocalDate.parse("14-Apr-1943", FORMATTER));
        put(2001, LocalDate.parse("13-Apr-1944", FORMATTER));
        put(2002, LocalDate.parse("13-Apr-1945", FORMATTER));
        put(2003, LocalDate.parse("13-Apr-1946", FORMATTER));
        put(2004, LocalDate.parse("14-Apr-1947", FORMATTER));
        put(2005, LocalDate.parse("13-Apr-1948", FORMATTER));
        put(2006, LocalDate.parse("13-Apr-1949", FORMATTER));
        put(2007, LocalDate.parse("13-Apr-1950", FORMATTER));
        put(2008, LocalDate.parse("14-Apr-1951", FORMATTER));
        put(2009, LocalDate.parse("13-Apr-1952", FORMATTER));
        put(2010, LocalDate.parse("13-Apr-1953", FORMATTER));
        put(2011, LocalDate.parse("13-Apr-1954", FORMATTER));
        put(2012, LocalDate.parse("14-Apr-1955", FORMATTER));
        put(2013, LocalDate.parse("13-Apr-1956", FORMATTER));
        put(2014, LocalDate.parse("13-Apr-1957", FORMATTER));
        put(2015, LocalDate.parse("13-Apr-1958", FORMATTER));
        put(2016, LocalDate.parse("14-Apr-1959", FORMATTER));
        put(2017, LocalDate.parse("13-Apr-1960", FORMATTER));
        put(2018, LocalDate.parse("13-Apr-1961", FORMATTER));
        put(2019, LocalDate.parse("13-Apr-1962", FORMATTER));
        put(2020, LocalDate.parse("14-Apr-1963", FORMATTER));
        put(2021, LocalDate.parse("13-Apr-1964", FORMATTER));
        put(2022, LocalDate.parse("13-Apr-1965", FORMATTER));
        put(2023, LocalDate.parse("13-Apr-1966", FORMATTER));
        put(2024, LocalDate.parse("14-Apr-1967", FORMATTER));
        put(2025, LocalDate.parse("13-Apr-1968", FORMATTER));
        put(2026, LocalDate.parse("13-Apr-1969", FORMATTER));
        put(2027, LocalDate.parse("14-Apr-1970", FORMATTER));
        put(2028, LocalDate.parse("14-Apr-1971", FORMATTER));
        put(2029, LocalDate.parse("13-Apr-1972", FORMATTER));
        put(2030, LocalDate.parse("13-Apr-1973", FORMATTER));
        put(2031, LocalDate.parse("14-Apr-1974", FORMATTER));
        put(2032, LocalDate.parse("14-Apr-1975", FORMATTER));
        put(2033, LocalDate.parse("13-Apr-1976", FORMATTER));
        put(2034, LocalDate.parse("13-Apr-1977", FORMATTER));
        put(2035, LocalDate.parse("14-Apr-1978", FORMATTER));
        put(2036, LocalDate.parse("14-Apr-1979", FORMATTER));
        put(2037, LocalDate.parse("13-Apr-1980", FORMATTER));
        put(2038, LocalDate.parse("13-Apr-1981", FORMATTER));
        put(2039, LocalDate.parse("14-Apr-1982", FORMATTER));
        put(2040, LocalDate.parse("14-Apr-1983", FORMATTER));
        put(2041, LocalDate.parse("13-Apr-1984", FORMATTER));
        put(2042, LocalDate.parse("13-Apr-1985", FORMATTER));
        put(2043, LocalDate.parse("14-Apr-1986", FORMATTER));
        put(2044, LocalDate.parse("14-Apr-1987", FORMATTER));
        put(2045, LocalDate.parse("13-Apr-1988", FORMATTER));
        put(2046, LocalDate.parse("13-Apr-1989", FORMATTER));
        put(2047, LocalDate.parse("14-Apr-1990", FORMATTER));
        put(2048, LocalDate.parse("14-Apr-1991", FORMATTER));
        put(2049, LocalDate.parse("13-Apr-1992", FORMATTER));
        put(2050, LocalDate.parse("13-Apr-1993", FORMATTER));
        put(2051, LocalDate.parse("14-Apr-1994", FORMATTER));
        put(2052, LocalDate.parse("14-Apr-1995", FORMATTER));
        put(2053, LocalDate.parse("13-Apr-1996", FORMATTER));
        put(2054, LocalDate.parse("13-Apr-1997", FORMATTER));
        put(2055, LocalDate.parse("14-Apr-1998", FORMATTER));
        put(2056, LocalDate.parse("14-Apr-1999", FORMATTER));
        put(2057, LocalDate.parse("13-Apr-2000", FORMATTER));
        put(2058, LocalDate.parse("14-Apr-2001", FORMATTER));
        put(2059, LocalDate.parse("14-Apr-2002", FORMATTER));
        put(2060, LocalDate.parse("14-Apr-2003", FORMATTER));
        put(2061, LocalDate.parse("13-Apr-2004", FORMATTER));
        put(2062, LocalDate.parse("14-Apr-2005", FORMATTER));
        put(2063, LocalDate.parse("14-Apr-2005", FORMATTER));
        put(2064, LocalDate.parse("14-Apr-2007", FORMATTER));
        put(2065, LocalDate.parse("13-Apr-2008", FORMATTER));
        put(2066, LocalDate.parse("14-Apr-2009", FORMATTER));
        put(2067, LocalDate.parse("14-Apr-2010", FORMATTER));
        put(2068, LocalDate.parse("14-Apr-2011", FORMATTER));
        put(2069, LocalDate.parse("13-Apr-2012", FORMATTER));
        put(2070, LocalDate.parse("14-Apr-2013", FORMATTER));
        put(2071, LocalDate.parse("14-Apr-2014", FORMATTER));
        put(2072, LocalDate.parse("14-Apr-2015", FORMATTER));
        put(2073, LocalDate.parse("13-Apr-2016", FORMATTER));
        put(2074, LocalDate.parse("14-Apr-2017", FORMATTER));
        put(2075, LocalDate.parse("14-Apr-2018", FORMATTER));
        put(2076, LocalDate.parse("14-Apr-2019", FORMATTER));
        put(2077, LocalDate.parse("13-Apr-2020", FORMATTER));
        put(2078, LocalDate.parse("14-Apr-2021", FORMATTER));
        put(2079, LocalDate.parse("14-Apr-2022", FORMATTER));
        put(2080, LocalDate.parse("14-Apr-2023", FORMATTER));
        put(2081, LocalDate.parse("13-Apr-2024", FORMATTER));
        put(2082, LocalDate.parse("14-Apr-2025", FORMATTER));
        put(2083, LocalDate.parse("14-Apr-2026", FORMATTER));
        put(2084, LocalDate.parse("14-Apr-2027", FORMATTER));
        put(2085, LocalDate.parse("13-Apr-2028", FORMATTER));
        put(2086, LocalDate.parse("14-Apr-2029", FORMATTER));
        put(2087, LocalDate.parse("14-Apr-2030", FORMATTER));
        put(2088, LocalDate.parse("15-Apr-2031", FORMATTER));
        put(2089, LocalDate.parse("14-Apr-2032", FORMATTER));
        put(2090, LocalDate.parse("14-Apr-2033", FORMATTER));
        put(2091, LocalDate.parse("14-Apr-2034", FORMATTER));
        put(2092, LocalDate.parse("13-Apr-2035", FORMATTER));
        put(2093, LocalDate.parse("14-Apr-2036", FORMATTER));
        put(2094, LocalDate.parse("14-Apr-2037", FORMATTER));
        put(2095, LocalDate.parse("14-Apr-2038", FORMATTER));
        put(2096, LocalDate.parse("15-Apr-2039", FORMATTER));
        put(2097, LocalDate.parse("13-Apr-2040", FORMATTER));
        put(2098, LocalDate.parse("14-Apr-2041", FORMATTER));
        put(2099, LocalDate.parse("14-Apr-2042", FORMATTER));
        put(2100, LocalDate.parse("14-Apr-2043", FORMATTER));
    }};

    // 2057,13-Apr-2000
    private static final LocalDate adRefDate = LocalDate.of(2000, 4, 13);
    private static final BikramSambat bsRefDate = BikramSambat.of(2057, 1, 1);

    public static BikramSambat localDateIntoBs(ZonedDateTime gDate) {
        long daysElapsed = DAYS.between(adRefDate, gDate);

        return computeBikramSambat(daysElapsed, gDate.getDayOfWeek());

    }

    private static BikramSambat computeBikramSambat(long daysElapsedSinceRefAd, DayOfWeek dayOfWeek) {
        int y = bsRefDate.getYear();
        int m = bsRefDate.getMonth();
        int d = bsRefDate.getDay();

        while (daysElapsedSinceRefAd > 0) {

            int daysInMth = bsMonthsDaysLookup.get(y)[m - 1];

            // calculate total no of days in year y
            Integer daysInYear = Arrays.stream(bsMonthsDaysLookup.get(y))
                                       .reduce(0, (f, s) -> f + s);

            if (daysElapsedSinceRefAd >= daysInYear) {
                // days elapsed is more than no of days in year y.
                y++;
                m = 1;
                d = 1;
                daysElapsedSinceRefAd = daysElapsedSinceRefAd - daysInYear;
            } else if (daysElapsedSinceRefAd >= daysInMth) {
                // days elapsed is more the than no of days in month m
                m++;
                d = 1;

                if (m > 12) {
                    y++;
                    m = 1;
                    d = 1;
                }
                daysElapsedSinceRefAd = daysElapsedSinceRefAd - daysInMth;
            } else {
                d++;
                daysElapsedSinceRefAd--;
            }
        }

        return BikramSambat.of(y, m, d, dayOfWeek.getValue());
    }

    public static BikramSambat localDateIntoBs(LocalDate gDate) {

        // No of days elapsed since the reference AD date
        long daysElapsed = DAYS.between(adRefDate, gDate);

        // Equivalent reference VS year, month & day
        return computeBikramSambat(daysElapsed, gDate.getDayOfWeek());
    }

    public static void main(String[] args) {
        System.out.println(BikramSambat.today().toNepaliString());
        System.out.println(BsAdConverter.localDateIntoBs(LocalDate.of(2018, 6, 24)).toNepaliString());
    }
}
