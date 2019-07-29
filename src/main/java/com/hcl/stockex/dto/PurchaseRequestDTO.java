package com.hcl.stockex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDTO {
	
    private Long UserId;
	
	private Long StockId;
	
	private String stockName;

	private String stockType;
	
	private Double PerStockPrice;
	
	private Integer QuantityOfStock;

}
