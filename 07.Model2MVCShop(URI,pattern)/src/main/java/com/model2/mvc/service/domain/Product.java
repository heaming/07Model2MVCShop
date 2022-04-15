package com.model2.mvc.service.domain;

import java.sql.Date;

public class Product {
	
	// field
	private String sellerId;
	private String fileName;
	private String dueDate;
	private int cost;
	private int price;
	private String prodDetail;
	private String prodName;
	private int prodNo;
	private int prodCode;
	private Date regDate;
	private String proTranCode;
	
	
	// ctor
	public Product(){
	}


	// method :: getter & setter
	public String getSellerId() {
		return sellerId;
	}

	
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getDueDate() {
		return dueDate;
	}


	public void setDueDate(String dueDate) {
		if(dueDate.length() > 8) {
			this.dueDate = dueDate.replace("-","");
		} else {		
		this.dueDate = dueDate;
		}
	}

	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getProdDetail() {
		return prodDetail;
	}


	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}


	public String getProdName() {
		return prodName;
	}


	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public int getProdNo() {
		return prodNo;
	}


	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}


	public int getProdCode() {
		return prodCode;
	}


	public void setProdCode(int prodCode) {
		this.prodCode = prodCode;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public String getProTranCode() {
		return proTranCode;
	}


	public void setProTranCode(String proTranCode) {
		this.proTranCode = proTranCode;
	}


	@Override
	public String toString() {
		return "Product [sellerId=" + sellerId + ", fileName=" + fileName + ", dueDate=" + dueDate + ", cost=" + cost
				+ ", price=" + price + ", prodDetail=" + prodDetail + ", prodName=" + prodName + ", prodNo=" + prodNo
				+ ", prodCode=" + prodCode + ", regDate=" + regDate + ", proTranCode=" + proTranCode + "]";
	}

}
