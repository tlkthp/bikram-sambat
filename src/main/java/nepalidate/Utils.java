package nepalidate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Utils {

    private final static Map<Character, Integer> nepaliDigitToIntLookup = new HashMap<Character, Integer>() {{
        put('०', 0);
        put('१', 1);
        put('२', 2);
        put('३', 3);
        put('४', 4);
        put('५', 5);
        put('६', 6);
        put('७', 7);
        put('८', 8);
        put('९', 9);
    }};

    /**
     * Convert Nepali digit into english digit.
     *
     * @param nepaliDigit
     * @return
     */
    public static int nepaliDigitToInt(String nepaliDigit) {
        if (!isValidNepaliDigit(nepaliDigit)) {
            throw new RuntimeException(nepaliDigit + " is not a valid Nepali digit. Should be one of " + nepaliDigitToIntLookup.keySet());
        }

        String str = nepaliDigit.chars()
                                .mapToObj(c -> (char) c)
                                .map(dd -> nepaliDigitToIntLookup.get(dd))
                                .map(num -> Integer.toString(num))
                                .reduce("", (first, second) -> first + second);
        return Integer.parseInt(str);
    }

    public static int nepaliDigitToInt_j7(String nepaliDigit) {
        if (!isValidNepaliDigit_j7(nepaliDigit)) {
            throw new RuntimeException(nepaliDigit + " is not a valid Nepali digit. Should be one of " + nepaliDigitToIntLookup.keySet());
        }

        char[] chars = nepaliDigit.toCharArray();

        String num = "";
        for(char c : chars) {
            Integer intVal = nepaliDigitToIntLookup.get(c);
            num += Integer.toString(intVal);
        }

        return Integer.parseInt(num);
    }

    public static boolean isValidNepaliDigit(String nepaliDigit) {
        return nepaliDigit.chars()
                          .mapToObj(c -> (char) c)
                          .allMatch(ch -> nepaliDigitToIntLookup.keySet().contains(ch));
    }

    public static boolean isValidNepaliDigit_j7(String nepaliDigit) {
        char[] chars = nepaliDigit.toCharArray();
        for(char c : chars) {
            if(!nepaliDigitToIntLookup.keySet().contains(c)) {
                return false;
            }
        }

        return true;
    }

    public static String intToNepaliDigit(int digit) {
        String devnagariDigit = Integer.toString(digit);
        Map<Integer, Character> lookup = reverse(nepaliDigitToIntLookup);
        return devnagariDigit.chars()
                             .map(num -> lookup.get(Character.getNumericValue(num)))
                             .mapToObj(c -> (char) c) // map to object stream
                             .map(ch -> Character.toString(ch)) // convert chars into strings
                             .reduce("", (f, s) -> f + s);

    }

    public static String intToNepaliDigit_j7(int digit) {
        Map<Integer, Character> lookup = reverse_j7(nepaliDigitToIntLookup);

        String nepaliDigit = "";
        for(char c : Integer.toString(digit).toCharArray()) {
            Character chr = lookup.get(Character.getNumericValue(c));
            String s = Character.toString(chr);
            nepaliDigit += s;
        }

        return nepaliDigit;
    }

    private static <V, K> Map<V, K> reverse(Map<K, V> lookup) {
        Map<V, K> reverse = lookup.entrySet().stream()
                                  .collect(Collectors.toMap(entry -> entry.getValue(), entry -> entry.getKey(), (old, n) -> old));
        return reverse;
    }

    private static <V, K> Map<V, K> reverse_j7(Map<K, V> lookup) {
        Map<V, K> reverse = new HashMap<>();
        for(K key : lookup.keySet()) {
            reverse.put(lookup.get(key), key);
        }
        return reverse;
    }

    public static void main(String[] args) {
        System.out.println("Valid ? " + isValidNepaliDigit_j7("९९९९९९९"));
        System.out.println(nepaliDigitToInt_j7("९९९९९९९"));
        System.out.println(intToNepaliDigit_j7(1234567890));
        ;
    }

}
