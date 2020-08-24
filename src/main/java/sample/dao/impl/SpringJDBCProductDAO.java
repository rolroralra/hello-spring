package sample.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import lombok.Setter;
import sample.dao.ProductDAO;
import sample.vo.OwnerVO;
import sample.vo.PetVO;
import sample.vo.ProductVO;

@Repository
@Setter
public class SpringJDBCProductDAO implements ProductDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public ProductVO selectProduct(String name, int price) {
		return new ProductVO(name, price);
	}

	@Override
	public int selectCount(String tableName) {
		return jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM " + tableName, 
				Integer.class
		); 
		
	}
	
	public int selectCountOfPet(String ownerName) {
		return jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM PET WHERE OWNER_NAME = ?", 
				new Object[]{ownerName}, 
				Integer.class
		);
	}
	
	public Date selectBirthDateOfPet(String petName) {
		return jdbcTemplate.queryForObject(
				"SELECT BIRTH_DATE FROM PET WHERE PET_NAME = ?", 
				new Object[]{petName},
				Date.class
		);
	}
	
	public Map<String, Object> selectPet(String petName) {
		return jdbcTemplate.queryForMap("SELECT * FROM PET WHERE PET_NAME = ?", petName);
	}
	
	public PetVO selectPetVo(String petName) {
		/* Way1. RowMapper<T> mapRow(ResultSet rs, int rowNum) Method By Anonymous Class */
		/*return jdbcTemplate.queryForObject("SELECT * FROM PET WHERE PET_NAME = ?", new RowMapper<PetVO>() {

			@Override
			public PetVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PetVO resultObj = new PetVO();
				resultObj.setPetName(rs.getString("PET_NAME"));
				resultObj.setOwnerName(rs.getString("OWNER_NAME"));
				resultObj.setPrice(rs.getInt("PRICE"));
				resultObj.setBirthDate(rs.getDate("BIRTH_DATE"));
				return resultObj;
			}
		}, petName);*/
		/* Way2. RowMapper<T> By Lambda Expression */
		/*return jdbcTemplate.queryForObject("SELECT * FROM PET WHERE PET_NAME = ?", 
			(ResultSet rs, int rowNum) -> {
				PetVO resultObj = new PetVO();
				resultObj.setPetName(rs.getString("PET_NAME"));
				resultObj.setOwnerName(rs.getString("OWNER_NAME"));
				resultObj.setPrice(rs.getInt("PRICE"));
				resultObj.setBirthDate(rs.getDate("BIRTH_DATE"));
				return resultObj;
			}, petName);*/
		
		// Way3. BeanPropertyRowMapper<T>
		return jdbcTemplate.queryForObject("SELECT * FROM PET WHERE PET_NAME = ?", 
				new BeanPropertyRowMapper<PetVO>(PetVO.class), petName);
	}
	
	public List<Map<String, Object>> selectAllPetOfOwner(String ownerName) {
		return jdbcTemplate.queryForList("SELECT * FROM PET WHERE OWNER_NAME = ?", ownerName);
	}
	
	/* ResultSetExtractor<T> - T extractData(ResultSet rs) Method */
	public OwnerVO selectOwnerWithPetList(String ownerName) {
		return jdbcTemplate.query(
				"SELECT * FROM PET WHERE OWNER_NAME = ?", 
				new Object[]{ ownerName }, 
				new ResultSetExtractor<OwnerVO>() {
					@Override
					public OwnerVO extractData(ResultSet rs) throws SQLException, DataAccessException {
						if (!rs.next()) {
							return null;
						}
						
						OwnerVO owner = new OwnerVO();
						owner.setOwnerName(rs.getString("OWNER_NAME"));
						do {
							PetVO pet = new PetVO();
							pet.setPetName(rs.getString("PET_NAME"));
							pet.setOwnerName(rs.getString("OWNER_NAME"));
							pet.setPrice(rs.getInt("PRICE"));
							pet.setBirthDate(rs.getDate("BIRTH_DATE"));
							
							owner.getPetList().add(pet);
						} while (rs.next());
						
						return owner;
					}
				}
		);
	}

	@Override
	public void insertOwner(OwnerVO owner) {
		jdbcTemplate.update("INSERT INTO OWNER(OWNER_NAME) VALUES(?)", owner.getOwnerName());
	}

	@Override
	public void insertPet(PetVO pet) {
		jdbcTemplate.update(
				"INSERT INTO PET(PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE) VALUES(?, ?, ?, ?)", 
				pet.getPetName(),
				pet.getOwnerName(),
				pet.getPrice(),
				pet.getBirthDate()
		);
	}

	@Override
	public int insertOwnerList(List<OwnerVO> ownerList) {
		int[] ownerBatchResults = jdbcTemplate.batchUpdate(
				"INSERT INTO OWNER(OWNER_NAME) VALUES(?)", 
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, ownerList.get(i).getOwnerName());
					}
					
					@Override
					public int getBatchSize() {
						return ownerList.size();
					}
				}
		);
		
		int totalResult = 0;
		
		for (int ownerBatchResult : ownerBatchResults) {
			totalResult += ownerBatchResult;
		}
		
		return totalResult;
	}

	@Override
	public int insertPetList(List<PetVO> petList) {
		int[] petBatchResults = jdbcTemplate.batchUpdate(
				"INSERT INTO PET(PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE) VALUES(?, ?, ?, ?)", 
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, petList.get(i).getPetName());
						ps.setString(2, petList.get(i).getOwnerName());
						ps.setInt(3, petList.get(i).getPrice());
						ps.setDate(4, new Date(petList.get(i).getBirthDate().getTime()));
					}
					
					@Override
					public int getBatchSize() {
						return petList.size();
					}
				}
		);
		
		int totalResult = 0;
		
		for (int ownerBatchResult : petBatchResults) {
			totalResult += ownerBatchResult;
		}
		
		return totalResult;
	}

}
