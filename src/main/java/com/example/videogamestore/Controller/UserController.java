package com.example.videogamestore.Controller;



import com.example.videogamestore.Model.User;
import com.example.videogamestore.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user, UriComponentsBuilder ucb){
        User savedUser = userService.addUser(user);
        var urlLocation = ucb.path("/api/v1/users/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(
                urlLocation
        ).body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        try {
            User updatedUser = userService.updateUser(id, user);
            return  ResponseEntity.ok(updatedUser);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User deleted successfully");

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }
}
