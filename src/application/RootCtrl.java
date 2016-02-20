package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Ajmal Bahawodin, Stefan 	Gustafsson and Melker Holmgren
 *
 */
public class RootCtrl {
	
	private Main main;
	private AnchorPane ongoingAuctions;
	
	@FXML
	private BorderPane root;

	@FXML
	public void showOngoingAuctions() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("OngoingAuctionsView.fxml"));
			ongoingAuctions = (AnchorPane) loader.load();
			root.setCenter(ongoingAuctions);
			
			OngoingAuctionsCtrl controller = loader.getController();
			controller.setMain(main);
			controller.initAuctions();
			controller.setRootCtrl(this);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}	
	}
	
	@FXML
	public void addSupplier() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("addsupplier.fxml"));
			AnchorPane addSupplierView = (AnchorPane) loader.load();
			root.setCenter(addSupplierView);
			
			AddSupplierCtrl controller = loader.getController();
			controller.setRootCtrl(this);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}	
	}
	
	@FXML
	public void showReports() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("reports.fxml"));
			AnchorPane reportView = (AnchorPane) loader.load();
			
			ReportCtrl controller = loader.getController();
            controller.getCommisionReport();
            controller.getCustomerOrder();
            controller.getNoBidAuctions();
            root.setCenter(reportView);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}	
	}
	
	@FXML
	public void addAuction() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("addauctionAndProduct.fxml"));
			AnchorPane addAuctionView = (AnchorPane) loader.load();
			root.setCenter(addAuctionView);
			
			AddauctionAndProductCtrl controller = loader.getController();
			controller.readInProductsNotInAuction();
			controller.setMain(main);
			controller.setRootCtrl(this);
		
		} catch (IOException e) {
			e.printStackTrace();
			
		}	
	}
	
	@FXML
	public void addCustomer() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("addcustomer.fxml"));
			BorderPane addCustomerView = (BorderPane) loader.load();
			root.setCenter(addCustomerView);
			
			AddCustomerCtrl controller = loader.getController();
			controller.setRootCtrl(this);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}	
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setToOngoingAuctions() {
		root.setCenter(ongoingAuctions);
	}
	
	public void setRootCenter(Node node) {
		root.setCenter(node);
	}

}
