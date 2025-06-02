package br.com.davidev.pixelbank.repository;

import br.com.davidev.pixelbank.gamermodel.GamerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepository extends JpaRepository<GamerUser, Long> {
}
