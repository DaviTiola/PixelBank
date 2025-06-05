package br.com.davidev.pixelbank.service;

import br.com.davidev.pixelbank.dto.GamerUserCreateRequestDTO;
import br.com.davidev.pixelbank.dto.GamerUserResponseDTO;
import br.com.davidev.pixelbank.dto.GamerUserUpdateDTO;
import br.com.davidev.pixelbank.exception.ResourceNotFoundException;
import br.com.davidev.pixelbank.gamermodel.Account;
import br.com.davidev.pixelbank.repository.AccountRepository;
import br.com.davidev.pixelbank.repository.GamerRepository;
import br.com.davidev.pixelbank.gamermodel.GamerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GamerUserServiceImpl implements GamerUserService {

    private final GamerRepository gamerRepository;
    private final PasswordEncoder passwordEncoder;


    // Construtor para injeção de dependências (forma recomendada)
    @Autowired // Opcional se tiver apenas um construtor a partir do Spring 4.3
    public GamerUserServiceImpl(GamerRepository gamerRepository,
                                PasswordEncoder passwordEncoder,
                                AccountRepository accountRepository) {
        this.gamerRepository = gamerRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
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

        Account newAccount = new Account();
        newAccount.setUser(savedUser);


        String accountNumber = "PXB-" + savedUser.getId() + "-" + (System.currentTimeMillis() % 10000);
        newAccount.setAccountNumber(accountNumber);


        accountRepository.save(newAccount);

            GamerUserResponseDTO responseDTO = new GamerUserResponseDTO();
            responseDTO.setId(savedUser.getId());
            responseDTO.setUsername(savedUser.getUsername());
            responseDTO.setEmail(savedUser.getEmail());
            responseDTO.setXpPoints(savedUser.getXpPoints());
            responseDTO.setLevel(savedUser.getLevel());
            responseDTO.setCreatedAt(savedUser.getCreatedAt());


            return responseDTO;

        }
    @Override
    @Transactional(readOnly = true)
    public GamerUserResponseDTO findUserById(Long id) {
        GamerUser gamerUser = gamerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário Gamer não encontrado com o ID: " + id));

        GamerUserResponseDTO responseDTO = new GamerUserResponseDTO();
        responseDTO.setId(gamerUser.getId());
        responseDTO.setUsername(gamerUser.getUsername());
        responseDTO.setEmail(gamerUser.getEmail());

        // Verifique o nome do campo xp no seu GamerUserResponseDTO (xp ou xpPoints) e ajuste o setter
        responseDTO.setXpPoints(gamerUser.getXpPoints());

        responseDTO.setLevel(gamerUser.getLevel());
        responseDTO.setCreatedAt(gamerUser.getCreatedAt());

        return responseDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GamerUserResponseDTO> findAllUsers() {
        List<GamerUser> allGamerUsers = gamerRepository.findAll();
        List<GamerUserResponseDTO> responseDTOs = allGamerUsers.stream()
            .map(this:: convertToResponseDTO)
                .collect(Collectors.toList());

        return responseDTOs;
    }

    private GamerUserResponseDTO convertToResponseDTO(GamerUser gamerUser) {
        GamerUserResponseDTO dto = new GamerUserResponseDTO();
        dto.setId(gamerUser.getId());
        dto.setUsername(gamerUser.getUsername());
        dto.setEmail(gamerUser.getEmail());
        dto.setXpPoints(gamerUser.getXpPoints());
        dto.setLevel(gamerUser.getLevel());
        dto.setCreatedAt(gamerUser.getCreatedAt());
        return dto;
        }


    @Override
    @Transactional
    public GamerUserResponseDTO updateUser(Long id, GamerUserUpdateDTO updateDTO) {
        GamerUser existingUser = gamerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário Gamer não encontrado com o ID: " + id + " para atualização."));
        boolean hasChanges = false;

        if (updateDTO.getUsername() != null && !updateDTO.getUsername().isBlank() && !updateDTO.getUsername().equalsIgnoreCase(existingUser.getUsername())) {
            Optional<GamerUser> userWithNewUsername = gamerRepository.findByUsernameIgnoreCase(updateDTO.getUsername());
            if (userWithNewUsername.isPresent() && !userWithNewUsername.get().getId().equals(existingUser.getId())) {
                throw new IllegalArgumentException("Username '" + updateDTO.getUsername() + "' já está em uso por outro usuário.");

            }
            existingUser.setUsername(updateDTO.getUsername());
            hasChanges = true;
        }

        if (updateDTO.getEmail() != null && !updateDTO.getEmail().isBlank() && !updateDTO.getEmail().equalsIgnoreCase(existingUser.getEmail())) {
            // Verifica se o novo email já está em uso por OUTRO usuário
            Optional<GamerUser> userWithNewEmail = gamerRepository.findByEmailIgnoreCase(updateDTO.getEmail());
            if (userWithNewEmail.isPresent() && !userWithNewEmail.get().getId().equals(existingUser.getId())) {
                throw new IllegalArgumentException("Email '" + updateDTO.getEmail() + "' já está em uso por outro usuário.");
            }
            existingUser.setEmail(updateDTO.getEmail());
            hasChanges = true;

        }

        if (hasChanges) {
            GamerUser updatedUser = gamerRepository.save(existingUser);
            // Mapeia a entidade ATUALIZADA para o DTO de resposta
            return convertToResponseDTO(updatedUser);
        } else {
            // Se não houve mudanças, apenas retorna os dados do usuário existente mapeados para DTO
            return convertToResponseDTO(existingUser);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!gamerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário Gamer não foi encontrado com o ID: " + id +" Deleção não realizada");
        }
        gamerRepository.deleteById(id);
    }
    private final AccountRepository accountRepository;

    }

