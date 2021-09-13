package com.myProject.POJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address_NestedPOJO {

        private String addressLine1, addressLine2, city, state, zipcode, country;

    }

