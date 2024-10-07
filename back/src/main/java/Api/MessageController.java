package Api;

import Model.MessageEntity;
import Model.RentalEntity;
import Model.UserEntity;
import Persistence.MessageRepository;
import Persistence.RentalRepository;
import Persistence.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, String>> createMessage(
            @RequestBody HashMap<String, String> message
    ) {
        Optional<UserEntity> user = userRepository.findById(Integer.parseInt(message.get("user_id")));
        Optional<RentalEntity> rental = rentalRepository.findById(Integer.parseInt(message.get("rental_id")));

        if (user.isEmpty() || rental.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User or Rental is missing");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            MessageEntity newMessage = new MessageEntity();
            newMessage
                    .setMessage(message.get("message"))
                    .setRental(rental.get())
                    .setUser(user.get());

            messageRepository.save(newMessage);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Message sent");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
