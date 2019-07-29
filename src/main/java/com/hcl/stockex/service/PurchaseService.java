package com.hcl.stockex.service;

import com.hcl.stockex.dto.PurchaseRequestDTO;
import com.hcl.stockex.dto.PurchaseResponseDTO;
import com.hcl.stockex.dto.ResponseDTO;
import com.hcl.stockex.exception.ApplicationException;

public interface PurchaseService {

	public ResponseDTO reviewPurchase(PurchaseRequestDTO purchaseRequestDTO);

	public ResponseDTO purchaseStock(PurchaseRequestDTO purchaseRequestDTO) throws ApplicationException;
	
	public PurchaseResponseDTO completeTrnx(PurchaseRequestDTO purchaseReqDTO) throws ApplicationException;}
