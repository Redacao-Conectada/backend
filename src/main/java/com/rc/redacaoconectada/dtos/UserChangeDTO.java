package com.rc.redacaoconectada.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserChangeDTO extends UserDTO{

    private String newUserName;
    private String image;

    public UserChangeDTO(String newUserName){
        super();
        this.newUserName = newUserName;
        this.image = image;
    }
}
