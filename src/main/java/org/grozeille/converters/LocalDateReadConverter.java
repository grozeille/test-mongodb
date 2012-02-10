package org.grozeille.converters;

import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("localDateReadConverter")
public class LocalDateReadConverter implements Converter<Date, LocalDate> {

	public LocalDate convert(Date source) {

		if (source == null) {
			return null;
		}

		return new LocalDate(source);
	}

}
