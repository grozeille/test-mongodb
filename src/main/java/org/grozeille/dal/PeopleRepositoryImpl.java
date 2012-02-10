package org.grozeille.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;

public class PeopleRepositoryImpl implements PeopleRepositoryCustom {

	private MongoTemplate mongoTemplate;
	
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public MapReduceResults<ValueObject> sumByCity() {
		
		return mongoTemplate.mapReduce("people", "classpath:map.js", "classpath:reduce.js", ValueObject.class);
	}
	

}
