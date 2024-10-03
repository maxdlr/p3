package Persistence;

import Model.RentalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity, Integer> {
}
