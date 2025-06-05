package br.com.davidev.pixelbank.repository;

import br.com.davidev.pixelbank.gamermodel.Account;
import br.com.davidev.pixelbank.gamermodel.GamerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.davidev.pixelbank.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
Optional<Account> findByAccountNumber(String accountNumber);
Optional<Account> findByUser(GamerUser user);
Optional<Account> findByUserId(Long userId);


}
