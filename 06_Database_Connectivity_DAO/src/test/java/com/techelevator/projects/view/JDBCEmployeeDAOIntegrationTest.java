package com.techelevator.projects.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;

public class JDBCEmployeeDAOIntegrationTest {

	private static final Long TEST_EMPLOYEEID= (long) 99;
	private static final Long TEST_DEPARTMENTID= (long) 89;
	
	private static SingleConnectionDataSource dataSource;
	private JDBCEmployeeDAO dao;
	
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects.sql");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		dataSource.setAutoCommit(false);
	}
	
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	
	

	@Before
	public void setup() {
		String sqlInsertEmployee = "INSERT INTO employee (employee_id, department_id, first_name, last_name, birth_date, gender) VALUES (?, 2, 'Franklin', 'Trumbauer',	'1980-07-14', 'M')"; //removed from and to date due to localDate format issue
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertEmployee, TEST_EMPLOYEEID);
		dao = new JDBCEmployeeDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	
	
	@Test
	public void returns_list_of_all_employees() {
		Employee theEmployee = getEmployee(TEST_EMPLOYEEID,45, "Jake", "Dooee", 'M');
		List<Employee> results = dao.getAllEmployees();
		
		assertNotNull(results);
		assertEquals(1, results.size());
		Employee savedEmployee=results.get(0);
		assertEmployeesAreEqual(theEmployee, savedEmployee);
	}

	
	@Test
	public void returns_employees_by_name() {
		String testFirstName = "King";
		String testLastName = "Kong";
		Employee theEmployee = getEmployee(TEST_EMPLOYEEID,45, testFirstName, testLastName, 'M');
		//dao.save(theEmployee);

		List<Employee> results = dao.searchEmployeesByName(testFirstName, testLastName);

		assertNotNull(results);
		assertEquals(1, results.size());
		Employee savedEmployee=results.get(0);
		assertEmployeesAreEqual(theEmployee, savedEmployee);
	}
	
	@Test
	public void returns_employees_by_department_id() {
		Long testDepartmentId = (long) 50;
		Employee theEmployee = getEmployee(TEST_EMPLOYEEID,testDepartmentId, "King", "Kong", 'M');

		List<Employee> results = dao.getEmployeesByDepartmentId(testDepartmentId);

		assertNotNull(results);
		assertEquals(1, results.size());
		Employee savedEmployee=results.get(0);
		assertEmployeesAreEqual(theEmployee, savedEmployee);
	}
	
	
	
	@Test
	public void returns_employees_without_projects() {
		Long testProjectId = null;
		Employee theEmployee = getEmployee(TEST_EMPLOYEEID,45 , "King", "Kong", 'M');

		List<Employee> results = dao.getEmployeesByProjectId(testProjectId);

		assertNotNull(results);
		assertEquals(1, results.size());
		Employee savedEmployee=results.get(0);
		assertEmployeesAreEqual(theEmployee, savedEmployee);
	}
	
	
	@Test
	public void returns_employees_by_project_id() {
		Long testProjectId = (long) 50;
		Employee theEmployee = getEmployee(TEST_EMPLOYEEID,45 , "King", "Kong", 'M');

		List<Employee> results = dao.getEmployeesByProjectId(testProjectId);

		assertNotNull(results);
		assertEquals(1, results.size());
		Employee savedEmployee=results.get(0);
		assertEmployeesAreEqual(theEmployee, savedEmployee);
	}
	
	
	
	@Test
	public void changes_employee_department() throws SQLException {
		Employee theEmployee = getEmployee(TEST_EMPLOYEEID,45 , "King", "Kong", 'M');
	
		dao.changeEmployeeDepartment(TEST_EMPLOYEEID, TEST_DEPARTMENTID);
		Assert.assertEquals(TEST_DEPARTMENTID, TEST_EMPLOYEEID);
		assertNotEquals(null,theEmployee.getId());
		
	}
	
	

	private Employee getEmployee(Long employee_id, long department_id, String first_name, String last_name,
			char gender) {
		Employee theEmployee = new Employee();
				
		theEmployee.setId(employee_id);
		theEmployee.setDepartmentId(department_id);
		theEmployee.setFirstName(first_name);
		theEmployee.setLastName(last_name);
		theEmployee.setGender(gender);

		return theEmployee;
	}
	
	
	private void assertEmployeesAreEqual(Employee expected, Employee actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getDepartmentId(), actual.getDepartmentId());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
		assertEquals(expected.getGender(), actual.getGender());
	}
	
	
}
