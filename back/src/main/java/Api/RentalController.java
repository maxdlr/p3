package Api;

import Model.RentalEntity;
import Model.UserEntity;
import Persistence.RentalRepository;
import Persistence.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Value("${assets.savePath}")
    private String assetsPath;

    @Value("${assets.getPath}")
    private String getPath;

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
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("surface", surface);
        params.put("price", price);
        params.put("picture", picture != null && !picture.isEmpty() ? picture : null);
        params.put("description", description);

        List<String> missingParams = new ArrayList<>();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() == null || (entry.getValue() instanceof String && ((String) entry.getValue()).isEmpty())) {
                missingParams.add(entry.getKey());
            }
        }

        if (!missingParams.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Missing or invalid parameters: " + String.join(", ", missingParams));
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Optional<UserEntity> user = userRepository.findByEmail(principal.getName());

        if (picture != null && user.isPresent()) {
            String fileName = picture.getOriginalFilename();
            Path filePath = Paths.get(this.assetsPath, fileName);
            Files.write(filePath, picture.getBytes());

            RentalEntity newRental = new RentalEntity();

            newRental
                    .setPicture(this.getPath + fileName)
                    .setName(name)
                    .setDescription(description)
                    .setOwner(user.get())
                    .setPrice(price)
                    .setSurface(surface);
            rentalRepository.save(newRental);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental successfully created");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path ="/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> update(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("surface") float surface,
            @RequestParam("price") float price,
            @RequestParam("description") String description
    ) {
        String message = "Rental successfully updated";
        HttpStatus status = HttpStatus.OK;
        try {
            Optional<RentalEntity> rental = rentalRepository.findById(id);
            if (rental.isPresent()) {
            rental
                    .get()
                    .setName(name)
                    .setSurface(surface)
                    .setPrice(price)
                    .setDescription(description);
            rentalRepository.save(rental.get());
            }
        } catch (Exception e) {
            message = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return new ResponseEntity<>(response, status);
    }
}