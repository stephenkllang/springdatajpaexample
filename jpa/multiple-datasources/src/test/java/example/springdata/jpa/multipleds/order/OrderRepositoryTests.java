/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.jpa.multipleds.order;

import example.springdata.jpa.multipleds.customer.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Integration test for {@link CustomerRepository}.
 * 
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "orderTransactionManager")
public class OrderRepositoryTests {

	@Autowired OrderRepository orders;
	@Autowired CustomerRepository customers;

	@Test
	public void persistsAndFindsCustomer() {

		customers.findAll().forEach(customer -> {
			assertThat(orders.findByCustomer(customer.getId()), hasSize(1));
		});
	}
}
