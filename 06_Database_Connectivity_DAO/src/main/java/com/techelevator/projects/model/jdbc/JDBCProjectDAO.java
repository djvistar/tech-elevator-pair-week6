package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Project> getAllActiveProjects() {

		ArrayList<Project> projects = new ArrayList<>();
		String sqlgetAllActiveProjects = "SELECT project_id, name, from_date, to_date"+
										   "FROM project ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlgetAllActiveProjects);
		while(results.next()) {
			Project theProject = mapRowToProject(results);
			projects.add(theProject);
		}
		return projects;
	}
		
		

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		String sql = "UPDATE employee set project_id = null"+
				"JOIN project_employee ON employee.employee_id = project_employee.employee_id"+
				"WHERE employee_id =?";
jdbcTemplate.update("UPDATE employee set project_id WHERE employee_id = ?", employeeId, projectId); 
		
			
	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		String sql = "UPDATE employee set project_id = ?"+
				"JOIN project_employee ON employee.employee_id = project_employee.employee_id"+
				"WHERE employee_id =?";
jdbcTemplate.update("UPDATE employee set project_id WHERE employee_id = ?", employeeId, projectId); 
	}

	private Project mapRowToProject(SqlRowSet results) {
		Project theProject;
		theProject = new Project();
		return theProject;
	}
	
	
}
