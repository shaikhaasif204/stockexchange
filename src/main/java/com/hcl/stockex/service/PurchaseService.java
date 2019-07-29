package com.hcl.stockex.service;

import com.hcl.stockex.dto.PurchaseRequestDTO;
import com.hcl.stockex.dto.PurchaseResponseDTO;

public interface PurchaseService {

	public PurchaseResponseDTO reviewPurchase(PurchaseRequestDTO purchaseRequestDTO);
}
