package com.godfrey.customer.dal.repos;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.godfrey.customer.dal.entities.Customer;

/* For testing project uses h2 in-memory database configured in 
 * src/test/resources/application.properties
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository sut; 

	@Test
	public void testSaveCustomer() {
		Customer customer = createTestCustomer();
		customer = sut.save(customer);
		
		Customer actual = sut.findById(customer.getId()).get();
		assertNotNull(actual);
		assertEquals("John", actual.getName());
		assertEquals("john@email.com", actual.getEmail());
	}
	
	@Test
	public void testFindCustomerById() {
		sut.save(createTestCustomer());
		
		Customer actual = sut.findById(1l).get();
		
		assertNotNull(actual);
		assertEquals("John", actual.getName());
		assertEquals("john@email.com", actual.getEmail());
	}
	
	@Test
	public void testUpdateCustomer() {
		sut.save(createTestCustomer());
		Customer customer = sut.findById(1l).get();
		
		customer.setEmail("fred@email.com");
		sut.save(customer);
		
		Customer actual = sut.findById(1l).get();
		assertEquals("John", actual.getName());
		assertEquals("fred@email.com", actual.getEmail());
	}
	
	@Test
	public void testDeleteCustomer() {
		Customer customer = sut.save(createTestCustomer());
		assertNotNull(sut.findById(customer.getId()).get());
		
		assertTrue(sut.existsById(customer.getId()));
		sut.delete(customer);
		assertFalse(sut.existsById(customer.getId()));

	}
	
	private Customer createTestCustomer() {
		Customer customer = new Customer(); 
		customer.setName("John");
		customer.setEmail("john@email.com");
		return customer;
	}

}
