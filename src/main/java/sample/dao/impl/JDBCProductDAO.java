package sample.dao.impl;
import static java.sql.Connection.TRANSACTION_SERIALIZABLE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.Setter;
import sample.dao.ProductDAO;
import sample.vo.OwnerVO;
import sample.vo.PetVO;
import sample.vo.ProductVO;

@Repository
@Setter
public class JDBCProductDAO implements ProductDAO {
	@Autowired
	DataSource dataSource;
	
	@Override
	public ProductVO selectProduct(String name, int price) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			conn.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
			pstm = conn.prepareStatement("SELECT COUNT(*) FROM ?");
			pstm.setString(1, "PET");
			rs = pstm.executeQuery();
			rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return new ProductVO("TEST", 1000);
	}
	
	public void insertOwner(OwnerVO owner) {
		Connection conn = null;
		PreparedStatement pstm = null;
//		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			conn.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("INSERT INTO OWNER VALUES(?)");
			pstm.setString(1, owner.getOwnerName());
			pstm.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void insertPet(PetVO pet) {
		Connection conn = null;
		PreparedStatement pstm = null;
//		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			conn.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("INSERT INTO PET VALUES(?, ?, ?, ?)");
			pstm.setString(1, pet.getPetName());
			pstm.setString(2, pet.getOwnerName());
			pstm.setInt(3, pet.getPrice());
			pstm.setDate(4, new Date(pet.getBirthDate().getTime()));
			pstm.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int selectCount(String tableName) {
		Connection conn = null;
//		PreparedStatement pstm = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			conn.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
//			pstm = conn.prepareStatement("SELECT COUNT(*) FROM " + tableName);
			st = conn.createStatement();
//			pstm.setString(1, tableName);
//			rs = pstm.executeQuery();
			rs = st.executeQuery("SELECT COUNT(*) FROM " + tableName);
			if (rs.next()) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public OwnerVO selectOwnerWithPetList(String ownerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectAllPetOfOwner(String ownerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PetVO selectPetVo(String petName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectPet(String petName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date selectBirthDateOfPet(String petName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCountOfPet(String ownerName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOwnerList(List<OwnerVO> ownerList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertPetList(List<PetVO> petList) {
		// TODO Auto-generated method stub
		return 0;
	}
}
