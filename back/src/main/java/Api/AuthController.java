package Api;

import Dto.AuthResponseDto;
import Dto.LoginDto;
import Dto.RegisterDto;
import Model.RoleEntity;
import Model.UserEntity;
import Persistence.RoleRepository;
import Persistence.UserRepository;
import Security.JwtGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private JwtGenerator jwtsGenerator;

    public AuthController(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtGenerator jwtsGenerator
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtsGenerator = jwtsGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtsGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("email is taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        RoleEntity role = roleRepository.findByName("USER").isPresent() ? roleRepository.findByName("USER").get() : null;
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        return new ResponseEntity<>("User successfully registered", HttpStatus.OK);
    }
}