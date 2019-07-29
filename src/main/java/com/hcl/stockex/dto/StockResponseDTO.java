package com.hcl.stockex.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDTO {
	
	private Long id;

	private String stockName;

	private String stockType;

	private String region;

	private LocalTime marketOpen;

	private LocalTime marketClose;

	private Double price;

}
