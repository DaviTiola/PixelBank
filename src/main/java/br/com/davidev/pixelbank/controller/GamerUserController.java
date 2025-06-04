package br.com.davidev.pixelbank.controller;


import br.com.davidev.pixelbank.dto.GamerUserCreateRequestDTO;
import br.com.davidev.pixelbank.dto.GamerUserResponseDTO;
import br.com.davidev.pixelbank.service.GamerUserService;
import br.com.davidev.pixelbank.service.GamerUserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class GamerUserController {

    private final GamerUserService gamerUserService;

    public GamerUserController(GamerUserService gamerUserService) {
        this.gamerUserService = gamerUserService;
    }

    @PostMapping
    public ResponseEntity<GamerUserResponseDTO> createdUser (@Valid @RequestBody GamerUserCreateRequestDTO createRequestDTO) {
        GamerUserResponseDTO createdUser = gamerUserService.createUser(createRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamerUserResponseDTO> findUserById(@PathVariable Long id) {
        GamerUserResponseDTO userResponseDTO = gamerUserService.findUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<GamerUserResponseDTO>> findAllUsers() {
        List<GamerUserResponseDTO> users = gamerUserService.findAllUsers();
        return ResponseEntity.ok(users);
    }
}
