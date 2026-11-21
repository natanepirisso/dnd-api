package com.example.dnd_api.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepository repository;

    public List<CharacterResponseDTO> findAll(){
        return repository.findAll().stream().map(CharacterResponseDTO::fromEntity).toList();
    }
    public Optional<CharacterResponseDTO> findById(Long id){
        return repository.findById(id).map(CharacterResponseDTO::fromEntity);
    }
    public CharacterResponseDTO create(CharacterRequestDTO dto){
        CharacterSheet character = new CharacterSheet();
        applyDtoToEntity(dto,character);

        CharacterSheet saved = repository.save(character);
        return CharacterResponseDTO.fromEntity(saved);
    }
    public Optional<CharacterResponseDTO> update(Long id, CharacterRequestDTO dto){
        Optional<CharacterSheet> optionalCharacter = repository.findById(id);

        if(optionalCharacter.isEmpty()){
            return Optional.empty();
        }
        CharacterSheet character = optionalCharacter.get();
        applyDtoToEntity(dto,character);

        CharacterSheet saved = repository.save(character);
        return Optional.of(CharacterResponseDTO.fromEntity(saved));
    }
    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
    private void applyDtoToEntity(CharacterRequestDTO dto, CharacterSheet character){
        character.setName(dto.name());
        character.setRace(dto.race());
        character.setCharacterClass(dto.characterClass());
        character.setLevel(dto.level());
        character.setMaxHp(dto.maxHp());
        //HP atual nunca é maior que o HP máximo
        character.setCurrentHp(Math.min(dto.currentHp(),dto.maxHp()));
        character.setStrength(dto.strength());
        character.setDex(dto.dex());
        character.setCons(dto.cons());
        character.setInte(dto.inte());
        character.setWis(dto.wis());
        character.setCharisma(dto.charisma());
    }
}
