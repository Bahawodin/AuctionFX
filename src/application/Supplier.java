package application;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Supplier {

	private IntegerProperty supplierID;
	private StringProperty orgNumber, companyName;
	private FloatProperty commisionRate;
	private ObservableList<Product> productList;
	
	public Supplier(int supplierID, String orgNumber, String companyName,
			float commisionRate) {
		this.supplierID = new SimpleIntegerProperty(supplierID);
		this.orgNumber = new SimpleStringProperty(orgNumber);
		this.companyName = new SimpleStringProperty(companyName);
		this.commisionRate = new SimpleFloatProperty(commisionRate);
		productList = FXCollections.observableArrayList();
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
	 * @return the orgNumber
	 */
	public StringProperty getOrgNumber() {
		return orgNumber;
	}

	/**
	 * @param orgNumber the orgNumber to set
	 */
	public void setOrgNumber(StringProperty orgNumber) {
		this.orgNumber = orgNumber;
	}

	/**
	 * @return the companyName
	 */
	public StringProperty getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(StringProperty companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the commisionRate
	 */
	public FloatProperty getCommisionRate() {
		return commisionRate;
	}

	/**
	 * @param commisionRate the commisionRate to set
	 */
	public void setCommisionRate(FloatProperty commisionRate) {
		this.commisionRate = commisionRate;
	}

	/**
	 * @return the productList
	 */
	public ObservableList<Product> getProductList() {
		return productList;
	}
	
	public void addProductToList(Product prod){
		productList.add(prod);
	}
	
	@Override
	public String toString(){
		return companyName.getValue();
	}

	

}
