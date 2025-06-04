package br.com.davidev.pixelbank.service;
import br.com.davidev.pixelbank.dto.GamerUserCreateRequestDTO;
import br.com.davidev.pixelbank.dto.GamerUserResponseDTO;
import br.com.davidev.pixelbank.dto.GamerUserUpdateDTO;

import java.util.List;

public interface GamerUserService {

    GamerUserResponseDTO createUser (GamerUserCreateRequestDTO gamerUserRequestDTO);

    GamerUserResponseDTO findUserById(Long id);

    List<GamerUserResponseDTO> findAllUsers();

    GamerUserResponseDTO updateUser(Long id, GamerUserUpdateDTO updateDTO);
}
