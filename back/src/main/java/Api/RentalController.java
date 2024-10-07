package Api;

import Model.RentalEntity;
import Persistence.RentalRepository;
import Persistence.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Value("${assets.path}")
    private String assetsPath;

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public RentalController(RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<HashMap<String, Iterable<RentalEntity>>> list(){
        HashMap<String, Iterable<RentalEntity>> response = new HashMap<>();
        response.put("rentals", rentalRepository.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalEntity> getRentalById(@PathVariable("id") int id){
        Optional<RentalEntity> rental = rentalRepository.findById(id);
        return rental.map(rentalEntity -> new ResponseEntity<>(rentalEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> create(
            final Principal principal,
            @RequestParam("name") String name,
            @RequestParam("surface") float surface,
            @RequestParam("price") float price,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("description") String description
    ) throws IOException {
         String fileName = picture.getOriginalFilename();
         Path filePath = Paths.get(this.assetsPath, fileName);
         Files.write(filePath, picture.getBytes());

         RentalEntity newRental = new RentalEntity();

        newRental
            .setPicture(this.assetsPath + fileName)
            .setName(name)
            .setDescription(description)
            .setOwner(userRepository.findByEmail(principal.getName()).get())
            .setPrice(price)
            .setSurface(surface);
        rentalRepository.save(newRental);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental successfully created");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}