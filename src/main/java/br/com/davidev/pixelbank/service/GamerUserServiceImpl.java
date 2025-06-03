package br.com.davidev.pixelbank.service;

import br.com.davidev.pixelbank.dto.GamerUserCreateRequestDTO;
import br.com.davidev.pixelbank.dto.GamerUserResponseDTO;
import br.com.davidev.pixelbank.repository.GamerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public class GamerUserServiceImpl implements GamerUserService{

    private final GamerRepository gamerRepository;
    private final PasswordEncoder passwordEncoder;

    public GamerUserServiceImpl(GamerRepository gamerRepository, PasswordEncoder passwordEncoder) {
        this.gamerRepository = gamerRepository;
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    @Transactional
    public GamerUserResponseDTO createUser (GamerUserCreateRequestDTO createRequestDTO) {
        if (createRequestDTO == null) throw new IllegalArgumentException("RequestDTO não pode ser nulo");
        GamerUserResponseDTO response = new GamerUserResponseDTO();

        response.setId(1L);
        response.setUsername(createRequestDTO.getUsername());
        response.setEmail(createRequestDTO.getEmail());
        response.setXpPoints(0L);
        response.setLevel(1);
        response.setCreatedAt(LocalDateTime.now());

        return response;
    }

}
