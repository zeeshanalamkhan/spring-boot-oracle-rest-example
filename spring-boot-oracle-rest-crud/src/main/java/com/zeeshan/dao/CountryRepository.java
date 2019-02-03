package com.zeeshan.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zeeshan.entity.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

}
