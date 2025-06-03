package br.com.davidev.pixelbank.gamermodel;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "gamer_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GamerUser {

    //Model do GamerUser. Usaremos ID, Nome, Email, Senha, experiência, Níveis e Data/Hora;

    @PrePersist // Não se esqueça de importar jakarta.persistence.PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.level == null) {
            this.level = 1;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="username", nullable = false, unique = true,length = 50)
    private String username;

    @Column(nullable = false, unique = true,length = 100)
    private String email;


    @Column(name ="password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "xp_points", nullable = false)
    private long xpPoints;

    @Column (nullable = false)
    private Integer level;

    @Column (name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


}
