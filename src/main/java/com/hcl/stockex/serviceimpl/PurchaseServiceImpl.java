package com.hcl.stockex.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.stockex.dto.PurchaseRequestDTO;
import com.hcl.stockex.dto.PurchaseResponseDTO;
import com.hcl.stockex.entity.StockTransaction;
import com.hcl.stockex.repository.StockTransactionRepository;
import com.hcl.stockex.service.PurchaseService;
import com.hcl.stockex.util.RequestStatusUtil;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	StockTransactionRepository stockTransactionRepository;

	@Override
	public PurchaseResponseDTO reviewPurchase(PurchaseRequestDTO purchaseRequestDTO) {
		Optional<StockTransaction> stockTransaction = stockTransactionRepository.getTransactionByUserIdAndStockId(purchaseRequestDTO.getUserId(), purchaseRequestDTO.getStockId(), RequestStatusUtil.INITIATED);
		if(stockTransaction.isPresent()) {
			StockTransaction transaction = stockTransaction.get();
			PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO(transaction.getUserId().getId(), transaction.getStockId().getId(), transaction.getStockId().getStockName(), transaction.getStockId().getStockType(), (transaction.getStockId().getConfirmPrice()*transaction.getQuantaty()), transaction.getStockId().getConfirmPrice(), transaction.getQuantaty());
			transaction.setStatus(RequestStatusUtil.REVIEWED);
			stockTransactionRepository.save(transaction);
		}
		return null;
	}

}
