package product.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.controller.MainController;
import product.domain.Product;

public class ProductDao {

	// 상품 리스트 가져오기
	public ArrayList<Product> productList() {

		ArrayList<Product> products = new ArrayList<Product>();

		Statement stmt = null;
		ResultSet rs = null;

		try{
			stmt = MainController.getDbController().getConnection().createStatement();
			String sql = "select * from shoppingMall_Product";
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				Product product = new Product();
				product.setProductNumber(rs.getInt("productNumber"));
				product.setProductName(rs.getString("productName"));
				product.setProductPrice(rs.getInt("productPrice"));
				product.setProductComment(rs.getString("productComment"));
				product.setProductVendor(rs.getString("productVendor"));
				products.add(product);

			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			MainController.getDbController().close(rs);
			MainController.getDbController().close(stmt);
		}

		return products;
	}


	// 상품 등록
	public boolean insertProduct(Product newProduct) {

		// 만약 상품이름이 동일하다면 등록되지 않는다.
		// 상품번호는 1씩 증가한다

		boolean success = false;

		Statement stmt = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int maxProductNumber = 0;

		try {

			String sql = "select * from SHOPPINGMALL_PRODUCT where productName = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setString(1, newProduct.getProductName());
			rs = pstmt.executeQuery();

			if(rs.next()){

				return success;

			} else {

				// 같은 상품이름이 존재하지 않으면
				sql = "select max(productNumber)+1 as maxProductNumber from SHOPPINGMALL_PRODUCT";
				stmt = MainController.getDbController().getConnection().createStatement();
				rs2 = stmt.executeQuery(sql);

				if(rs2.next()){

					maxProductNumber = rs2.getInt(1);

					if(rs2.wasNull()){
						maxProductNumber = 1;
					}
				}

				newProduct.setProductNumber(maxProductNumber);

				sql = "insert into SHOPPINGMALL_PRODUCT values(?,?,?,?,?)";
				pstmt2 = MainController.getDbController().getConnection().prepareStatement(sql);
				pstmt2.setInt(1, newProduct.getProductNumber());
				pstmt2.setString(2, newProduct.getProductName());
				pstmt2.setInt(3, newProduct.getProductPrice());
				pstmt2.setString(4, newProduct.getProductComment());
				pstmt2.setString(5, newProduct.getProductVendor());
				pstmt2.executeUpdate();
				success = true;

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			if(pstmt2 != null){MainController.getDbController().close(pstmt2);}
			if(rs2 != null){MainController.getDbController().close(rs2);}
			if(stmt != null){MainController.getDbController().close(stmt);}
			if(rs != null){MainController.getDbController().close(rs);}
			if(pstmt != null){MainController.getDbController().close(pstmt);}
			
		}

		return success;
	}


	// 상품 수정
	public boolean updateProduct(int selectedNum, Product updateProduct) {


		return false;
	}


	// 상품 삭제
	public boolean deleteProduct(int deleteProductNumber) {


		return false;
	}

}
