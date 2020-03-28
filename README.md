# bikram-sambat

![Build](https://github.com/tilakthapa/bikram-sambat/workflows/Build/badge.svg)

My primary use case - 
Convert Bikram Sambat date written in Nepali (devanagari script) e.g. "१३ चैत २०७६" into Java 8 LocalDate and vice versa.

```
// create BS date from Devanagari inputs
BikramSambat bs = BikramSambat.of("२०७६", "चैत", "१३");
System.out.println(bs);
// BikramSambat{year=2076, month=12, dayOfMonth=13, dnYear='२०७६', dnMonth='चैत', dnDayOfMonth='१३'}
System.out.println(bs.toDnString());
// १३ चैत, २०७६

// convert BS into AD
LocalDate localDateFromBs = bs.toLocalDate();
System.out.println(localDateFromBs);
// 2020-03-26

BikramSambat bs1 = BikramSambat.of("२०७६", "१२", "१३");
System.out.println(bs1);
// BikramSambat{year=2076, month=12, dayOfMonth=13, dnYear='२०७६', dnMonth='चैत', dnDayOfMonth='१३'}
System.out.println(bs1.toDnString());
// १३ चैत, २०७६

// create BS date from LocalDate
BikramSambat bsFromLocalDate = BikramSambat.from(LocalDate.of(2020, 3, 26));
System.out.println(bsFromLocalDate);
// BikramSambat{year=2076, month=12, dayOfMonth=13, dnYear='२०७६', dnMonth='चैत', dnDayOfMonth='१३'}
System.out.println(bsFromLocalDate.toDnString()); 
// १३ चैत, २०७६
```

References:

Thanks to the authors of
- https://github.com/bahadurbaniya/Date-Converter-Bikram-Sambat-to-English-Date
- https://github.com/medic/bikram-sambat
