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

import example.springdata.jpa.multipleds.customer.Customer.CustomerId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple domain class representing an {@link Invoice}
 * 
 * @author Oliver Gierke
 */
@Entity
@EqualsAndHashCode(of = "id")
@Getter
@RequiredArgsConstructor
@ToString
// Needs to be explicitly named as Invoice is a reserved keyword
@Table(name = "SampleINvoice")
public class Invoice {

	private @Id @GeneratedValue Long id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//
	private final List<LineItemInvoice> lineItemInvoices = new ArrayList<LineItemInvoice>();
	private final CustomerId customer;

	Invoice() {
		this.customer = null;
	}

	/**
	 * Adds a {@link LineItemInvoice} to the {@link Invoice}.
	 * 
	 * @param lineItemInvoice must not be {@literal null}.
	 */
	public void add(LineItemInvoice lineItemInvoice) {

		Assert.notNull(lineItemInvoice, "Line item must not be null!");

		this.lineItemInvoices.add(lineItemInvoice);
	}

	@Entity
	@EqualsAndHashCode(of = "id")
	@Getter
	@RequiredArgsConstructor
	@ToString
	@Table(name = "LineItemInvoice")
	public static class LineItemInvoice {

		private @Id @GeneratedValue Long id;
		private final String description;
	}
}
