package br.com.fiap.userms.controller;

import br.com.fiap.userms.entity.User;
import br.com.fiap.userms.records.UserRecord;
import br.com.fiap.userms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "User Controller", description = "User Controller exposes REST APIs for User")
@RestController
@RequestMapping("/v1/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @Operation(summary = "Save User REST API", description="Save user object in a database")
    @ResponseStatus(CREATED)
    public ResponseEntity<User> createPost(@RequestBody @Valid UserRecord userRecord ){
        User user = userService.create(userRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping
    @Operation(summary = "Get Users REST API", description="Get all users from database")
    public ResponseEntity<Page<UserRecord>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<UserRecord> list = userService.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    @Operation(summary = "Get User REST API by ID", description="Get user by ID from database")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user =  userService.findById(id);
        return ResponseEntity.ok().body(user);
    }


    @DeleteMapping(value="/{id}")
    @Operation(summary = "Delete User REST API", description="Delete user object by ID in a database")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Put User REST API", description="Put user object by ID in a database")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserRecord> update(@PathVariable Long id , @RequestBody UserRecord userRecord) {
        userService.update(id, userRecord);
        return ResponseEntity.ok(userRecord);
    }

}