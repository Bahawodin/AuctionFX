package application;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Ajmal Bahawodin
 *
 */
public class OngoingAuction {
	
	private StringProperty supplier, product, bider, productDescription;
	private IntegerProperty auctionID, startPrice, acceptPrice, highestBid;
	private ObjectProperty<LocalDate> endDate;
	
	public OngoingAuction(int auctionID, String supplier, String product, int startPrice, 
			int acceptPrice, int highestBid, String bider, LocalDate endDate, String productDescription) {
		this.supplier = new SimpleStringProperty(supplier);
		this.product = new SimpleStringProperty(product);
		this.bider = new SimpleStringProperty(bider);
		this.auctionID = new SimpleIntegerProperty(auctionID);
		this.startPrice = new SimpleIntegerProperty(startPrice);
		this.acceptPrice = new SimpleIntegerProperty(acceptPrice);
		this.highestBid = new SimpleIntegerProperty(highestBid);
		this.endDate = new SimpleObjectProperty<LocalDate>(endDate);
		this.productDescription = new SimpleStringProperty(productDescription);
	}


	/**
	 * @return the supplier
	 */
	public StringProperty getSupplier() {
		return supplier;
	}


	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(StringProperty supplier) {
		this.supplier = supplier;
	}


	/**
	 * @return the product
	 */
	public StringProperty getProduct() {
		return product;
	}


	/**
	 * @param product the product to set
	 */
	public void setProduct(StringProperty product) {
		this.product = product;
	}


	/**
	 * @return the bider
	 */
	public StringProperty getBider() {
		return bider;
	}


	/**
	 * @param bider the bider to set
	 */
	public void setBider(StringProperty bider) {
		this.bider = bider;
	}


	/**
	 * @return the auctionID
	 */
	public IntegerProperty getAuctionID() {
		return auctionID;
	}


	/**
	 * @param auctionID the auctionID to set
	 */
	public void setAuctionID(IntegerProperty auctionID) {
		this.auctionID = auctionID;
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
	 * @return the acceptPrice
	 */
	public StringProperty getAcceptPrice() {
		if(acceptPrice.get() == 0) {
			return new SimpleStringProperty("N/A");
		}
		else{
			return new SimpleStringProperty(acceptPrice.getValue().toString());
		}
	}


	/**
	 * @param acceptPrice the acceptPrice to set
	 */
	public void setAcceptPrice(IntegerProperty acceptPrice) {
		this.acceptPrice = acceptPrice;
	}


	/**
	 * @return the highestBid
	 */
	public IntegerProperty getHighestBid() {
			return highestBid;
	}


	/**
	 * @param highestBid the highestBid to set
	 */
	public void setHighestBid(IntegerProperty highestBid) {
		this.highestBid = highestBid;
	}


	/**
	 * @return the endDate
	 */
	public ObjectProperty<LocalDate> getEndDate() {
		return endDate;
	}


	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(ObjectProperty<LocalDate> endDate) {
		this.endDate = endDate;
	}


	/**
	 * @return the productDescription
	 */
	public StringProperty getProductDescription() {
		return productDescription;
	}


	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(StringProperty productDescription) {
		this.productDescription = productDescription;
	}
}
