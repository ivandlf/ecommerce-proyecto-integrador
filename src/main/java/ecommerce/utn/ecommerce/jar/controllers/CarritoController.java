package ecommerce.utn.ecommerce.jar.controllers;

import ecommerce.utn.ecommerce.jar.dto.CarritoDto;
import ecommerce.utn.ecommerce.jar.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarritoController {
    @Autowired
    private CarritoService carritoService;


    @GetMapping("/api/carrito")
    public List<CarritoDto> findAll(){
        return carritoService.findAll();
    }

    @GetMapping("api/carrito/{id}")
    public CarritoDto findCarritoById(@PathVariable Long id){
        return carritoService.findById(id);
    }

    @PostMapping("/api/carrito")
    public ResponseEntity<String> saveCarrito(@RequestBody CarritoDto carritoDto){
        carritoService.save(carritoDto);
        return ResponseEntity.ok("carrito creado con exitoooo");
    }

    @DeleteMapping("/api/carrito/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        carritoService.deleteById(id);
        return ResponseEntity.ok("carrito eliminado con exito");
    }
}
