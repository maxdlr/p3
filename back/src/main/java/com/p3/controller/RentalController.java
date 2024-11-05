package com.p3.controller;

import com.p3.dto.api.*;
import com.p3.model.RentalEntity;
import com.p3.model.UserEntity;
import com.p3.persistence.RentalRepository;
import com.p3.persistence.UserRepository;
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
    public ResponseEntity<Object> list() {
      return new BrowseApiResponse<>("rentals", rentalRepository.findAll()).get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRentalById(@PathVariable("id") int id) {
        return rentalRepository.findById(id)
                .map(rentalEntity -> new ReadApiResponse<>(rentalEntity).get())
                .orElseGet(() -> new ErrorApiResponse("Rental not found", HttpStatus.NOT_FOUND).get());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
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

            return new ErrorApiResponse(
                    "Missing or invalid parameters: " + String.join(", ", missingParams),
                    HttpStatus.BAD_REQUEST)
                    .get();
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

        return new AddApiResponse("Rental successfully created").get();
    }

    @PutMapping(path ="/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("surface") float surface,
            @RequestParam("price") float price,
            @RequestParam("description") String description
    ) {
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
            return new ErrorApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST).get();
        }

        return new EditApiResponse("Rental successfully updated").get();
    }
}