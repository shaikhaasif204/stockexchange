package com.hcl.stockex.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.stockex.dto.PurchaseRequestDTO;
import com.hcl.stockex.dto.PurchaseResponseDTO;
import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.entity.StockTransaction;
import com.hcl.stockex.exception.ApplicationException;
import com.hcl.stockex.repository.StockTransactionRepository;
import com.hcl.stockex.service.PurchaseService;
import com.hcl.stockex.util.RequestStatusUtil;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	StockTransactionRepository stockTransactionRepository;

	@Override
	public ResponseDTO reviewPurchase(PurchaseRequestDTO purchaseRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Optional<StockTransaction> stockTransaction = stockTransactionRepository.getTransactionByUserIdAndStockId(purchaseRequestDTO.getUserId(), purchaseRequestDTO.getStockId(), RequestStatusUtil.INITIATED);
		if(stockTransaction.isPresent()) {
			StockTransaction transaction = stockTransaction.get();
			PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO(transaction.getUser().getId(), transaction.getStock().getId(), transaction.getStock().getStockName(), transaction.getStock().getStockType(), (transaction.getStock().getConfirmPrice()*transaction.getQuantity()), transaction.getStock().getConfirmPrice(), transaction.getQuantity());
			transaction.setStatus(RequestStatusUtil.REVIEWED);
			stockTransactionRepository.save(transaction);
			responseDTO.setMessage("Review your order");
			responseDTO.setData(purchaseResponseDTO);
			responseDTO.setHttpStatus(HttpStatus.FOUND);
			return responseDTO;
		}
		responseDTO.setMessage("Please enter valid details");
		responseDTO.setData(null);
		responseDTO.setHttpStatus(HttpStatus.NOT_FOUND);
		return responseDTO;
	}
	@Override
	public ResponseDTO purchaseStock(PurchaseRequestDTO purchaseRequestDTO) throws ApplicationException {
		
		return null;
	}

}
