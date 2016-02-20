package application;

import java.io.IOException;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author Ajmal Bahawodin
 *
 */
public class OngoingAuctionsCtrl {
	
	private Main main;
	private RootCtrl rootCtrl;
	
	@FXML
	private TableView<OngoingAuction> bidView;
	@FXML
	private TableColumn<OngoingAuction, String> auctionID, supplier, product, startPrice, acceptPrice, highestBid, endDate, bider;
	@FXML
	private TextField searchField;
	
	@FXML
	private void initialize() {
		auctionID.setCellValueFactory(celData -> celData.getValue().getAuctionID().asString());
		supplier.setCellValueFactory(celData -> celData.getValue().getSupplier());
		product.setCellValueFactory(celData -> celData.getValue().getProduct());
		startPrice.setCellValueFactory(celData -> celData.getValue().getStartPrice().asString());
		acceptPrice.setCellValueFactory(celData -> celData.getValue().getAcceptPrice());
		highestBid.setCellValueFactory(celData -> celData.getValue().getHighestBid().asString());
		endDate.setCellValueFactory(celData -> celData.getValue().getEndDate().asString());
		bider.setCellValueFactory(celData -> celData.getValue().getBider());
		
		setOnActionClickedAuction();

	}
	
	public void initAuctions() {
		FilteredList<OngoingAuction> filteredList = new FilteredList<>(main.getAuctionList(), (p) -> true);

		searchField.textProperty().addListener((observable, oldValue, newValue) -> {

			filteredList.setPredicate((OngoingAuctions) -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				if (OngoingAuctions.getSupplier().getValue().toLowerCase().contains(newValue.toLowerCase())) {
					return true;
				}
				else if (OngoingAuctions.getProduct().getValue().toLowerCase().contains(newValue.toLowerCase())) {
					return true;
				}
				else if (OngoingAuctions.getBider().getValue().toLowerCase().contains(newValue.toLowerCase())) {
					return true;
				}
				return false;
			});
		});

		SortedList<OngoingAuction> sortedList = new SortedList<>(filteredList);
		sortedList.comparatorProperty().bind(bidView.comparatorProperty());
		bidView.setItems(sortedList);
	}
	
	@FXML
	private void setOnActionClickedAuction() {
		bidView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("singleProductView.fxml"));
				AnchorPane singleProductView = (AnchorPane) loader.load();
				rootCtrl.setRootCenter(singleProductView);

				ProductViewCtrl controller = loader.getController();
				controller.setRecords(newValue);

			} catch (IOException e) {

				e.printStackTrace();
			}
		});
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * @param rootCtrl the rootCtrl to set
	 */
	public void setRootCtrl(RootCtrl rootCtrl) {
		this.rootCtrl = rootCtrl;
	}

}
