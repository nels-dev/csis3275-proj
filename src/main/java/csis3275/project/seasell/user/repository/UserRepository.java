package csis3275.project.seasell.user.repository;

import csis3275.project.seasell.user.model.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);

    //Check by userName?
//    Optional<AppUser> findByUserName(String preferredUserName);
    //Check by userName?
    boolean existsByPreferredUserName(String preferredUserName);
}
