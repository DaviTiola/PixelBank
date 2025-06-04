package br.com.davidev.pixelbank.repository;

import br.com.davidev.pixelbank.gamermodel.GamerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamerRepository extends JpaRepository<GamerUser, Long> {

    Optional<GamerUser> findByUsernameIgnoreCase(String username);
    Optional<GamerUser>findByEmailIgnoreCase(String email);

}


