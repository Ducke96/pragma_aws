package com.pragma_aws.pragma_aws.controller.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Producto;
import com.pragma_aws.pragma_aws.repository.maptables.Sucursal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private int id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nombre")
    private String nombre;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stock")
    private int stock;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Sucursal")
    private SucursalDTO sucursal;

    public ProductoDTO(){}

    public ProductoDTO(Producto producto){
    this.id = producto.getIdproducto();
    this.nombre = producto.getNombre();
    this.stock = producto.getStock();
    if(producto.getSucursal() != null){
        SucursalDTO sucursalDTO = new SucursalDTO(producto.getSucursal());
        this.sucursal = sucursalDTO;
    }else{
        this.sucursal = null;
    }

  

    }
    
    
}
