package ecommerce.utn.ecommerce.jar.controllers;

import ecommerce.utn.ecommerce.jar.models.User;
import ecommerce.utn.ecommerce.jar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("api/user/{id}")
    public Optional<User> findUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/api/user")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok("usuario creado con exitoooo");
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("usuario eliminado con exito");
    }

}
