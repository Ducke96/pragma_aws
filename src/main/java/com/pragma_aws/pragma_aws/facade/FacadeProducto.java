package com.pragma_aws.pragma_aws.facade;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Producto;

import java.util.List;
import java.util.Optional;


public interface FacadeProducto {

    public Producto saveProducto(Producto producto);
    public Producto updateProducto(Producto producto);
    public Optional<Producto> getProducto(int id);
    public List<Producto> getProducto();
    public Boolean deleteProducto(int id);
    List<Producto> findProductosConMayorStockPorSucursal(int idFranquicia);
}
