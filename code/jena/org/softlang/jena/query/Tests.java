package org.softlang.jena.query;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.softlang.company.CompanyModel;

import com.hp.hpl.jena.rdf.model.Resource;

public class Tests {

	public final String filename = "sampleCompany.rdf";
	public CompanyModel sampleCompany = new CompanyModel();

	@Before
	public void setUp() throws FileNotFoundException {
		sampleCompany.getModel().read(new FileInputStream(filename), null);
	}

	@Test
	public void testTotal() throws SQLException {
		double preCutTotal = Total.total(sampleCompany);
		assertEquals(399747, preCutTotal, 0.0);
	}

	@Test
	public void testPrecedence() {
		assertTrue(Precedence.checkPrecedence(sampleCompany));
		// set erik's salary equal to his manager's salary
		sampleCompany.getModel()
				.getResource(sampleCompany.NS_COMPANY + "erik")
					.removeAll(sampleCompany.SALARY)
					.addLiteral(sampleCompany.SALARY, 123456.0);
		assertFalse(Precedence.checkPrecedence(sampleCompany));
	}

	@Test
	public void testContainment() {
		assertTrue(Containment.checkContainment(sampleCompany));
		// add the manager of the research department also to the development
		// department (as employee)
		Resource craig = sampleCompany.getModel()
				.getResource(sampleCompany.NS_COMPANY + "craig");
		sampleCompany.getModel()
				.getResource(sampleCompany.NS_COMPANY + "development")
					.getProperty(sampleCompany.EMPLOYEES)
						.getBag()
							.add(craig);
		
		assertFalse(Containment.checkContainment(sampleCompany));
	}

}
