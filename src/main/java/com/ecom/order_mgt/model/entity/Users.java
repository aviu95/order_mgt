package com.ecom.order_mgt.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonInclude.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@JsonInclude(Include.NON_NULL)
public class Users {

    @Id
    private Long id;

    private String firstName;

    private String lastName;
}
