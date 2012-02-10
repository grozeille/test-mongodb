package org.grozeille.dal;

import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;

public interface PeopleRepositoryCustom {
	MapReduceResults<ValueObject> sumByCity();
}
