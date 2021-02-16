package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		
		ArrayList<Department> departments = new ArrayList<>();
		String sqlsearchDepartmentsByName = "SELECT id, name "+
										   "FROM department ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlsearchDepartmentsByName);
		while(results.next()) {
			Department theDepartment = mapRowToDepartment(results);
			departments.add(theDepartment);
		}
		return departments;
	}
		
		
		
	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		
		ArrayList<Department> departments = new ArrayList<>();
		String sqlsearchDepartmentsByName = "SELECT id, name "+
										   "FROM department "+
										   "WHERE name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlsearchDepartmentsByName, nameSearch);
		while(results.next()) {
			Department theDepartment = mapRowToDepartment(results);
			departments.add(theDepartment);
		}
		return departments;
	}
		
	

	@Override
	public void saveDepartment(Department updatedDepartment) {
		
		String sqlInsertDepartment = "INSERT INTO department(id, name) " +
				   "VALUES(?, ?)";
		updatedDepartment.setId(getNextDepartmentId());
     jdbcTemplate.update(sqlInsertDepartment, updatedDepartment.getId(),
		updatedDepartment.getName());
		
	}

	@Override
	public Department createDepartment(Department newDepartment) {
		
		String sql = "INSERT INTO department set name = ? WHERE id = ?";
		jdbcTemplate.update(sql,newDepartment.getName(), newDepartment.getId());
		return null;
	}
	

	@Override
	public Department getDepartmentById(Long id) {
		
	Department theDepartment = null;
		String sqlFindDeptById = "SELECT id, name"+
							   "FROM department "+
							   "WHERE id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindDeptById, id);
		if(results.next()) {
			theDepartment = mapRowToDepartment(results);
		}
		return theDepartment;
	}
	
	public void delete(long id) {
		jdbcTemplate.update("DELETE FROM department WHERE id = ?",id);
	}

	private long getNextDepartmentId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_department_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new department");
		}
	}
	
	
	
	
	private Department mapRowToDepartment(SqlRowSet results) {
		Department theDepartment;
		theDepartment = new Department();
		return theDepartment;
	}
}
