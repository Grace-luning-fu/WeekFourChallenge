package me.grace.w3resumedb.repositories;

import me.grace.w3resumedb.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person,Long> {
}
