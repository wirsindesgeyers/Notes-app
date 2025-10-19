package com.kauan.br.projetinhoteste.model.note.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NoteRequestDTO(


        @NotBlank(message = "O título não pode estar em branco")
        @Size(max = 100, message = "O título não pode exceder 100 caracteres")
        String title,
        
        String content

)

{
}
