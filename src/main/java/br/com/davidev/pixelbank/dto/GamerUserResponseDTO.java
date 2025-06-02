package br.com.davidev.pixelbank.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class GamerUserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private Long xpPoints;
    private Integer level;
    private LocalDateTime createdAt;

}
