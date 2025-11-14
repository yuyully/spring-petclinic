# Plan: Define and refine agent 'test2'

This plan provides guidance for defining and refining the custom agent "test2" for the spring-petclinic repository.

## Steps

### 1. Clarify Purpose
Define the specific purpose and scope of the "test2" agent:
- What tasks will this agent handle?
- What domain expertise does it need?
- How does it differ from existing agents?

### 2. Specify Tools
Identify and document the tools this agent needs:
- File operations (view, create, edit)
- Build tools (Maven, Gradle)
- Testing frameworks (JUnit, Spring Test)
- Code quality tools (Checkstyle, linters)
- Version control (git commands)

### 3. Operating Instructions
Define clear operating guidelines:
- **Purpose**: Describe the agent's primary function
- **When to use**: Specify scenarios where this agent should be invoked
- **Style**: Define code style and documentation conventions
- **Constraints**: List limitations and boundaries
- **Dos and Don'ts**: Provide explicit guidance
- **Tool usage policy**: How to leverage available tools effectively
- **Delegation policy**: When to delegate to other agents

### 4. Success Criteria & Quality Gates
Establish verification requirements:
- Requirements coverage: All specified requirements must be met
- Build validation: `./mvnw verify` or `./gradlew build` must pass
- Lint/style checks: Checkstyle and code formatting rules must pass
- Tests: All relevant tests must pass, including unit and integration tests
- Documentation: Updates must include appropriate documentation

### 5. Assumptions & Edge Cases
Document important considerations:
- Build system compatibility (Maven and Gradle)
- Java version requirements (Java 17+, runtime supports Java 25)
- Spring Boot 4.0.0-RC2 framework specifics
- Database configurations (H2, MySQL, PostgreSQL)
- Test categories and execution patterns

### 6. Future Refinement Checklist
Items for ongoing improvement:
- [ ] Validate agent performance on real tasks
- [ ] Gather feedback from usage patterns
- [ ] Refine tool selection and usage
- [ ] Update operating instructions based on learnings
- [ ] Add specific examples and use cases
- [ ] Document common pitfalls and solutions
- [ ] Benchmark against similar agents
- [ ] Iterate on quality gates and success criteria

## Operating Instructions

### Purpose
The "test2" agent is designed to assist with development tasks in the spring-petclinic project, providing specialized support for Java Spring Boot applications.

### When to Use
Invoke this agent when:
- Working with Spring Boot application components
- Implementing or modifying pet clinic domain logic
- Writing or updating tests for controllers, services, or models
- Ensuring code quality and style compliance
- Refactoring existing functionality

### Style
- Follow Spring Framework conventions and best practices
- Use Java 17 language features appropriately
- Maintain consistent code formatting per project Checkstyle rules
- Write clear, self-documenting code with minimal but meaningful comments
- Use dependency injection and Spring annotations properly

### Constraints
- Do not modify core Spring Boot framework files
- Preserve existing test coverage
- Maintain backward compatibility unless explicitly required
- Follow the project's package structure (`org.springframework.samples.petclinic.*`)
- Respect database abstraction layers

### Dos
- Run `./mvnw verify` to validate changes
- Use `./gradlew test` for quick test feedback
- Check Checkstyle compliance: `src/checkstyle/nohttp-checkstyle.xml`
- Write unit tests alongside code changes
- Update documentation for public APIs
- Use existing Spring components and utilities

### Don'ts
- Don't skip tests or remove test coverage
- Don't introduce new dependencies without justification
- Don't modify configuration files without understanding impact
- Don't bypass security or validation layers
- Don't ignore Checkstyle or compiler warnings

### Tool Usage Policy
- **Prefer ecosystem tools**: Use Maven/Gradle commands over manual operations
- **Leverage Spring testing**: Use `@SpringBootTest`, `@WebMvcTest`, etc.
- **Run incrementally**: Test changes frequently with quick feedback loops
- **Use git effectively**: Check status and diffs before committing

