package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {

	private IntegerProperty productID, supplierID, categoryID, acceptancePrice, startPrice;
	private StringProperty productName, descrption;
	
	public Product(int productID, int supplierID, int categoryID,
			String productName, String descrption, int acceptancePrice, 
			int startPrice) {
		this.productID = new SimpleIntegerProperty(productID);
		this.supplierID = new SimpleIntegerProperty(supplierID);
		this.categoryID = new SimpleIntegerProperty(categoryID);
		this.acceptancePrice = new SimpleIntegerProperty(acceptancePrice);
		this.startPrice = new SimpleIntegerProperty(startPrice);
		this.productName = new SimpleStringProperty(productName);
		this.descrption = new SimpleStringProperty(descrption);
	}

	/**
	 * @return the productID
	 */
	public IntegerProperty getProductID() {
		return productID;
	}

	/**
	 * @param productID the productID to set
	 */
	public void setProductID(IntegerProperty productID) {
		this.productID = productID;
	}

	/**
	 * @return the supplierID
	 */
	public IntegerProperty getSupplierID() {
		return supplierID;
	}

	/**
	 * @param supplierID the supplierID to set
	 */
	public void setSupplierID(IntegerProperty supplierID) {
		this.supplierID = supplierID;
	}

	/**
	 * @return the categoryID
	 */
	public IntegerProperty getCategoryID() {
		return categoryID;
	}

	/**
	 * @param categoryID the categoryID to set
	 */
	public void setCategoryID(IntegerProperty categoryID) {
		this.categoryID = categoryID;
	}

	/**
	 * @return the acceptancePrice
	 */
	public IntegerProperty getAcceptancePrice() {
		return acceptancePrice;
	}

	/**
	 * @param acceptancePrice the acceptancePrice to set
	 */
	public void setAcceptancePrice(IntegerProperty acceptancePrice) {
		this.acceptancePrice = acceptancePrice;
	}

	/**
	 * @return the startPrice
	 */
	public IntegerProperty getStartPrice() {
		return startPrice;
	}

	/**
	 * @param startPrice the startPrice to set
	 */
	public void setStartPrice(IntegerProperty startPrice) {
		this.startPrice = startPrice;
	}

	/**
	 * @return the productName
	 */
	public StringProperty getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(StringProperty productName) {
		this.productName = productName;
	}

	/**
	 * @return the descrption
	 */
	public StringProperty getDescrption() {
		return descrption;
	}

	/**
	 * @param descrption the descrption to set
	 */
	public void setDescrption(StringProperty descrption) {
		this.descrption = descrption;
	}
	
	@Override
	public String toString(){
		return "ID "+productID.getValue().toString()+", "+productName.get();
	}
	
	

}