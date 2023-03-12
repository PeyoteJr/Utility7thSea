package utility7thsea.mapper;

import utility7thsea.model.Character;
import utility7thsea.model.SequenceMember;

import java.util.Optional;

public class CharacterToSequenceMemberMapper {

    public static SequenceMember map(Character c){
        return new SequenceMember(Optional.of(c.getId()), c.getName(), c.getDramatic());
    }
}
