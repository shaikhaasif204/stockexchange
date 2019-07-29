package com.hcl.stockex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponseDTO {
	
	private Long UserId;
	
	private Long StockId;
	
	private String stockName;

	private String stockType;
	
	private Double Totalprice;
	
	private Double PerStockPrice;
	
	private Integer QuantityOfStock;
	
	private Integer status;

	public PurchaseResponseDTO(Long userId, Long stockId, String stockName, String stockType, Double totalprice,
			Double perStockPrice, Integer quantityOfStock) {
		super();
		UserId = userId;
		StockId = stockId;
		this.stockName = stockName;
		this.stockType = stockType;
		Totalprice = totalprice;
		PerStockPrice = perStockPrice;
		QuantityOfStock = quantityOfStock;
	}

}
