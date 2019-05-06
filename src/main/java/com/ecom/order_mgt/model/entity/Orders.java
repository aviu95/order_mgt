package com.ecom.order_mgt.model.entity;

import com.ecom.order_mgt.model.dto.OrderRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@JsonInclude(Include.NON_NULL)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users userId;

    private Long itemId;

    private String orderDesc;

    private LocalDateTime orderDate;

    public Orders(OrderRequest orderRequest, Users users) {
        this.userId = users;
        this.itemId = orderRequest.getItemId();
        this.orderDesc = orderRequest.getOrderDesc();
        this.orderDate = LocalDateTime.now();
    }
}
