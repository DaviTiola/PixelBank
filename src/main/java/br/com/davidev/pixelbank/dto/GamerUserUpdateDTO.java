package br.com.davidev.pixelbank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GamerUserUpdateDTO {

    @Size(min = 3, max = 50, message = "O nome de usuário, se fornecido, deve ter entre 3 e 50 caracteres.")
    private String username;

    @Email (message = "Formato de e-mail inválido, se fornecido.")
    @Size(max = 100, message = "O e-mail, se fornecido, não pode exceder 100 caracteres")
    private String email;

}
