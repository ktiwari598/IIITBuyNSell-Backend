package com.project.IIITBuyNSell.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigInteger;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO  implements Serializable {
    private BigInteger itemId;
    @NotEmpty
    private String itemName;
    private String itemDescription;
    @NotEmpty
    private BigInteger itemPrice;
    @NotEmpty
    private String itemCategory;
    @Builder.Default
    private Boolean isLostFound = false;

    private UserDTO user;
}