### Delegation Policy
- Delegate to specialized agents when available
- For complex refactoring, consider code-analysis-focused agents
- For documentation tasks, use documentation agents if available
- Trust custom agent results; validate only on failure

## Tools & Rationale

### Build & Test Tools
- **Maven** (`./mvnw`): Primary build tool, use for full verification
  - `./mvnw verify`: Complete build with tests and checks
  - `./mvnw test`: Run tests only
  - `./mvnw checkstyle:check`: Validate code style
- **Gradle** (`./gradlew`): Alternative build tool
  - `./gradlew build`: Full build
  - `./gradlew test`: Run tests
- **JUnit 5**: Testing framework for unit and integration tests
- **Spring Test**: Spring-specific testing utilities

### Code Quality Tools
- **Checkstyle**: Enforces code style rules
  - Configuration: `src/checkstyle/nohttp-checkstyle.xml`
  - Suppressions: `src/checkstyle/nohttp-checkstyle-suppressions.xml`
- **Maven Enforcer**: Ensures build consistency
- **Jacoco**: Code coverage analysis

### Development Tools
- **Spring Boot DevTools**: Hot reload during development
- **Spring Boot Actuator**: Application monitoring and management
- **H2 Database**: In-memory database for testing
- **MySQL/PostgreSQL**: Production database options

## Success Criteria & Quality Gates

### Requirements Coverage
- All specified requirements in the task must be implemented
- Edge cases identified in planning must be handled
- Documentation must reflect all changes

### Build Validation
- Maven build must pass: `./mvnw verify` exits with code 0
- Gradle build must pass: `./gradlew build` exits with code 0
- No compilation errors or warnings introduced

### Lint & Style
- Checkstyle validation must pass
- No HTTP URLs (HTTPS required per nohttp-checkstyle)
- Code formatting follows Spring conventions
- No new linter warnings introduced

### Tests
- All existing tests must continue to pass
- New functionality must include appropriate tests
- Test coverage should not decrease
- Integration tests must pass for all database profiles
- No flaky tests introduced

### Documentation
- Public APIs must have JavaDoc
- README updates for user-facing changes
- Configuration changes documented
- Migration notes for breaking changes

## Edge Cases & Assumptions

### Build Systems
- Project supports both Maven and Gradle
- Maven is the primary build system (pom.xml is authoritative)
- Gradle configuration mirrors Maven capabilities
- Both must be kept in sync for dependencies and plugins

### Java Version Compatibility
- Source/target: Java 17 (maven.compiler.release=17)
- Runtime: Supports up to Java 25 (java.version=25)
- Use Java 17 language features safely
- Avoid Java 25-specific features that break Java 17 compatibility

### Database Profiles
- Default: H2 in-memory database
- Optional: MySQL profile (`-Pmysql`)
- Optional: PostgreSQL profile (`-Ppostgres`)
- Tests must work with all supported databases

### Spring Boot Version
- Using Spring Boot 4.0.0-RC2 (release candidate)
- APIs may differ from stable versions
- Check migration guides for version-specific changes
- Some features may be experimental

### Testing Considerations
- Some tests require specific profiles
- Integration tests may need database setup
- Web tests use `@WebMvcTest` or `@SpringBootTest`
- Test data initialization via SQL scripts or test fixtures

### Directory Structure
- Source: `src/main/java/org/springframework/samples/petclinic/`
- Tests: `src/test/java/org/springframework/samples/petclinic/`
- Resources: `src/main/resources/`
- Static assets: `src/main/resources/static/`
- Templates: `src/main/resources/templates/`

## Future Refinement Checklist

- [ ] Test agent on actual spring-petclinic issues and PRs
- [ ] Document successful patterns and anti-patterns
- [ ] Create example scenarios and expected outcomes
- [ ] Refine tool selection based on task effectiveness
- [ ] Add metrics for agent performance (time, quality, accuracy)
- [ ] Establish feedback loop for continuous improvement
- [ ] Compare with other Spring Boot specialized agents
- [ ] Update based on Spring Boot 4.0.0 final release changes
