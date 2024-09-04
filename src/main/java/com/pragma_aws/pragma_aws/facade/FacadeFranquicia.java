package com.pragma_aws.pragma_aws.facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Producto;

import java.util.List;
import java.util.Optional;


public interface FacadeFranquicia {   
    
    public Franquicia saveFranquicia(Franquicia franquicia);
    public Optional<Franquicia> getFranquicia(int id);
    public List<Franquicia> getFranquicias();
    public Boolean deleteFranquicia(int id);
    public Franquicia updateProducto(Franquicia franquicia);

}
