package org.grozeille.dal;

import java.util.List;

import org.bson.types.ObjectId;
import org.grozeille.People;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PeopleRepository extends PagingAndSortingRepository<People, ObjectId>, PeopleRepositoryCustom {
	
	List<People> findByFirstnameAndLastname(String firstname, String lastname);
}
