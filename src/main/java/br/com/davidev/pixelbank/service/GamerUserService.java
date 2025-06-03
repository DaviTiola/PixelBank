package br.com.davidev.pixelbank.service;

import br.com.davidev.pixelbank.dto.GamerUserCreateRequestDTO;
import br.com.davidev.pixelbank.dto.GamerUserResponseDTO;

public interface GamerUserService {

    GamerUserResponseDTO createUser (GamerUserCreateRequestDTO gamerUserRequestDTO);

    GamerUserResponseDTO findUserById(Long id);
}
