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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

class PetClinicApplicationTests {

	@Test
	void mainDelegatesToSpringApplicationRun() {
		try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
			PetClinicApplication.main(new String[] {});
			mocked.verify(() -> SpringApplication.run(PetClinicApplication.class, new String[] {}));
		}
	}

	@Test
	void mainPassesCommandLineArgsToSpringApplicationRun() {
		try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
			String[] args = { "--spring.profiles.active=test" };
			PetClinicApplication.main(args);
			mocked.verify(() -> SpringApplication.run(PetClinicApplication.class, args));
		}
	}

	@Test
	void mainPropagatesExceptionWhenSpringApplicationRunFails() {
		try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
			RuntimeException startupException = new RuntimeException("Context failed to start");
			mocked.when(() -> SpringApplication.run(any(Class.class), any(String[].class))).thenThrow(startupException);
			assertThatThrownBy(() -> PetClinicApplication.main(new String[] {})).isInstanceOf(RuntimeException.class)
				.hasMessage("Context failed to start");
		}
	}

}
