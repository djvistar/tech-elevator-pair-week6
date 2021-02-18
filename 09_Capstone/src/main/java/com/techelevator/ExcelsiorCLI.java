package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class ExcelsiorCLI {

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	public ExcelsiorCLI(DataSource datasource) {

	}

	public void run() {

		// Reminder: No System.out.printlns in this class

		// Availability check
//		 if userinput start date > reservation startdate && userinput start date <reservation end date
//		then not available
//		 next line
//		else if 
//				if userinput end date > reservation startdate && userinput end date <reservation end date
//		 then not available
//		next line
//		else if open month is null---available add to list
//		 else if requested date outside of open from and open to, not available, next line
//		else 
//		input into list as Available

	}
}
