package com.demo.javase.jdk8.lambda.date;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Test1 {
	public static void main(String[] args) {
		Clock clock=Clock.systemDefaultZone();
		long millis=clock.millis();
		Instant instant=clock.instant();
		Date legacyDate=Date.from(instant);

		System.out.println(ZoneId.getAvailableZoneIds());
// prints all available timezone ids
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());

		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late);       // 23:59:59
		DateTimeFormatter germanFormatter =
				DateTimeFormatter
						.ofLocalizedTime(FormatStyle.SHORT)
						.withLocale(Locale.GERMAN);
		LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
		System.out.println(leetTime);   // 13:37
	}
}
