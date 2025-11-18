package com.portfolio.todotask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

	@NotBlank(message = "El nombre de usuario es obligatorio.")
	@Size(min = 4, max = 50, message = "El nombre de usuario debe tener entre 4 y 50 caracteres.")
	private String username;
	
	@NotBlank(message = "El email es obligatorio.")
	@Email(message = "Debe de ser un email valido")
	private String email;
	
	@NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    private String password;
	
}
