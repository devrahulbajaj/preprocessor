package com.example.preprocessor.repo;

import java.util.List;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.example.preprocessor.model.Schedule;;
 
@ViewIndexed(designDoc = "schedule")
public interface PreprocessorRepository extends CouchbaseRepository<Schedule, String> {
	
	List<Schedule> findByflightNumber(String number);
}
