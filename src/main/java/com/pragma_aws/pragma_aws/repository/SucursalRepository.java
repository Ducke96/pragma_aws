package com.pragma_aws.pragma_aws.repository;
import org.springframework.data.repository.CrudRepository;

import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Sucursal;

import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends CrudRepository<Sucursal, Integer>{
    
}
