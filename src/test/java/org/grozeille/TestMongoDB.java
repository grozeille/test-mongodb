package org.grozeille;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.grozeille.dal.InvoiceRepository;
import org.grozeille.dal.PeopleRepository;
import org.grozeille.dal.ValueObject;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestMongoDB {
	
	private static Logger logger = LoggerFactory.getLogger(TestMongoDB.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	@Test
	public void test(){
		People first = new People();
		first.setFirstname("Mathias");
		first.setLastname("Kluba");
		Address address = new Address();
		address.setCity("Issy Les Moulineaux");
		address.setCountry("France");
		address.setStreet("32 rue d'Erevan");		
		first.setAddresses(new ArrayList<Address>());
		first.getAddresses().add(address);
		
		first =  peopleRepository.save(first);
		
		List<People> toSave = new ArrayList<People>();
		
		for(int cpt = 0; cpt < 10; cpt++){
			People p = new People();
			
			if(cpt % 2 == 0){
				p.setFirstname("Mathias");
				p.setLastname("Kluba");
				Address a = new Address();
				a.setCity("Issy Les Moulineaux");
				a.setCountry("France");
				a.setStreet("32 rue d'Erevan");
				p.setAddresses(Arrays.asList(a));
			}
			else {
				p.setLastname("Michel");
				p.setLastname("Kluba");
				Address a = new Address();
				a.setCity("Leucat");
				a.setCountry("France");
				a.setStreet("Unknown");
				p.setAddresses(Arrays.asList(a));
			}
			
			toSave.add(p);
		}
		
		peopleRepository.save(toSave);
		
		Address newAddress = new Address();
		newAddress.setCity("Rodez");
		newAddress.setCountry("France");
		newAddress.setStreet("26 rue du 18 Juin");
		first.getAddresses().add(newAddress);
		
		this.peopleRepository.save(first);
		
		Invoice i = new Invoice();
		i.setDate(new LocalDate(2012, 02, 10));
		i.setPrice(48.5);
		i.setCustomer(first);
		
		invoiceRepository.save(i);
		
		for(Invoice invoice : invoiceRepository.findAll()){
			logger.info("Found: {}", ToStringBuilder.reflectionToString(invoice));
		}
		
		List<People> found = peopleRepository.findByFirstnameAndLastname("Mathias", "Kluba");
		logger.info("Found {} Mathias Kluba", found.size());
		
		MapReduceResults<ValueObject> mapReduceResult = peopleRepository.sumByCity();
		
		for(ValueObject s : mapReduceResult){
			logger.info("MapReduce : {}", ToStringBuilder.reflectionToString(s));
		}

		try {
			throw new Exception();
		} catch (Exception e) {
			logger.error("Error", e);
		}
	}
}
