package Api;

import Model.RentalEntity;
import Persistence.RentalRepository;
import Persistence.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public RentalController(RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String rentals() {
        RentalEntity defaultRental = new RentalEntity();

        return "Rentals";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            final Principal principal,
//            @RequestParam("name") String name,
//            @RequestParam("surface") Integer surface,
//            @RequestParam("price") Integer price,
            @RequestParam("picture") MultipartFile picture,
            @Validated @RequestBody RentalEntity rentalEntity
//            @RequestParam("description") String description
            ) throws IOException {
        System.out.println(picture);
        // Save the file to a directory
        String fileName = picture.getOriginalFilename();
        // Save the file to the filesystem or cloud storage
        // For example:
         Path filePath = Paths.get("files", fileName);
         Files.write(filePath, picture.getBytes());
         RentalEntity newRental = new RentalEntity();


        // Set the file name (or URL) in the RentalEntity
//        newRental.setPicture(fileName);
//        newRental.setName(name);
//        newRental.setDescription(description);
//        newRental.setOwner(userRepository.findByEmail(principal.getName()).get());
//        newRental.setPrice(price);
//        newRental.setSurface(surface);

        // Now save the entity to the database
        rentalRepository.save(newRental);

        return new ResponseEntity<>("Rental created with file: " + fileName, HttpStatus.OK);
    }


}