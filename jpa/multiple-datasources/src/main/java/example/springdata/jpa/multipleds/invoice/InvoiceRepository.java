/*
 * Copyright 2015 the original author or authors.
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
package example.springdata.jpa.multipleds.invoice;

import example.springdata.jpa.multipleds.customer.Customer;
import example.springdata.jpa.multipleds.customer.Customer.CustomerId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Spring Data repository managing {@link Invoice}s.
 *
 * @author Oliver Gierke
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

	/**
	 * Returns all {@link Invoice}s for the {@link Customer} with the given identifier.
	 *
	 * @param id must not be {@literal null}.
	 * @return
	 */
	List<Invoice> findByCustomer(CustomerId id);
}
