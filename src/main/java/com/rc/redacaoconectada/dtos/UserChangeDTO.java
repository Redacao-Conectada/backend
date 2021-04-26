package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.services.validation.UserInsertValid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@UserInsertValid
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
