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

package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * Unit tests for {@link Owner}.
 */
class OwnerTests {

	private Owner owner;

	@BeforeEach
	void setUp() {
		owner = new Owner();
	}

	@Nested
	class AddPet {

		@Test
		void newPetIsAddedToOwner() {
			Pet pet = new Pet();
			pet.setName("Buddy");

			owner.addPet(pet);

			assertThat(owner.getPets()).contains(pet);
		}

		@Test
		void existingPetIsNotAddedAgain() {
			Pet pet = new Pet();
			pet.setName("Buddy");
			pet.setId(1);

			owner.addPet(pet);

			assertThat(owner.getPets()).doesNotContain(pet);
		}

	}

	@Nested
	class GetPetByName {

		@Test
		void returnsPetWhenNameMatches() {
			Pet pet = new Pet();
			pet.setName("Buddy");
			owner.addPet(pet);

			Pet found = owner.getPet("Buddy");

			assertThat(found).isEqualTo(pet);
		}

		@Test
		void returnsNullWhenNoMatchingName() {
			Pet found = owner.getPet("Unknown");

			assertThat(found).isNull();
		}

		@Test
		void matchingIsCaseInsensitive() {
			Pet pet = new Pet();
			pet.setName("Buddy");
			owner.addPet(pet);

			Pet found = owner.getPet("buddy");

			assertThat(found).isEqualTo(pet);
		}

	}

	@Nested
	class GetPetByNameWithIgnoreNew {

		@Test
		void returnsNewPetWhenIgnoreNewIsFalse() {
			Pet pet = new Pet();
			pet.setName("Buddy");
			owner.addPet(pet);

			Pet found = owner.getPet("Buddy", false);

			assertThat(found).isEqualTo(pet);
		}

		@Test
		void returnsNullForNewPetWhenIgnoreNewIsTrue() {
			Pet pet = new Pet();
			pet.setName("Buddy");
			owner.addPet(pet);

			Pet found = owner.getPet("Buddy", true);

			assertThat(found).isNull();
		}

		@Test
		void returnsSavedPetWhenIgnoreNewIsTrue() {
			Pet pet = new Pet();
			pet.setName("Buddy");
			pet.setId(1);
			owner.getPets().add(pet);

			Pet found = owner.getPet("Buddy", true);

			assertThat(found).isEqualTo(pet);
		}

	}

	@Nested
	class GetPetById {

		@Test
		void returnsPetWhenIdMatches() {
			Pet pet = new Pet();
			pet.setName("Buddy");
			pet.setId(42);
			owner.getPets().add(pet);

			Pet found = owner.getPet(42);

			assertThat(found).isEqualTo(pet);
		}

		@Test
		void returnsNullWhenIdDoesNotMatch() {
			Pet pet = new Pet();
			pet.setName("Buddy");
			pet.setId(42);
			owner.getPets().add(pet);

			Pet found = owner.getPet(99);

			assertThat(found).isNull();
		}

		@Test
		void returnsNullForNewPetWithNoId() {
			Pet pet = new Pet();
			pet.setName("Buddy");
			owner.addPet(pet);

			Pet found = owner.getPet(1);

			assertThat(found).isNull();
		}

	}

	@Nested
	class AddVisit {

		@Test
		void addsVisitToMatchingPet() {
			Pet pet = new Pet();
			pet.setId(1);
			owner.getPets().add(pet);

			Visit visit = new Visit();
			visit.setDescription("Checkup");

			owner.addVisit(1, visit);

			assertThat(pet.getVisits()).contains(visit);
		}

		@Test
		void throwsExceptionWhenPetIdIsNull() {
			Visit visit = new Visit();
			visit.setDescription("Checkup");

			assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(null, visit));
		}

		@Test
		void throwsExceptionWhenVisitIsNull() {
			Pet pet = new Pet();
			pet.setId(1);
			owner.getPets().add(pet);

			assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(1, null));
		}

		@Test
		void throwsExceptionWhenPetIdDoesNotMatchAnyPet() {
			assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(99, new Visit()));
		}

	}

}
