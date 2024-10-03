package Api;

import Model.RentalEntity;
import Persistence.RentalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalRepository rentalRepository;

    public RentalController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }


    @GetMapping
    public String rentals() {
        return "Rentals";
    }

    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<String> create(
            @Validated @RequestBody RentalEntity rentalEntity,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        // Save the file to a directory
        String fileName = file.getOriginalFilename();
        // Save the file to the filesystem or cloud storage
        // For example:
         Path filePath = Paths.get("path/to/your/directory", fileName);
         Files.write(filePath, file.getBytes());

         RentalEntity newRental = new RentalEntity();

        // Set the file name (or URL) in the RentalEntity
        newRental.setPicture(fileName);
        newRental.setName(rentalEntity.getName());
        newRental.setDescription(rentalEntity.getDescription());
        newRental.setOwner(rentalEntity.getOwner());

        // Now save the entity to the database
        rentalRepository.save(newRental);

        return new ResponseEntity<>("Rental created with file: " + fileName, HttpStatus.OK);
    }


}