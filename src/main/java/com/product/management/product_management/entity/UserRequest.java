package com.product.management.product_management.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor
@Builder
public class UserRequest {

    private String userName;
    private String passWord;
}
