package com.hcl.stockex.serviceimpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.stockex.dto.PurchaseRequestDTO;
import com.hcl.stockex.dto.PurchaseResponseDTO;
import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.entity.Stock;
import com.hcl.stockex.entity.StockTransaction;
import com.hcl.stockex.entity.User;
import com.hcl.stockex.exception.ApplicationException;
import com.hcl.stockex.repository.StockRepository;
import com.hcl.stockex.repository.StockTransactionRepository;
import com.hcl.stockex.repository.UserRepository;
import com.hcl.stockex.service.PurchaseService;
import com.hcl.stockex.util.RequestStatusUtil;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	

	@Autowired
	StockTransactionRepository stockTransactionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	StockRepository stockRepository;


	@Override
	public ResponseDTO reviewPurchase(PurchaseRequestDTO purchaseRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		Optional<StockTransaction> stockTransaction = stockTransactionRepository.getTransactionByUserIdAndStockId(purchaseRequestDTO.getUserId(), purchaseRequestDTO.getStockId(), RequestStatusUtil.INITIATED);
		if(stockTransaction.isPresent()) {
			StockTransaction transaction = stockTransaction.get();
			PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO(transaction.getUser().getId(), transaction.getStock().getId(), transaction.getStock().getStockName(), transaction.getStock().getStockType(), (transaction.getStock().getConfirmPrice()*transaction.getQuantity()), transaction.getStock().getConfirmPrice(), transaction.getQuantity(),transaction.getStatus());
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
		
		ResponseDTO responseDTO = new ResponseDTO();
		PurchaseResponseDTO responseObject = new PurchaseResponseDTO();
		
		StockTransaction transaction = new StockTransaction();
		
		Optional<User> optionalUserId = userRepository.findById(purchaseRequestDTO.getUserId());
		
		User user;
		if(optionalUserId.isPresent()) {
			user = optionalUserId.get();
		}else {
			throw new ApplicationException("Invalid user id.");
		}
		
		Optional<Stock> optionalStockId = stockRepository.findById(purchaseRequestDTO.getStockId());
		Stock stock;
		
		if(optionalStockId.isPresent()) {
			stock = optionalStockId.get();
		}else {
			throw new ApplicationException("Invalid stock id.");
		}
		
		transaction.setQuantity(purchaseRequestDTO.getQuantityOfStock());
		transaction.setStatus(RequestStatusUtil.INITIATED);
		transaction.setStockPrice(stock.getPurchasePrice());
		transaction.setTotalPrice(purchaseRequestDTO.getQuantityOfStock()*stock.getPurchasePrice());
		transaction.setStock(stock);
		transaction.setUser(user);
		
	    StockTransaction savedTransaction = stockTransactionRepository.save(transaction);
	    
	    BeanUtils.copyProperties(savedTransaction, responseObject);
	    responseObject.setUserId(user.getId());
	    responseObject.setPerStockPrice(stock.getPurchasePrice());
	    responseObject.setQuantityOfStock(purchaseRequestDTO.getQuantityOfStock());
	    responseObject.setStatus(RequestStatusUtil.INITIATED);
	    responseObject.setStockId(stock.getId());
	    responseObject.setStockName(stock.getStockName());
	    responseObject.setStockType(stock.getStockType());
	    responseObject.setTotalprice(purchaseRequestDTO.getQuantityOfStock()*stock.getPurchasePrice());

		
		responseDTO.setHttpStatus(HttpStatus.OK);
		responseDTO.setMessage("The stock prices change regularly and the quote is subject to change.  The order will be placed with the live price at the time of submission. "+"   "+"Order has been placed successfully");
		responseDTO.setData(responseObject);
		
		return responseDTO;
	}

	@Override
	public PurchaseResponseDTO completeTrnx(PurchaseRequestDTO purchaseReqDTO) throws ApplicationException {
		StockTransaction stockTrnx = stockTransactionRepository.getTrnxByUserIdAndStockId(purchaseReqDTO.getUserId(),
				purchaseReqDTO.getStockId());
		Stock stock = null;
		PurchaseResponseDTO purchaseResponseDTO = null;
		if (null != stockTrnx) {
			if (RequestStatusUtil.EXECUTED.equals(purchaseReqDTO.getStatus()) 
					|| RequestStatusUtil.DECLINED.equals(purchaseReqDTO.getStatus())) {
				if (RequestStatusUtil.REVIEWED.equals(stockTrnx.getStatus())) {
					Optional<Stock> stockOptional = stockRepository.findById(purchaseReqDTO.getStockId());
					if (stockOptional.isPresent()) {
						stock = stockOptional.get();
					} else {
						throw new ApplicationException("Stock id is not valid");
					}
					stockTrnx.setStatus(purchaseReqDTO.getStatus());
					Double stockPrice = stock.getExecutePrice();
					int stockQuantity = purchaseReqDTO.getQuantityOfStock();
					stockTrnx.setQuantity(stockQuantity);
					stockTrnx.setStockPrice(stockPrice);
					Double totalPrice = purchaseReqDTO.getQuantityOfStock() * stockPrice;
					stockTrnx.setTotalPrice(totalPrice);
					stockTransactionRepository.save(stockTrnx);
					purchaseResponseDTO = new PurchaseResponseDTO();
					purchaseResponseDTO.setPerStockPrice(stockPrice);
					purchaseResponseDTO.setQuantityOfStock(stockQuantity);
					purchaseResponseDTO.setStockId(stock.getId());
					purchaseResponseDTO.setStockName(stock.getStockName());
					purchaseResponseDTO.setStockType(stock.getStockType());
					purchaseResponseDTO.setTotalprice(totalPrice);
					purchaseResponseDTO.setUserId(purchaseReqDTO.getUserId());

				} 
				else {
					throw new ApplicationException("Provided transaction is not valid");
				}
			} 
			else {
				throw new ApplicationException("Transaction is not valid");
			}

		} else {
			throw new ApplicationException("No transaaction for requested information");
		}
		return purchaseResponseDTO;
	}

}
