package com.reader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.reader.model.ArnatiSteelLimited;

@Repository
public interface ExcelRepository extends CrudRepository<ArnatiSteelLimited, Integer>{

}
