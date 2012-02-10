package org.grozeille.converters;

import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("localDateWirteConverter")
public class LocalDateWriteConverter implements Converter<LocalDate, Date>{

	public Date convert(LocalDate source) {
		if(source == null){
			return null;
		}
		
		return source.toDate();
	}

}
