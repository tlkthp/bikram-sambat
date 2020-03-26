package com.di.utils.bs;

public abstract class Utils {

    public static boolean isValidDnDigit(String nepaliDigit) {
        for (int len = nepaliDigit.length(), i = 0; i < len; ++i) {
            char c = nepaliDigit.charAt(i);
            // ० - ९
            // 2406 - 2415
            boolean validDigit = c >= 2406 && c < 2415;
            if (!validDigit) return false;
        }
        return true;
    }

    /**
     * Converts integer to Devanagari digits e.g. 987654321 to ९८७६५४३२१
     *
     * @param intVal
     * @return
     */
    public static String intToDnDigit(int intVal) {
        CharSequence from = Integer.toString(intVal);

        StringBuilder bob = new StringBuilder();
        for (int len = from.length(), i = 0; i < len; ++i) {
            char c = from.charAt(i);
            if (c >= 48 && c < 58) c += 2358;
            bob.append(c);
        }
        return bob.toString();
    }

    /**
     * Converts Devanagari digit to integer e.g. ९८७६५४३२१ to 987654321
     *
     * @param dnDigit
     * @return
     */
    public static int dnDigitToInt(CharSequence dnDigit) {
        StringBuilder sb = new StringBuilder();
        for (int len = dnDigit.length(), i = 0; i < len; ++i) {
            char c = dnDigit.charAt(i);
            // ० - ९
            // 2406 - 2415
            if (c >= 2406 && c < 2415) c -= 2358;
            sb.append(c);
        }

        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {

        System.out.println(intToDnDigit(987654321));
        System.out.println(intToDnDigit(987654321));

        System.out.println(dnDigitToInt("९८७६५४३२१"));

        char c = '०';
        // ० - ९
        // 2406 - 2415
        System.out.println((int) c);
    }

}
