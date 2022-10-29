package com.project.IIITBuyNSell.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 2818186066152946823L;
    private Object data;
    private String message;
}

