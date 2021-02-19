package com.techelevator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.model.domain.Category;

public class MyDAOTest extends DAOIntegrationTest {
	
	
	// RENAME, REUSE, OR DELETE ME... THIS IS A PLACEHOLDER ONLY SHOWING HOW YOU CAN 
	// EXTEND DAOINTEGRATIONTEST AND INHERIT ALL THE @BEFORE/@AFTER METHODS

	
	@Test
	public void returnCategoryListByVenue(){
		//need to add dummy data

		List<Category>allCategoriesFromVenue = new ArrayList<Category>();
	Assert.assertNotNull(allCategoriesFromVenue);
	Assert.assertEquals(22,allCategoriesFromVenue.size());
	}
	
	@Test
	public void testSomethingElse() {
		
	}

}
