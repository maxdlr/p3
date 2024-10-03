package Persistence;

import Model.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
}
