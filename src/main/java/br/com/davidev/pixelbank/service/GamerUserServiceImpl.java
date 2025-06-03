package br.com.davidev.pixelbank.service;

import br.com.davidev.pixelbank.dto.GamerUserCreateRequestDTO;
import br.com.davidev.pixelbank.dto.GamerUserResponseDTO;
import br.com.davidev.pixelbank.repository.GamerRepository;
import org.springframework.transaction.annotation.Transactional;

public class GamerUserServiceImpl implements GamerUserService{

    private final GamerRepository gamerRepository;

    public GamerUserServiceImpl (GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    @Override
    @Transactional
    public GamerUserResponseDTO createUser (GamerUserCreateRequestDTO createRequestDTO) {
        if (createRequestDTO == null) throw new IllegalArgumentException("RequestDTO não pode ser nulo");
        GamerUserResponseDTO response = new GamerUserResponseDTO();
        response.setId(1L);
        response.setUsername(createRequestDTO.getUsername());

        return null;
    }

}
