package Api;

import Model.UserEntity;
import Persistence.UserRepository;
import Services.ApiResponse;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Iterable<UserEntity>> list(){
        return ResponseEntity.status(200).body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable int id){
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ApiResponse create(@Validated @RequestBody UserEntity user){
        UserEntity newUser = userRepository.save(user);
        Object data = ResponseEntity.ok().body(newUser);
        return new ApiResponse("User successfully created", data);
    }

    //todo: remove
    @GetMapping("/prune")
    public ResponseEntity<String> deleteAll(){
        userRepository.deleteAll();
        return new ResponseEntity<>("All deleted", HttpStatus.OK);
    }
}
