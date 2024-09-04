package com.pragma_aws.pragma_aws.facade.serviceimpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pragma_aws.pragma_aws.facade.FacadeFranquicia;
import com.pragma_aws.pragma_aws.facade.FacadeSucursal;
import com.pragma_aws.pragma_aws.repository.SucursalRepository;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Producto;
import com.pragma_aws.pragma_aws.repository.maptables.Sucursal;

@Service
public class FacadeSucursalimpl implements FacadeSucursal{
    private SucursalRepository sucursalRepository;

    @Autowired
    public FacadeSucursalimpl(SucursalRepository sucursalRepository){
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public Sucursal saveSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Optional<Sucursal> getSucursal(int id) {
        return sucursalRepository.findById(id);
    }

    @Override
    public List<Sucursal> getSucursal() {
           return StreamSupport.stream(sucursalRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteSucursal(int id) {
        if (sucursalRepository.existsById(id)) {
            sucursalRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Sucursal updateSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    
}
