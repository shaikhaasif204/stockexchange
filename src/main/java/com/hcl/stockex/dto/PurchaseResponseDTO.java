package com.hcl.stockex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponseDTO {
	
	private Long userId;
	
	private Long stockId;
	
	private String stockName;

	private String stockType;
	
	private Double totalprice;
	
	private Double perStockPrice;
	
	private Integer quantityOfStock;
	
	private Integer status;

}
