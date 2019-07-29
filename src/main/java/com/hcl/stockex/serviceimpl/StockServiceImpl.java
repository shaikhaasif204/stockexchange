package com.hcl.stockex.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.entity.Stock;
import com.hcl.stockex.exception.ApplicationException;
import com.hcl.stockex.repository.StockRepository;
import com.hcl.stockex.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;

	@Override
	public ResponseDTO getAllStockDetails() throws ApplicationException {
		
		ResponseDTO responseDTO = new ResponseDTO();
		List<Stock> stockList = stockRepository.findAll();
		
		if (null != stockList && 0 < stockList.size() )  {
			responseDTO.setHttpStatus(HttpStatus.OK);
			responseDTO.setMessage("Warning : Stock prices change regularly and the quote is subject to change. ");
			responseDTO.setData(stockList);
		}else {
			responseDTO.setHttpStatus(HttpStatus.OK);
			responseDTO.setMessage("No stocks found");
		}
		return responseDTO;
	}
	
}
