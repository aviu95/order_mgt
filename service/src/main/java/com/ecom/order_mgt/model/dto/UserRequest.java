package com.ecom.order_mgt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Boolean fullSync;

    private List<Long> userIds;
}
