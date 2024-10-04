package Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MESSAGES")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private RentalEntity rental;

    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    private long message;

    private Date created_at;
    private Date updated_at;

    public MessageEntity() {
        this.created_at = new Date();
    }

    public int getId() {
        return id;
    }

    public RentalEntity getRental() {
        return rental;
    }

    public void setRental(RentalEntity rental) {
        this.rental = rental;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public long getMessage() {
        return message;
    }

    public void setMessage(long message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }
}
