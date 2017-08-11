package me.grace.w3resumedb.repositories;

import me.grace.w3resumedb.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepo extends CrudRepository<Skill,Long> {
}