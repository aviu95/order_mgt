package com.ecom.order_mgt.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "view_order_user_details")
public class OrderUserDetails {

    @Id
    private Long id;

    private String orderDesc;

    private String firstName;

    private String lastName;

}
