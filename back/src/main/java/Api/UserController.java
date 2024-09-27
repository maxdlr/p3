package Api;

import Model.User;
import Persistence.UserRepository;
import Services.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> list(){

        return ResponseEntity.status(200).body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ApiResponse create(@Validated @RequestBody User user){
        User newUser = userRepository.save(user);
        Object data = ResponseEntity.ok().body(newUser);
        return new ApiResponse("User successfully created", data);
    }
}
