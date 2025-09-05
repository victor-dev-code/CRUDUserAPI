package com.es.examen.crud_ec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
}
