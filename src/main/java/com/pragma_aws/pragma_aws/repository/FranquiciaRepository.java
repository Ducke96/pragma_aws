package com.pragma_aws.pragma_aws.repository;
import org.springframework.data.repository.CrudRepository;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;

import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends CrudRepository<Franquicia, Integer> {


    
}