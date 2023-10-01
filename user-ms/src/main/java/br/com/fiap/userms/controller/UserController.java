package br.com.fiap.userms.controller;

import br.com.fiap.userms.entity.User;
import br.com.fiap.userms.records.UserRecord;
import br.com.fiap.userms.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public ResponseEntity<User> createPost(@RequestBody @Valid UserRecord userRecord ){
        User user = userService.createUser(userRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list =  userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user =  userService.findById(id);
        return ResponseEntity.ok().body(user);
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}