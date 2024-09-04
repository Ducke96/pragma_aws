package com.pragma_aws.pragma_aws.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Producto;
import com.pragma_aws.pragma_aws.repository.maptables.Sucursal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pragma_aws.pragma_aws.facade.FacadeFranquicia;
import com.pragma_aws.pragma_aws.facade.FacadeSucursal;
import com.pragma_aws.pragma_aws.controller.dto.ProductoDTO;
import com.pragma_aws.pragma_aws.controller.dto.SucursalDTO;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "sucursal")
@CrossOrigin(origins = "*")
public class SucursalController {
    private FacadeSucursal facadeSucursal;
    private FacadeFranquicia facadeFranquicia;

    @Autowired
    public SucursalController(FacadeSucursal facadeSucursal ,FacadeFranquicia facadeFranquicia){
    this.facadeSucursal = facadeSucursal;
    this.facadeFranquicia = facadeFranquicia;
    }
    



@GetMapping("/{id}")
public ResponseEntity<Object> getSucursal(@PathVariable int id) {

    Optional<Sucursal> optionalSucursal =facadeSucursal.getSucursal(id);
    if (!optionalSucursal.isPresent()) {
        return new ResponseEntity<>("Sucursal No encontrado", HttpStatus.NOT_FOUND);
        }
    SucursalDTO sucursal = new SucursalDTO(optionalSucursal.get());
    return new ResponseEntity<>(sucursal,HttpStatus.OK );

}

@GetMapping
public ResponseEntity<List<SucursalDTO>> getAllSucursales() {

List<Sucursal> sucursales = facadeSucursal.getSucursal();
List<SucursalDTO> sucursalDTOs = sucursales.stream()
                                       .map(SucursalDTO::new)
                                       .collect(Collectors.toList());
return new ResponseEntity<>(sucursalDTOs, HttpStatus.OK);
}

@PostMapping
public ResponseEntity<Object> saveSucursal(@RequestBody SucursalDTO sucursalDTO) {
Sucursal sucursal = new Sucursal();

if(sucursalDTO.getNombre() == null || sucursalDTO.getNombre().isEmpty()){
    return new ResponseEntity<>("nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
}

Optional<Franquicia> optionalFranquicia =facadeFranquicia.getFranquicia(sucursalDTO.getFranquicia().getId());
if (!optionalFranquicia.isPresent()) {

    return new ResponseEntity<>("Franquicia no encontrda", HttpStatus.NOT_FOUND);
}
sucursal.setNombre(sucursalDTO.getNombre());
Franquicia franquicia = optionalFranquicia.get();
sucursal.setFranquicia(franquicia);
Sucursal savedSucursal = facadeSucursal.saveSucursal(sucursal);
SucursalDTO savedSucursalDTO = new SucursalDTO(savedSucursal);

return new ResponseEntity<>(savedSucursalDTO, HttpStatus.CREATED);
}

@DeleteMapping("/{id}")
public ResponseEntity<Object> deleteSucursal(@PathVariable int id) {
boolean isRemoved = facadeSucursal.deleteSucursal(id);
if (isRemoved) {
    return new ResponseEntity<>("Sucursal eliminada",HttpStatus.NO_CONTENT);
} else {
    return new ResponseEntity<>("Sucursal no encontrada",HttpStatus.NOT_FOUND);
}
}


@PatchMapping
public ResponseEntity<Object> updateSucursal(@RequestBody SucursalDTO sucursalDTO) {

if(sucursalDTO.getNombre() == null || sucursalDTO.getNombre().isEmpty()){
        return new ResponseEntity<>("nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
}

Optional<Sucursal> optionalSucursal =facadeSucursal.getSucursal(sucursalDTO.getId());
if (!optionalSucursal.isPresent()) {

    return new ResponseEntity<>("sucursal no encontrado", HttpStatus.NOT_FOUND);
}

Sucursal sucursal1 = optionalSucursal.get();
sucursal1.setNombre(sucursalDTO.getNombre());

Sucursal savedSucursal = facadeSucursal.updateSucursal(sucursal1);
SucursalDTO savedSucursalDTO = new SucursalDTO(savedSucursal);
return new ResponseEntity<>(savedSucursalDTO, HttpStatus.OK);
}



}
