package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddauctionAndProductCtrl {
	
	Main main;
	RootCtrl rootCtrl;
	
	private ObservableList<Supplier> suplierList;
	private ObservableList<Category> categoryList;
	
	@FXML
	private ComboBox<Supplier> pick_supplier;
	@FXML
	private ComboBox<Product> pick_product;
	@FXML
	private ComboBox<Category> category;
	@FXML
	private DatePicker start_date, end_date;
	@FXML
	private TextField start_price, accept_price, supplierOrgNr, productName, reg_start_price, reg_accept_price;
	@FXML
	private TextArea productDescription;
	@FXML
	private Label notRegistered, auctionLabel, productLabel, startLabel1, startLabel2, acceptLabel1, acceptLabel2;
	
	
	@FXML
	public void initialize(){
		pick_supplier.setOnAction((e) -> { pick_product.getItems().clear();
			pick_product.getItems().addAll(pick_supplier.getSelectionModel().getSelectedItem().getProductList());
		});
		readInCategories();
	}

	@FXML
	public void readInProductsNotInAuction(){
		suplierList = FXCollections.observableArrayList();
		boolean supExist = false;
		int supIndex = 0;
		boolean prodExist = false;
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
			stm = conn.createStatement();
			rs = stm.executeQuery("SELECT * FROM auctionsDb.products_not_in_auction;");
			
			while(rs.next()){
				Supplier sup = new Supplier(rs.getInt(7), rs.getString(8), rs.getString(9), rs.getFloat(10));
				Product prod = new Product(rs.getInt(1), rs.getInt(7), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
				
				for (int i = 0; i < suplierList.size(); i++) {
					if(sup.getSupplierID().get() == suplierList.get(i).getSupplierID().get()) {
						supExist = true;
						supIndex = i;
					}
					
				}
				if (!supExist){
					sup.addProductToList(prod);
					suplierList.add(sup);
				}
				else {
					for (int i = 0; i < suplierList.get(supIndex).getProductList().size(); i++) {
						if(suplierList.get(supIndex).getProductList().get(i).getProductID().get() == prod.getProductID().get()){
							prodExist = true;
						}
					}
					if(!prodExist){
						suplierList.get(supIndex).getProductList().add(prod);
					}
				}
			}
			pick_supplier.getItems().addAll(suplierList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@FXML
	private void addToAuctions(){
		Connection conn = null;
		CallableStatement cstm = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
			
			if (pick_supplier.getSelectionModel().getSelectedItem() == null || pick_product.getSelectionModel().getSelectedItem() == null 
					|| start_price.getText().isEmpty() || start_date.getValue() == null || end_date.getValue() == null) {
				auctionLabel.setVisible(true);
			}
			else if(isNumeric(start_price.getText())) {
				startLabel1.setVisible(true);
			}
			else if (!accept_price.getText().isEmpty() && isNumeric(accept_price.getText())) {
				acceptLabel1.setVisible(true);
			}
			else {
				if (accept_price.getText().isEmpty()) { accept_price.setText("0"); }
				conn.setAutoCommit(false);
				
				cstm = conn.prepareCall("{CALL createAuction(?, ?, ?, ? ,?)}");
				cstm.setInt(1, pick_product.getSelectionModel().getSelectedItem().getProductID().getValue().intValue());
				cstm.setInt(2, Integer.parseInt(start_price.getText()));
				cstm.setInt(3, Integer.parseInt(accept_price.getText()));
				cstm.setDate(4, Date.valueOf(start_date.getValue()));
				cstm.setDate(5, Date.valueOf(end_date.getValue()));
				
				cstm.execute();
				
				stm = conn.createStatement();
				rs = stm.executeQuery("SELECT MAX(auction_id) AS max, supplier.company_name FROM auction "
						+ "INNER JOIN product ON auction.product_id = product.product_id "
						+ "INNER JOIN supplier ON supplier.supplier_id = product.supplier_id;");
				
				conn.commit();
				
				rs.next();
	
				main.getAuctionList().add(new OngoingAuction(rs.getInt(1), rs.getString(2), pick_product.getSelectionModel().getSelectedItem().getProductName().getValue(),
						Integer.parseInt(start_price.getText()), Integer.parseInt(accept_price.getText()), 0, "N/A", end_date.getValue(),
						pick_product.getSelectionModel().getSelectedItem().getDescrption().getValue()));
				
				rootCtrl.addAuction();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
				rs.close();
				if(stm != null)
				stm.close();
				if(cstm != null)
				cstm.close();
				if(conn != null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void readInCategories(){
		categoryList = FXCollections.observableArrayList();
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
			stm = conn.createStatement();
			rs = stm.executeQuery("SELECT * FROM auctionsDb.category;");
			
			while(rs.next()){
				categoryList.add(new Category(rs.getInt(1), rs.getString(2)));
			}
			category.getItems().addAll(categoryList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void addProduct(){
		Connection conn = null;
		CallableStatement cstm = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
			
			if(supplierOrgNr.getText().isEmpty() || category.getSelectionModel().getSelectedItem() == null || productName.getText().isEmpty()
					|| reg_accept_price.getText().isEmpty() || reg_start_price.getText().isEmpty()) {
				productLabel.setVisible(true);
			}
			else if(isNumeric(reg_accept_price.getText())) {
				startLabel2.setVisible(true);
			}
			else if (isNumeric(reg_start_price.getText())) {
				acceptLabel2.setVisible(true);
			}
			else {
				pstm = conn.prepareStatement("SELECT supplier.supplier_id FROM supplier WHERE supplier.org_number = ?;");
				pstm.setString(1, supplierOrgNr.getText());
				rs = pstm.executeQuery();
				
				if(rs.next()) {
					int supplierID = rs.getInt(1);
					
					cstm = conn.prepareCall("{CALL RegisterProduct(?, ?, ?, ?, ?, ?)}");
					cstm.setInt(1, supplierID);
					cstm.setInt(2, category.getSelectionModel().getSelectedItem().getCategoryId().get());
					cstm.setString(3, productName.getText());
					cstm.setString(4, productDescription.getText());
					cstm.setInt(5, Integer.parseInt(reg_accept_price.getText()));
					cstm.setInt(6, Integer.parseInt(reg_start_price.getText()));
					
					cstm.execute();
					rootCtrl.addAuction();
				}
				else {
					notRegistered.setVisible(true);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (cstm != null)
					cstm.close();
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean isNumeric(String str){
	    try {
	      double d = Double.parseDouble(str);
	    } catch(NumberFormatException nfe) {
	      return true;
	    }
	    return false;
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




























