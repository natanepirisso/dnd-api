package com.example.dnd_api.character;

public record CharacterResponseDTO(Long id,
    String name,
    String race,
    String characterClass,
    Integer level,
    Integer proficiencyBonus,
    Integer maxHp,
    Integer currentHp,
    Integer strength,
    Integer strengthMod,
    Integer dex,
    Integer dexMod,
    Integer cons,
    Integer consMod,
    Integer inte,
    Integer inteMod,
    Integer wis,
    Integer wisMod,
    Integer charisma,
    Integer charismaMod)
{
    public static CharacterResponseDTO fromEntity(CharacterSheet c){
        return new CharacterResponseDTO(
                c.getId(),
                c.getName(),
                c.getRace(),
                c.getCharacterClass(),
                c.getLevel(),
                calculateProficiency(c.getLevel()),
                c.getMaxHp(),
                c.getCurrentHp(),
                c.getStrength(),
                calculateModifier(c.getStrength()),
                c.getDex(),
                calculateModifier(c.getDex()),
                c.getCons(),
                calculateModifier(c.getCons()),
                c.getInte(),
                calculateModifier(c.getInte()),
                c.getWis(),
                calculateModifier(c.getWis()),
                c.getCharisma(),
                calculateModifier(c.getCharisma())
        );
    }
    private static Integer calculateModifier(Integer score){
        if(score == null) return 0;
        return  Math.floorDiv(score - 10, 2);
    }
    private static Integer calculateProficiency(Integer level){
        if(level== null || level < 1 ) return 2;
        return ((level - 1)/4 + 2);
    }
}
