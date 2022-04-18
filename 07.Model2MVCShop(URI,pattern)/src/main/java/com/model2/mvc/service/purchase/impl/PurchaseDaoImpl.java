package com.model2.mvc.service.purchase.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;


@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	
	// field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	
	// ctor
	public PurchaseDaoImpl() {
		System.out.println("[ "+this.getClass()+" ] :: start"); // debugging
	}

	
	// method
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}

	@Override
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception {
		search.setSearchKeyword(userId);	
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", search);
	}

	@Override
	public List<Purchase> getUserSaleList(Search search, String userId) throws Exception {	
		search.setSearchCondition("0");		
		search.setSearchKeyword(userId);		
		System.out.println(search);
		return sqlSession.selectList("PurchaseMapper.getUserSaleList", search);
	}

	@Override
	public List<Purchase> getSaleList(Search search) throws Exception {	
		return sqlSession.selectList("PurchaseMapper.getSaleList", search);
		
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);

	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}
	
	@Override
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", search);
	}

}
