package com.myProject.POJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewUser_POJO {
    /*
{
    "name":"Lenny Belardo",
    "gender":"male",
    "email":"lennybelardo@me.com",
    "status":"active"
}
     */

private String  name, gender, email, status;

}
