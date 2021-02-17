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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.jdbc.JDBCProjectDAO;

public class JDBCProjectDAOIntegrationTest {

	private static final Long TEST_PROJECTID= (long) 99;
	private static final Long TEST_EMPLOYEEID= (long) 999;
	
	private static SingleConnectionDataSource dataSource;
	private JDBCProjectDAO dao;
	
	
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
		String sqlInsertProject = "INSERT INTO project (project_id, name) VALUES (?, 'Project X')"; //removed from and to date due to localDate format issue
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertProject, TEST_PROJECTID);
		dao = new JDBCProjectDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Test
	public void returns_list_of_all_projects() {
		Project theProject = getProject(TEST_PROJECTID, "Fake Project");
		List<Project> results = dao.getAllActiveProjects();
		assertNotNull(results);
		assertEquals(1, results.size());
		Project savedProject=results.get(0);
		assertProjectsAreEqual(theProject, savedProject);
	}
	
	@Test
	public void adds_employee_to_project() {
		Project theProject = getProject(TEST_PROJECTID, "Fake Project");
		dao.addEmployeeToProject(TEST_PROJECTID, (long) 11);
		Assert.assertEquals((long)3,(long)5); // always true as its original data
		Assert.assertEquals((long)99,(long)11);//this is our input data being tested
	}
	
	
	@Test
	public void removes_employee_from_project() {
		Project theProject = getProject(TEST_PROJECTID, "Fake Project");
		dao.addEmployeeToProject(TEST_PROJECTID, TEST_EMPLOYEEID);
		dao.addEmployeeToProject(TEST_PROJECTID, (long) 1);
		dao.removeEmployeeFromProject(TEST_PROJECTID, (long) 1);
		Assert.assertEquals(TEST_PROJECTID, TEST_EMPLOYEEID);
		assertNotNull(TEST_EMPLOYEEID);
		
		
	}
	
	
	
	private Project getProject(Long project_id, String name) {
		Project theProject = new Project();
		
		theProject.setId(project_id);
		theProject.setName(name);

		return theProject;
	}
	
	private void assertProjectsAreEqual(Project expected, Project actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		
	}
	
	
}

//	@Test
//	public void save_new_city_and_read_it_back() throws SQLException {
//		City theCity = getCity("SQL Station", "South Dakota", "USA", 65535);
//
//		dao.save(theCity);
//		City savedCity = dao.findCityById(theCity.getId());
//
//		assertNotEquals(null, theCity.getId());
//		assertCitiesAreEqual(theCity, savedCity);
//	}
//
//	@Test
//	public void returns_cities_by_country_code() {
//		City theCity = getCity("SQL Station", "South Dakota", TEST_COUNTRY, 65535);
//
//		dao.save(theCity);
//		List<City> results = dao.findCityByCountryCode(TEST_COUNTRY);
//
//		assertNotNull(results);
//		assertEquals(1, results.size());
//		City savedCity = results.get(0);
//		assertCitiesAreEqual(theCity, savedCity);
//	}
//
//	@Test
//	public void returns_multiple_cities_by_country_code() {
//
//		dao.save(getCity("SQL Station", "South Dakota", TEST_COUNTRY, 65535));
//		dao.save(getCity("Postgres Point", "North Dakota", TEST_COUNTRY, 65535));
//
//		List<City> results = dao.findCityByCountryCode(TEST_COUNTRY);
//
//		assertNotNull(results);
//		assertEquals(2, results.size());
//	}
//
//	@Test
//	public void returns_cities_by_district() {
//		String testDistrict = "Tech Elevator";
//		City theCity = getCity("SQL Station", testDistrict, TEST_COUNTRY, 65535);
//		dao.save(theCity);
//
//		List<City> results = dao.findCityByDistrict(testDistrict);
//
//		assertNotNull(results);
//		assertEquals(1, results.size());
//		City savedCity = results.get(0);
//		assertCitiesAreEqual(theCity, savedCity);
//	}
//
//	private City getCity(String name, String district, String countryCode, int population) {
//		City theCity = new City();
//		theCity.setName(name);
//		theCity.setDistrict(district);
//		theCity.setCountryCode(countryCode);
//		theCity.setPopulation(population);
//		return theCity;
//	}
//
//	private void assertCitiesAreEqual(City expected, City actual) {
//		assertEquals(expected.getId(), actual.getId());
//		assertEquals(expected.getName(), actual.getName());
//		assertEquals(expected.getDistrict(), actual.getDistrict());
//		assertEquals(expected.getCountryCode(), actual.getCountryCode());
//		assertEquals(expected.getPopulation(), actual.getPopulation());
//	}
//}
//
//}
