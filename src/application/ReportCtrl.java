package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * 
 * @author Melker Holmgren and Stefan Gustafsson
 *
 */


public class ReportCtrl implements Initializable {
	
	@FXML
	Button ok;
	@FXML
	Label startDateLabel, endDateLabel;

	@FXML
	TableView<ReportCommision> monthly_tw;
	@FXML
	TableView<CustomerValue> cusomer_tw;
	@FXML
	TableView<CommisionForCast> commision_tw;
	@FXML
	TableView<ReportNoBids> nobid_tw;
	

	@FXML
	TableColumn<CustomerValue, String> cust_col, value_col;
	@FXML
	TableColumn<CommisionForCast, String> auction_id, product, Start_date, End_Date, commision_1;
	@FXML
	TableColumn<ReportNoBids, String> auction_id_nobid, product_nobid;
	
	@FXML
	TableColumn<ReportCommision, String> Month_com, total_com;
	@FXML
	DatePicker pick_start, pick_end;

	private Connection con;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;

	private ObservableList<CommisionForCast> commisionDateList;
	private ObservableList<CustomerValue> customerList;
	private ObservableList<ReportCommision> commisionList;
	private ObservableList<ReportNoBids> noBidList;

	public void getMonthlyCommision() {
		
		startDateLabel.setVisible(false);
		endDateLabel.setVisible(false);

		commisionDateList = FXCollections.observableArrayList();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
			pst = con.prepareStatement(
					"SELECT  auction.auction_id, product.product_name, auction.startdate, auction.enddate, commision_rate*bid/100 FROM auction "
							+ "INNER JOIN product ON product.product_id = auction.product_id "
							+ "INNER JOIN supplier ON supplier.supplier_id = product.supplier_id "
							+ "INNER JOIN currentbids ON currentbids.auction_id = auction.auction_id "
							+ "WHERE auction.enddate BETWEEN ? AND ?;");
			
			if(pick_start.getValue() == null && pick_end.getValue() == null) {
				startDateLabel.setVisible(true);
				endDateLabel.setVisible(true);
			}
			else if(pick_start.getValue() == null) {
				startDateLabel.setVisible(true);
			}
			else if(pick_end.getValue() == null){
				endDateLabel.setVisible(true);
			}
			else {
				pst.setDate(1, Date.valueOf(pick_start.getValue()));
				pst.setDate(2, Date.valueOf(pick_end.getValue()));
				rs = pst.executeQuery();
	
				while (rs.next()) {
					commisionDateList.add(new CommisionForCast(rs.getInt(1),rs.getString(2),rs.getDate(3).toLocalDate(),
							rs.getDate(4).toLocalDate(), rs.getInt(5)));
	
				}
				commision_tw.setItems(commisionDateList);
			}

		} catch (SQLException e) {
			System.out.println("SQL ERROR" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void getCustomerOrder() {

		customerList = FXCollections.observableArrayList();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
			st = con.createStatement();
			rs = st.executeQuery("SELECT Name,`Total Order Value` FROM auctionsdb.customer_list_ordervalue;");

			while (rs.next()) {
				customerList.add(new CustomerValue(rs.getString(1), rs.getInt(2)));

			}
			cusomer_tw.setItems(customerList);

		} catch (SQLException e) {
			System.out.println("SQL ERROR" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void getCommisionReport() {

		commisionList = FXCollections.observableArrayList();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT `Date`, SUM(Commision) FROM commisionbyauction GROUP BY `Date` ORDER BY `Date` DESC;");

			while (rs.next()) {
				commisionList.add(new ReportCommision(rs.getInt(2), rs.getString(1)));

			}
			monthly_tw.setItems(commisionList);

		} catch (SQLException e) {
			System.out.println("SQL ERROR" + e.getMessage());
			e.printStackTrace();
		}

	}
	
	
	public void getNoBidAuctions(){
	
		noBidList = FXCollections.observableArrayList();
				
	try{	
		con = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
		st = con.createStatement();
		rs = st.executeQuery(
				"SELECT auctionhistory.auction_id, product.product_name "
				+ "FROM auctionhistory "
				+ "LEFT JOIN bidhistory ON bidhistory.auction_id = auctionhistory.auction_id "
				+ "INNER JOIN product ON product.product_id = auctionhistory.product_id "
				+ "WHERE bidhistory.bid IS NULL;");
		
		while (rs.next()) {
			noBidList.add(new ReportNoBids(rs.getInt(1), rs.getString(2)));

		}
		nobid_tw.setItems(noBidList);
		
		
		
		
	} catch (SQLException e) {
		System.out.println("SQL ERROR" + e.getMessage());
		e.printStackTrace();
	}
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Month_com.setCellValueFactory(celData -> celData.getValue().getDate());
		total_com.setCellValueFactory(celData -> celData.getValue().getCommision().asString());
		cust_col.setCellValueFactory(celData -> celData.getValue().getName());
		value_col.setCellValueFactory(celData -> celData.getValue().getTotalValue().asString());
		auction_id.setCellValueFactory(celData -> celData.getValue().getAuction_id().asString());
		product.setCellValueFactory(celData -> celData.getValue().getProduct());
		Start_date.setCellValueFactory(celData -> celData.getValue().getStart_date().asString());
		End_Date.setCellValueFactory(celData -> celData.getValue().getEnd_date().asString());
		commision_1.setCellValueFactory(celData -> celData.getValue().getCommision().asString());
		auction_id_nobid.setCellValueFactory(celData -> celData.getValue().getAuction_id().asString());
		product_nobid.setCellValueFactory(celData -> celData.getValue().getProductName());
		
		
	}
}