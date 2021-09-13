package com.myProject.POJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class NewPost_POJO {

    /*
  {
    "user_id": 9,
    "title": "Subiungo odio magnam caritas carus arto tametsi compello bellum celo crustulum optio sustineo solus verto.",
    "body": "Vero vester aspicio. Brevis tui aurum. Spiculum urbanus compono. Depopulo officiis cerno. Anser caries cena. Paens chirographum laudantium. Carus spargo spero. Audentia complectus articulus. Vergo carcer crepusculum. Conforto cado     subito. Comedo undique est. Angustus vicissitudo audax. Pel tot varius. Aranea creber aptus. Vigor sponte aliquam. Stultus verus aetas. Custodia pecto blandior. Sum depopulo barba."
}
     */
    private String user_id, title, body;
}
