package me.grace.w3resumedb.repositories;

import me.grace.w3resumedb.Education;
import me.grace.w3resumedb.Experience;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepo extends CrudRepository<Education,Long> {

}
