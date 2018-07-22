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
package com.greglturnquist.payroll;

import static com.greglturnquist.payroll.WebSocketConfiguration.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import javax.json.Json;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component
@RepositoryEventHandler(Employee.class)
public class EventHandler {

	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;

	private final String mockData = Json.createArrayBuilder()
		.add(Json.createObjectBuilder()
			.add("name", "Antivirus")
			.add("totalAssetCount", "12345")
			.add("threatAssetCount", "12345")
			.add("threatLevel", "0.2")
			.add("timestamp", "2018-06-01T01:01:00")
			.add("links", "[]"))
		.add(Json.createObjectBuilder()
			.add("name", "Malware")
			.add("totalAssetCount", "12345")
			.add("threatAssetCount", "12345")
			.add("threatLevel", "0.2")
			.add("timestamp", "2018-06-01T01:01:00")
			.add("links", "[]"))
		.add(Json.createObjectBuilder()
			.add("name", "Firewall")
			.add("totalAssetCount", "22345")
			.add("threatAssetCount", "12345")
			.add("threatLevel", "0.3")
			.add("timestamp", "2018-06-01T01:01:00")
			.add("links", "[]"))
		.build()
		.toString();

	@Autowired
	public EventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
	}

	@HandleAfterCreate
	public void newEmployee(Employee employee) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/newEmployee", mockData);
	}

	@HandleAfterDelete
	public void deleteEmployee(Employee employee) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/deleteEmployee", getPath(employee));
	}

	@HandleAfterSave
	public void updateEmployee(Employee employee) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/updateEmployee", getPath(employee));
	}

	/**
	 * Take an {@link Employee} and get the URI using Spring Data REST's {@link EntityLinks}.
	 *
	 * @param employee
	 */
	private String getPath(Employee employee) {
		return this.entityLinks.linkForSingleResource(employee.getClass(),
				employee.getId()).toUri().getPath();
	}

}
// end::code[]
