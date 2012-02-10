package org.grozeille;

import org.bson.types.ObjectId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Invoice {

	@Id
	private ObjectId id;

	private LocalDate date;

	@DBRef
	private People customer;

	private Double price;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public People getCustomer() {
		return customer;
	}

	public void setCustomer(People customer) {
		this.customer = customer;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
