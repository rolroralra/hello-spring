package sample.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import lombok.Setter;
import sample.dao.ProductDAO;
import sample.vo.OwnerVO;
import sample.vo.PetVO;
import sample.vo.ProductVO;

@Repository
@Setter
public class NPJdbcTemplateProductDAO implements ProductDAO {
	@Autowired
	NamedParameterJdbcTemplate npJdbcTemplate;
	
	@Override
	public ProductVO selectProduct(String name, int price) {
		return new ProductVO(name, price);
	}

	@Override
	public int selectCount(String tableName) {
		return npJdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM :TABLE_NAME", 
				new MapSqlParameterSource("TABLE_NAME", tableName),
				Integer.class
		); 
		
	}
	
	@Override
	public int selectCountOfPet(String ownerName) {
		return npJdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM PET WHERE OWNER_NAME = :OWNER_NAME", 
				new MapSqlParameterSource("OWNER_NAME", ownerName),
				Integer.class
		);
	}
	
	@Override
	public Date selectBirthDateOfPet(String petName) {
		return npJdbcTemplate.queryForObject(
				"SELECT BIRTH_DATE FROM PET WHERE PET_NAME = :PET_NAME", 
				new MapSqlParameterSource("PET_NAME", petName),
				Date.class
		);
	}
	
	@Override
	public Map<String, Object> selectPet(String petName) {
		return npJdbcTemplate.queryForMap(
				"SELECT * FROM PET WHERE PET_NAME = :PET_NAME", 
				new MapSqlParameterSource().addValue("PET_NAME", petName)
		);
	}
	
	@Override
	public PetVO selectPetVo(String petName) {
		// Way1. RowMapper<T> mapRow(ResultSet rs, int rowNum) Method By Anonymous Class
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
		
		// Way2. RowMapper<T> By Lambda Expression
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
		return npJdbcTemplate.queryForObject(
				"SELECT * FROM PET WHERE PET_NAME = :PET_NAME", 
				new MapSqlParameterSource().addValue("PET_NAME", petName),
				new BeanPropertyRowMapper<PetVO>(PetVO.class)
		);
	}
	
	@Override
	public List<Map<String, Object>> selectAllPetOfOwner(String ownerName) {
		return npJdbcTemplate.queryForList(
				"SELECT * FROM PET WHERE OWNER_NAME = :OWNER_NAME", 
				new MapSqlParameterSource().addValue("OWNER_NAME", ownerName, java.sql.Types.VARCHAR, ownerName.getClass().getTypeName())
		);
	}
	
	/* ResultSetExtractor<T> - T extractData(ResultSet rs) Method */
	@Override
	public OwnerVO selectOwnerWithPetList(String ownerName) {
		return npJdbcTemplate.query(
				"SELECT * FROM PET WHERE OWNER_NAME = :OWNER_NAME",
				new MapSqlParameterSource().addValue("OWNER_NAME", ownerName),
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
		npJdbcTemplate.update(
				"INSERT INTO OWNER(OWNER_NAME) VALUES(:OWNER_NAME)", 
				new MapSqlParameterSource("OWNER_NAME", owner.getOwnerName())
		);
	}

	@Override
	public void insertPet(PetVO pet) {
		npJdbcTemplate.update(
				"INSERT INTO PET(PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE) VALUES(:petName, :owner.ownerName, :price, :birthDate)",
				new BeanPropertySqlParameterSource(pet));
	}

	@Override
	public int insertOwnerList(List<OwnerVO> ownerList) {
		SqlParameterSource[] batchArgs = new BeanPropertySqlParameterSource[ownerList.size()];
		int index = 0;
		for (OwnerVO owner : ownerList) {
			batchArgs[index++] = new BeanPropertySqlParameterSource(owner);
		}
		
		int[] batchResult = npJdbcTemplate.batchUpdate(
				"INSERT INTO OWNER(OWNER_NAME) VALUES(:ownerName)", 
				batchArgs
		);
		
		int totalResult = 0;
		for (int i : batchResult) {
			totalResult += i;
		}
		
		return totalResult;
	}

	@Override
	public int insertPetList(List<PetVO> petList) {
		SqlParameterSource[] batchArgs = new BeanPropertySqlParameterSource[petList.size()];
		int index = 0;
		for (PetVO pet : petList) {
			batchArgs[index++] = new BeanPropertySqlParameterSource(pet);
		}
		
		int[] batchResult = npJdbcTemplate.batchUpdate(
				"INSERT INTO PET(PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE) VALUES(:petName, :ownerName, :price, :birthDate)", 
				batchArgs
		);
		
		int totalResult = 0;
		for (int i : batchResult) {
			totalResult += i;
		}
		
		return totalResult;
	}

}
