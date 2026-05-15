// from global agent
// from global claude.md
## Plan: Petclinic Improvement Roadmap

This draft plan focuses on highest-impact improvements in reliability, security, and maintainability with minimal architectural churn. It starts by baselining current behavior, then hardens operational exposure, improves data-access performance, and closes test/quality gaps. The goal is a safer production posture and faster iteration while keeping the existing MVC + JPA structure (`OwnerController`, `PetController`, `VetRepository`) intact.

### Steps
1. Baseline hotspots from [README.md](README.md), [pom.xml](pom.xml), and [build.gradle](build.gradle) around `java.version`, plugins, and current testing scope.
2. Harden runtime surface in [application.properties](src/main/resources/application.properties) and add guarded endpoint policy for `management.endpoints.web.exposure.include`.
3. Introduce web security layer around [OwnerController.java](src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java) and [VetController.java](src/main/java/org/springframework/samples/petclinic/vet/VetController.java) routes.
4. Reduce ORM overfetch by reviewing eager mappings in [Owner.java](src/main/java/org/springframework/samples/petclinic/owner/Owner.java), [Pet.java](src/main/java/org/springframework/samples/petclinic/owner/Pet.java), and [Vet.java](src/main/java/org/springframework/samples/petclinic/vet/Vet.java).
5. Strengthen resilience with centralized exception handling for `IllegalArgumentException` across [PetController.java](src/main/java/org/springframework/samples/petclinic/owner/PetController.java) and [VisitController.java](src/main/java/org/springframework/samples/petclinic/owner/VisitController.java).
6. Expand regression coverage in [OwnerControllerTests.java](src/test/java/org/springframework/samples/petclinic/owner/OwnerControllerTests.java), [VisitControllerTests.java](src/test/java/org/springframework/samples/petclinic/owner/VisitControllerTests.java), and integration suites.

### Further Considerations
1. Which track should be first? Option A security hardening, Option B performance tuning, Option C test/quality gates.
2. Keep dual build tooling (`pom.xml` and `build.gradle`) or standardize on one for simpler maintenance?
3. Prioritize production-readiness (`docker-compose.yml`, `k8s/petclinic.yml`) now, or after application-layer refactors?
