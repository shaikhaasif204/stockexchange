package com.hcl.stockex.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.stockex.dto.PurchaseResponseDTO;
import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.entity.StockTransaction;
import com.hcl.stockex.repository.StockTransactionRepository;
import com.hcl.stockex.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	StockTransactionRepository stockTransactionRepository;
	
	
	@Override
	public ResponseDTO getHistory(Long userId) {
		List<StockTransaction> history = stockTransactionRepository.findAllByUserId(userId);
		System.out.println(history);
		
		ResponseDTO responseDTO = new ResponseDTO();
		List<PurchaseResponseDTO> purchaseResponseDTOList = history.stream().map(i -> new PurchaseResponseDTO(i.getUser().getId(),i.getStock().getId(),
				i.getStock().getStockName(),i.getStock().getStockType(),i.getTotalPrice(),i.getStockPrice(),i.getQuantity())).collect(Collectors.toList());
		responseDTO.setHttpStatus(HttpStatus.OK);
		responseDTO.setMessage("List of Past Purchase Tradings");
		responseDTO.setData(purchaseResponseDTOList);
		
		return null;
	}

	

}
