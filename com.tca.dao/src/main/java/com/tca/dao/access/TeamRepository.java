package com.tca.dao.access;

import org.springframework.data.repository.CrudRepository;

import com.tca.dao.model.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}
