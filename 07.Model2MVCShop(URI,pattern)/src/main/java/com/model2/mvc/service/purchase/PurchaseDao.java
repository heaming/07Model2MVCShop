package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {
	
	// INSERT
	public void addPurchase(Purchase purchase) throws Exception;
		
	// SELECT ONE
	public Purchase getPurchase(int tranNo) throws Exception;
	
	// SELECT LIST : User Purchase
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception;
	
	// SELECT LIST : User Sale
	public List<Purchase> getUserSaleList(Search search, String userId) throws Exception;
	
	// SELECT LIST : ALL
	public List<Purchase> getSaleList(Search search) throws Exception;
	
	// UPDATE : Purchase
	public void updatePurchase(Purchase purchase) throws Exception;
	
	// UPDATE : TranCode
	public void updateTranCode(Purchase purchase) throws Exception;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception ;
	
}
