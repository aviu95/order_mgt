package com.ecom.order_mgt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long userId;

    private Long itemId;

    private String orderDesc;


}
