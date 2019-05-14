package com.ecom.order_mgt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Paged<T> {

    private List<T> items;

    private Integer pageIndex;

    private Integer size;

    private Integer totalPages;
}
