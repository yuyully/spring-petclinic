# Custom Agent: test2

This is a custom agent for the spring-petclinic project.

## Purpose

The test2 agent assists with development tasks in the spring-petclinic project, providing specialized support for Java Spring Boot applications.

## Capabilities

- Working with Spring Boot application components
- Implementing or modifying pet clinic domain logic
- Writing or updating tests for controllers, services, or models
- Ensuring code quality and style compliance
- Refactoring existing functionality

## Tools

- Maven (`./mvnw`) and Gradle (`./gradlew`) build systems
- JUnit 5 and Spring Test frameworks
- Checkstyle for code quality validation
- Git for version control
- File operations (view, create, edit)

## Operating Guidelines

For detailed operating instructions, success criteria, and refinement guidelines, see the comprehensive plan at:

**[plan-test2.prompt.md](../../plan-test2.prompt.md)**

## Quick Reference

### Build & Test Commands
```bash
# Full Maven verification
./mvnw verify

# Run tests only
./mvnw test

# Checkstyle validation
./mvnw checkstyle:check

# Gradle build
./gradlew build
```

### Code Quality
- Follow Spring Framework conventions
- Maintain Checkstyle compliance: `src/checkstyle/nohttp-checkstyle.xml`
- Use Java 17 language features
- Write tests for all changes

### Key Constraints
- Do not modify core Spring Boot framework files
- Preserve existing test coverage
- Maintain backward compatibility
- Follow package structure: `org.springframework.samples.petclinic.*`
