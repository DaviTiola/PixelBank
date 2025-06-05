package br.com.davidev.pixelbank.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountResponseDTO {

    private Long id; // ID da Conta
    private String accountNumber; // Número da Conta
    private BigDecimal balance; // Saldo da Conta
    private Long gamerUserId; // ID do GamerUser dono da conta
    private String gamerUsername; // Username do GamerUser dono da conta (para conveniência)
    private LocalDateTime createdAt; // Data de criação da Conta
    private LocalDateTime updatedAt; // Data da última atualização da Conta


}
