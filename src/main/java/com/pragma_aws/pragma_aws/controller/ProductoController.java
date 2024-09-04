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
import com.pragma_aws.pragma_aws.facade.FacadeProducto;
import com.pragma_aws.pragma_aws.facade.FacadeSucursal;
import com.pragma_aws.pragma_aws.controller.dto.ProductoDTO;
import com.pragma_aws.pragma_aws.controller.dto.SucursalDTO;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "producto")
@CrossOrigin(origins = "*")
public class ProductoController {
    private FacadeProducto facadeProducto;
    private FacadeSucursal facadeSucursal;

@Autowired
public ProductoController(FacadeProducto facadeProducto , FacadeSucursal facadeSucursal){
    this.facadeProducto = facadeProducto;
    this.facadeSucursal = facadeSucursal;
}




@GetMapping("/{id}")
public ResponseEntity<Object> getProducto(@PathVariable int id) {

    Optional<Producto> optionalProducto =facadeProducto.getProducto(id);
    if (!optionalProducto.isPresent()) {
        ProductoDTO savedProducto = new ProductoDTO();
        savedProducto.setId(id);
        return new ResponseEntity<>("No encontrado", HttpStatus.NOT_FOUND);
    }
    ProductoDTO producto = new ProductoDTO(optionalProducto.get());
    return new ResponseEntity<>(producto,HttpStatus.OK );

}

@GetMapping
public ResponseEntity<List<ProductoDTO>> getAllProductos() {

List<Producto> productos = facadeProducto.getProducto();
List<ProductoDTO> productoDTOs = productos.stream()
                                       .map(ProductoDTO::new)
                                       .collect(Collectors.toList());
return new ResponseEntity<>(productoDTOs, HttpStatus.OK);
}

@PostMapping
public ResponseEntity<ProductoDTO> saveProducto(@RequestBody ProductoDTO productoDTO) {
Producto producto1 = new Producto();
producto1.setNombre(productoDTO.getNombre());

Optional<Sucursal> optionalSucursal =facadeSucursal.getSucursal(productoDTO.getSucursal().getId());
if (!optionalSucursal.isPresent()) {
    ProductoDTO savedProducto = new ProductoDTO();
    savedProducto.setId(productoDTO.getSucursal().getId());
    return new ResponseEntity<>(savedProducto, HttpStatus.NOT_FOUND);
}

Sucursal sucursal = optionalSucursal.get();
producto1.setSucursal(sucursal);
producto1.setStock(productoDTO.getStock());
Producto savedProducto = facadeProducto.saveProducto(producto1);
ProductoDTO savedProductoDTO = new ProductoDTO(savedProducto);

return new ResponseEntity<>(savedProductoDTO, HttpStatus.CREATED);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteProducto(@PathVariable int id) {
boolean isRemoved = facadeProducto.deleteProducto(id);
if (isRemoved) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
} else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}

@PatchMapping
public ResponseEntity<ProductoDTO> updateProducto(@RequestBody ProductoDTO productoDTO) {

Optional<Producto> optionalProducto =facadeProducto.getProducto(productoDTO.getId());
if (!optionalProducto.isPresent()) {
    ProductoDTO savedProducto = new ProductoDTO();
    savedProducto.setId(productoDTO.getSucursal().getId());
    return new ResponseEntity<>(savedProducto, HttpStatus.NOT_FOUND);
}

Producto producto1 = optionalProducto.get();
producto1.setStock(productoDTO.getStock());
Producto savedProducto = facadeProducto.updateProducto(producto1);
ProductoDTO savedProductoDTO = new ProductoDTO(savedProducto);
return new ResponseEntity<>(savedProductoDTO, HttpStatus.CREATED);
}


@GetMapping("/ProductosMaxStock/{id}")
public ResponseEntity<List<ProductoDTO>> getAllProductosFilter(@PathVariable int id) {

List<Producto> productos = facadeProducto.findProductosConMayorStockPorSucursal(id);
List<ProductoDTO> productoDTOs = productos.stream()
                                       .map(ProductoDTO::new)
                                       .collect(Collectors.toList());
return new ResponseEntity<>(productoDTOs, HttpStatus.OK);
}

}
