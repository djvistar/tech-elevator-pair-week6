package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		ArrayList<Employee> employees = new ArrayList<>();
		String sqlsearchEmployeesByName = "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date "+
										   "FROM employee ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlsearchEmployeesByName);
		while(results.next()) {
			Employee theEmployee = mapRowToEmployee(results);
			employees.add(theEmployee);
		}
		return employees;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {

		ArrayList<Employee> employees = new ArrayList<>();
		String sqlsearchEmployeesByName= "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date "+
											"FROM employee "+
										   "WHERE first_name = ? AND last_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlsearchEmployeesByName, firstNameSearch, lastNameSearch);
		while(results.next()) {
			Employee theEmployee = mapRowToEmployee(results);
			employees.add(theEmployee);
		}
		return employees;
	}
	

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {
		ArrayList<Employee> employees = new ArrayList<>();
		String sqlGetEmployeesByDepartmentId= "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date "+
											"FROM employee "+
										   "WHERE department_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployeesByDepartmentId, id);
		while(results.next()) {
			Employee theEmployee = mapRowToEmployee(results);
			employees.add(theEmployee);
		}
		return employees;
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		ArrayList<Employee> employees = new ArrayList<>();
		String sqlGetEmployeesWithoutProjects= "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date "+
											"FROM employee "+
											"JOIN project_employee ON employee.employee_id = project_employee.employee_id"+
										    "WHERE project_id = null ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployeesWithoutProjects);
		while(results.next()) {
			Employee theEmployee = mapRowToEmployee(results);
			employees.add(theEmployee);
		}
		return employees;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		ArrayList<Employee> employees = new ArrayList<>();
		String sqlgetEmployeesByProjectId= "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date "+
											"FROM employee "+
											"JOIN project_employee ON employee.employee_id = project_employee.employee_id"+
										    "WHERE project_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlgetEmployeesByProjectId, projectId);
		while(results.next()) {
			Employee theEmployee = mapRowToEmployee(results);
			employees.add(theEmployee);
		}
		return employees;
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		String sql = "UPDATE employee set department_id = ?"+
						"WHERE employee_id =?";
		jdbcTemplate.update("UPDATE employee set department_id WHERE employee_id = ?", employeeId, departmentId); 
	}

	
	
	
	private Employee mapRowToEmployee(SqlRowSet results) {
		Employee theEmployee;
		theEmployee = new Employee();
		return theEmployee;
	}
	
	public void delete(long id) {
		jdbcTemplate.update("DELETE FROM employee WHERE employee_id = ?",id);
	}

}
