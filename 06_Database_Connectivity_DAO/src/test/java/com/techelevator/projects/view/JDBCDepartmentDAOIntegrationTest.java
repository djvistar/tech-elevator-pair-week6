package com.techelevator.projects.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


import com.techelevator.projects.model.Department;

import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;


public class JDBCDepartmentDAOIntegrationTest {

	private static final Long TEST_DEPARTMENTID= (long) 99;
	
	private static SingleConnectionDataSource dataSource;
	private JDBCDepartmentDAO dao;
	
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
		String sqlInsertDepartment = "INSERT INTO project (department_id, name) VALUES (?, 'Network Administration')"; //removed from and to date due to localDate format issue
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertDepartment, TEST_DEPARTMENTID);
		dao = new JDBCDepartmentDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	
	
	@Test
	public void returns_list_of_all_departments() {
		Department theDepartment = getDepartment(TEST_DEPARTMENTID, "Fake Department");
		List<Department> results = dao.getAllDepartments();
		assertNotNull(results);
		assertEquals(1, results.size());
		Department savedDepartment=results.get(0);
		assertDepartmentsAreEqual(theDepartment, savedDepartment);
	}
	
	
	
	@Test
	public void returns_departments_by_name() {
		String testName = "Tech Elevator";
		Department theDepartment = getDepartment(TEST_DEPARTMENTID, testName);
		dao.saveDepartment(theDepartment);

		List<Department> results = dao.searchDepartmentsByName(testName);

		assertNotNull(results);
		assertEquals(1, results.size());
		Department savedDepartment=results.get(0);
		assertDepartmentsAreEqual(theDepartment, savedDepartment);
	}

	
	
	@Test
	public void creates_and_saves_department() throws SQLException {
		Department theDepartment = getDepartment((long)50, "SQL Station");

		dao.saveDepartment(theDepartment);
		Department savedDepartment = dao.getDepartmentById(theDepartment.getId());

		assertNotEquals(null,theDepartment .getId());
		assertDepartmentsAreEqual(theDepartment , savedDepartment);
	}
	
	
	@Test
	public void returns_department_by_ID() {
		Department theDepartment = getDepartment(TEST_DEPARTMENTID, "name");

		dao.saveDepartment(theDepartment);
		Department savedDepartment = dao.getDepartmentById(theDepartment.getId());

		
		assertDepartmentsAreEqual(theDepartment, savedDepartment);
	}

	
	
	
	
	
	
	
	private Department getDepartment(Long department_id, String name) {
		Department theDepartment = new Department();
		
		theDepartment.setId(department_id);
		theDepartment.setName(name);

		return theDepartment;
	}
	
	private void assertDepartmentsAreEqual(Department expected, Department actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		
	}
	
	
	
}
