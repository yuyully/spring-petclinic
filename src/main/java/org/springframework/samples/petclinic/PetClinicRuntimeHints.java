/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.jspecify.annotations.Nullable;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.vet.Vet;

/**
 * Registers runtime hints needed by the PetClinic application for AOT/native-image usage.
 * <p>
 * This class implements {@link RuntimeHintsRegistrar} to provide the Spring AOT engine with
 * information about resources and types that need to be available at runtime when the
 * application is compiled to a native image using GraalVM.
 * <p>
 * The registered hints ensure that:
 * <ul>
 * <li>Database initialization scripts and configuration files are accessible</li>
 * <li>Internationalization message bundles can be loaded</li>
 * <li>Domain model entities can be serialized for caching and other purposes</li>
 * </ul>
 *
 * @author Spring PetClinic Team
 * @see RuntimeHintsRegistrar
 * @see RuntimeHints
 */
public class PetClinicRuntimeHints implements RuntimeHintsRegistrar {

	/**
	 * Contributes resource and serialization hints required at runtime.
	 * <p>
	 * Resource patterns are registered to make non-code assets visible in the native image:
	 * <ul>
	 * <li>{@code db/*} - Database schema and data initialization scripts for HSQLDB and MySQL</li>
	 * <li>{@code messages/*} - Internationalization message property files</li>
	 * <li>{@code mysql-default-conf} - MySQL configuration file</li>
	 * </ul>
	 * <p>
	 * Serialization types are registered to ensure reflection metadata is available for:
	 * <ul>
	 * <li>{@link BaseEntity} - Base class for all persistent entities</li>
	 * <li>{@link Person} - Base class for person-related entities</li>
	 * <li>{@link Vet} - Veterinarian entity</li>
	 * </ul>
	 *
	 * @param hints the runtime hints registry to update
	 * @param classLoader the class loader to use, if available
	 */
	@Override
	public void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
		hints.resources().registerPattern("db/*"); // https://github.com/spring-projects/spring-boot/issues/32654
		hints.resources().registerPattern("messages/*");
		hints.resources().registerPattern("mysql-default-conf");
		hints.serialization().registerType(BaseEntity.class);
		hints.serialization().registerType(Person.class);
		hints.serialization().registerType(Vet.class);
	}

}
