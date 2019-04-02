package com.faltdor.webservices.restfullwebservices.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
@Api("User Properties")
@JsonFilter(value = "filterName")
public class User {

    @ApiModelProperty(notes = "User Id")
    private int id;
    @ApiModelProperty("User Name; Should have atleast 2 characters")
    @Size(min=2, message="Name should have atleast 2 characters")
    private String name;
    @Past
    @ApiModelProperty(notes = "Birthdate musth be on the past. ")
    private Date birthdate;

}
