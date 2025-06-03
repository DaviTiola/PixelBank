package br.com.davidev.pixelbank.service;

import br.com.davidev.pixelbank.dto.GamerUserCreateRequestDTO;
import br.com.davidev.pixelbank.dto.GamerUserResponseDTO;
import br.com.davidev.pixelbank.repository.GamerRepository;
import br.com.davidev.pixelbank.gamermodel.GamerUser;
import br.com.davidev.pixelbank.service.GamerUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class GamerUserServiceImpl implements GamerUserService {

    private final GamerRepository gamerRepository;
    private final PasswordEncoder passwordEncoder;

    // Construtor para injeção de dependências (forma recomendada)
    @Autowired // Opcional se tiver apenas um construtor a partir do Spring 4.3
    public GamerUserServiceImpl(GamerRepository gamerRepository, PasswordEncoder passwordEncoder) {
        this.gamerRepository = gamerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public GamerUserResponseDTO createUser(GamerUserCreateRequestDTO createRequestDTO) {
        if (createRequestDTO == null) {
            throw new IllegalArgumentException("Os dados para criação do usuário (DTO) não podem ser nulos.");
        }


            GamerUser gamerUserEntity = new GamerUser();
            gamerUserEntity.setUsername(createRequestDTO.getUsername());
            gamerUserEntity.setEmail(createRequestDTO.getEmail());

            String hashedPassword = passwordEncoder.encode(createRequestDTO.getPassword());
            gamerUserEntity.setPasswordHash(hashedPassword);


            GamerUser savedUser = gamerRepository.save(gamerUserEntity);


            GamerUserResponseDTO responseDTO = new GamerUserResponseDTO();
            responseDTO.setId(savedUser.getId());
            responseDTO.setUsername(savedUser.getUsername());
            responseDTO.setEmail(savedUser.getEmail());
            responseDTO.setXpPoints(savedUser.getXpPoints());
            responseDTO.setLevel(savedUser.getLevel());
            responseDTO.setCreatedAt(savedUser.getCreatedAt());


            return responseDTO;

        }
    }