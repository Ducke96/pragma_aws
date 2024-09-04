package com.pragma_aws.pragma_aws.controller.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Sucursal;
import com.pragma_aws.pragma_aws.controller.dto.SucursalDTO;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)

public class FranquiciaDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private int id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nombre")
    private String nombre;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sucursales")
    private List<SucursalDTO> sucursalDTO;

    public FranquiciaDTO(){}

    public FranquiciaDTO(Franquicia franquicia){
        this.id = franquicia.getIdFranquicia();
        this.nombre = franquicia.getNombre();
        if(franquicia.getSucursales() != null && !franquicia.getSucursales().isEmpty()){
        List<SucursalDTO> sucursalDTOlt = new ArrayList<>(); 
        for (Sucursal sucursal : franquicia.getSucursales()) {
            SucursalDTO suc = new SucursalDTO();
            suc.setId(sucursal.getIdSucursal());
            suc.setNombre(sucursal.getNombre());
            sucursalDTOlt.add(suc);
        }
        this.sucursalDTO=sucursalDTOlt;
    }
        
    }

}
