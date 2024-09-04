package com.pragma_aws.pragma_aws.facade;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Sucursal;

import java.util.List;
import java.util.Optional;


public interface FacadeSucursal {
    public Sucursal saveSucursal(Sucursal sucursal);
    public Optional<Sucursal> getSucursal(int id);
    public List<Sucursal> getSucursal();
    public Boolean deleteSucursal(int id);
}
