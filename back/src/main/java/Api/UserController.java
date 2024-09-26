package Api;

import Model.User;
import Persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> list(){
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User create(@Validated @RequestBody User user){
        return userRepository.save(user);
    }
}
