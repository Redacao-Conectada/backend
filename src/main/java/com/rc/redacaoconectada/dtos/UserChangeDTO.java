package com.rc.redacaoconectada.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChangeDTO {

    private String newUserName;

    private String newGraduation;

    private String newSchoolName;

    private String newState;

    private String newCity;

    private String image;
}
