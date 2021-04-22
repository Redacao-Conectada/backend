package com.rc.redacaoconectada.Mapper;

import com.rc.redacaoconectada.dtos.EssayDTO;
import com.rc.redacaoconectada.entities.Essay;

import java.util.List;

public class EssayMapper {

    public static void mapsEssaytoEssayDTO(Essay essay, EssayDTO essayDTO) {
        essayDTO.setId(essay.getId());
        essayDTO.setUpVote(essay.getUpVote());
        essayDTO.setBody(essay.getBody());
    }

    public static void mapsEssaystoEssaysDTO(List<Essay> essays, List<EssayDTO> essaysDTO) {
        for (Essay essay : essays) {
            EssayDTO essayDTO = new EssayDTO();
            mapsEssaytoEssayDTO(essay, essayDTO);

            essaysDTO.add(essayDTO);
        }
    }

}
