package com.pragma_aws.pragma_aws.facade.serviceimpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pragma_aws.pragma_aws.facade.FacadeFranquicia;
import com.pragma_aws.pragma_aws.facade.FacadeProducto;
import com.pragma_aws.pragma_aws.repository.ProductoRepository;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Producto;

@Service
public class FacadeProductoimpl implements FacadeProducto{
    
    private ProductoRepository productoRepository;

    @Autowired
    public FacadeProductoimpl(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> getProducto(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> getProducto() {
           return StreamSupport.stream(productoRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteProducto(int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Producto updateProducto(Producto producto) {
        return productoRepository.save(producto);
    }


    public List<Producto> findProductosConMayorStockPorSucursal(int idFranquicia){
        return productoRepository.findProductosConMayorStockPorSucursal(idFranquicia);
    }

}
