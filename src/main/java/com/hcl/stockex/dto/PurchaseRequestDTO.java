package com.hcl.stockex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDTO {
	
    private Long userId;
	
	private Long stockId;
	
	private String stockName;
	
	private Integer quantityOfStock;
	
	private Integer status;

}
