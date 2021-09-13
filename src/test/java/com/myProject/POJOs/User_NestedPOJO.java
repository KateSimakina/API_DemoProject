package com.myProject.POJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User_NestedPOJO {
    private String name, gender, email, status;
    private Address_NestedPOJO address;


}

