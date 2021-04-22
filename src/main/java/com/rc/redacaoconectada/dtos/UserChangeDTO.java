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

    public UserChangeDTO(String newUserName){
        super();
        this.newUserName = newUserName;
    }
}
