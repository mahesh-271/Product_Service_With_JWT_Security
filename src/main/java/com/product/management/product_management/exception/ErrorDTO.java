package com.product.management.product_management.exception;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorDTO {

    private String errorMessage;
    private Integer statusCode;
    private Date timeStamp;
    private String path;
}
