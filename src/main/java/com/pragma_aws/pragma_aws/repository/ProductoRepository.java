package com.pragma_aws.pragma_aws.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Producto;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

        @Query("SELECT p FROM Producto p WHERE p.sucursal.idSucursal IN (" +
           "SELECT s.idSucursal FROM Sucursal s WHERE s.franquicia.idFranquicia = :idFranquicia" +
           ") AND p.stock = (" +
           "SELECT MAX(p2.stock) FROM Producto p2 WHERE p2.sucursal.idSucursal = p.sucursal.idSucursal" +
           ")")
    List<Producto> findProductosConMayorStockPorSucursal(int idFranquicia);
}
