package nepalidate;

import java.util.Arrays;

public enum NepaliDayOfWeek {
    MONDAY("सोमवार", 1),
    TUESDAY("मङ्गलवार", 2),
    WEDNESDAY("बुधवार", 3),
    THURSDAY("बिहिवार", 4),
    FRIDAY("शुक्रवार", 5),
    SATURDAY("शनिवार", 6),
    SUNDAY("आइतवार", 7);

    private String nepName;
    private int intValue;

    NepaliDayOfWeek(String nepName, int intValue) {
        this.nepName = nepName;
        this.intValue = intValue;
    }

    public String getNepName() {
        return nepName;
    }

    public int getIntValue() {
        return intValue;
    }

    public static NepaliDayOfWeek fromIntValue(int intValue) {
        return Arrays.stream(NepaliDayOfWeek.values())
                     .filter(nepaliDayOfWeek -> nepaliDayOfWeek.intValue == intValue)
                     .findFirst()
                     .orElseThrow(() -> new RuntimeException("Invalid intValue = " + intValue));
    }
}
