package com.myProject.POJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewComment_POJO {

    /*
    {
"post_id": 10,
"name": "Vimal Menon",
"email": "vimal_menon@lttel.io",
"body": "Perspiciatis quam cumque."
}
     */

    private String post_id, name, email, body;
}
