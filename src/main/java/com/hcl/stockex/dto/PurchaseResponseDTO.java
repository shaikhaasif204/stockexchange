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

	public PurchaseResponseDTO(Long userId, Long stockId, String stockName, String stockType, Double totalprice,
			Double perStockPrice, Integer quantityOfStock) {
		super();
		userId = userId;
		stockId = stockId;
		this.stockName = stockName;
		this.stockType = stockType;
		totalprice = totalprice;
		perStockPrice = perStockPrice;
		quantityOfStock = quantityOfStock;
	}

}
