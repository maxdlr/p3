package com.p3.controller;

import com.p3.response.AddApiResponse;
import com.p3.exception.ApiBadPostRequestException;
import com.p3.model.MessageEntity;
import com.p3.model.RentalEntity;
import com.p3.model.UserEntity;
import com.p3.persistence.MessageRepository;
import com.p3.persistence.RentalRepository;
import com.p3.persistence.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public MessageController(RentalRepository rentalRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @PostMapping()
    ResponseEntity<Object> create(
            @RequestBody HashMap<String, String> message
    ) {
        Optional<UserEntity> user = userRepository.findById(Integer.parseInt(message.get("user_id")));
        Optional<RentalEntity> rental = rentalRepository.findById(Integer.parseInt(message.get("rental_id")));

        if (user.isEmpty() || rental.isEmpty()) {
            throw new ApiBadPostRequestException("User or Rental is missing");
        }

        try {
            MessageEntity newMessage = new MessageEntity();
            newMessage
                    .setMessage(message.get("message"))
                    .setRental(rental.get())
                    .setUser(user.get());

            messageRepository.save(newMessage);

            return new AddApiResponse("Message sent").get();
        } catch (Exception e) {
            throw new ApiBadPostRequestException(e.getMessage());
        }
    }
}
