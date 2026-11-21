package com.example.dnd_api.character;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "characters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String race;
    private String name;
    private String characterClass;
    private Integer level;

    private Integer maxHp;
    private Integer currentHp;

    //Atributos base do D&D
    private Integer strength;
    private Integer dex;
    private Integer cons;
    private Integer inte;
    private Integer wis;
    private Integer charisma;

}
