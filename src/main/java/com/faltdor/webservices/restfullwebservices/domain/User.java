package com.faltdor.webservices.restfullwebservices.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    @Size(min=2, message="Name should have atleast 2 characters")
    private String name;
    @Past
    private Date birthdate;

}
