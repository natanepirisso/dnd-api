package com.example.dnd_api.character;

import jakarta.validation.constraints.*;

public record CharacterRequestDTO(
    @NotBlank(message = "O nome do personagem é obrigatório.")
    String name,
    @NotBlank(message = "A raça é obrigatória.")
    String race,
    @NotBlank(message = "A classe é obrigatória.")
    String characterClass,

    @NotNull(message = "O nível é obrigatório")
    @Min(value = 1, message = "O nível mínimo é 1.")
    @Max(value = 20, message = "O nível máximo é 20.")
    Integer level,
    @NotNull @Min(1)
    Integer maxHp,
    @NotNull @Min(0)
    Integer currentHp,
    @NotNull @Min(1) @Max(30)
    Integer strength,
    @NotNull @Min(1) @Max(30)
    Integer dex,
    @NotNull @Min(1) @Max(30)
    Integer inte,
    @NotNull @Min(1) @Max(30)
    Integer wis,
    @NotNull @Min(1) @Max(30)
    Integer cons,
    @NotNull @Min(1) @Max(30)
    Integer charisma
)
{
}
