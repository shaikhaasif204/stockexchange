package com.hcl.stockex.service;

import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.exception.ApplicationException;

public interface StockService {
	
	public ResponseDTO getAllStockDetails() throws ApplicationException;

}
