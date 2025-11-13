# Refactoring Checklist: java1 → TodoList Application

## How to Use This Checklist
- Mark tasks as complete: `- [x]`
- Mark tasks as in progress: `- [ ]` (add `🚧` emoji)
- Mark tasks as pending: `- [ ]`
- Update this file as you complete each task
- Refer to REFACTORING_PLAN.md for detailed implementation guidance

---

## Phase 1: Foundation & Structure (4-6 hours)
**Status**: ⏳ Not Started  
**Risk**: 🟢 LOW

### Project Structure
- [ ] Create directory structure (`src/main/java/com/todolist/...`)
- [ ] Create package hierarchy (model, service, repository, persistence, ui)
- [ ] Create test directory structure
- [ ] Archive existing `A.java` and `ATest.java` to `archive/` folder

### Build System
- [ ] Create `build.gradle` with Java 21+ configuration
- [ ] Add dependencies (JUnit 5, Jackson, JaCoCo)
- [ ] Create `settings.gradle`
- [ ] Configure Gradle wrapper
- [ ] Verify build works: `./gradlew build`

### Git & Documentation
- [ ] Create `.gitignore` file
- [ ] Create `README.md` with project overview
- [ ] Create `ARCHITECTURE.md` with design documentation
- [ ] Initialize git repository (if needed)
- [ ] Create initial commit

### Module Configuration
- [ ] Update `module-info.java` with new module name
- [ ] Add Jackson module requirements
- [ ] Export necessary packages
- [ ] Verify module compilation

**Phase 1 Acceptance Criteria**:
- [ ] `./gradlew build` succeeds
- [ ] Project structure matches specification
- [ ] All documentation files created
- [ ] Git repository clean

---

## Phase 2: Core Domain Model (6-8 hours)
**Status**: ⏳ Not Started  
**Risk**: 🟢 LOW

### Priority Enum
- [ ] Create `Priority.java` enum (LOW, MEDIUM, HIGH)
- [ ] Add `getDisplayName()` method
- [ ] Add `getLevel()` method
- [ ] Add `fromString()` static method
- [ ] Add JavaDoc documentation

### Priority Tests
- [ ] Create `PriorityTest.java`
- [ ] Test enum values
- [ ] Test display names
- [ ] Test level ordering
- [ ] Test fromString() with various inputs
- [ ] Test fromString() defaults

### TodoItem Record
- [ ] Create `TodoItem.java` record
- [ ] Define fields (id, description, completed, createdDate, priority, dueDate)
- [ ] Implement compact constructor with validation
- [ ] Add convenience constructors
- [ ] Add "with" methods (withCompleted, withPriority, etc.)
- [ ] Add `isOverdue()` method
- [ ] Add `toFormattedString()` method
- [ ] Add Jackson annotations for JSON support
- [ ] Add JavaDoc documentation

### TodoItem Tests
- [ ] Create `TodoItemTest.java`
- [ ] Test constructor with description
- [ ] Test constructor with description and priority
- [ ] Test blank description throws exception
- [ ] Test description trimming
- [ ] Test null ID throws exception
- [ ] Test past due date throws exception
- [ ] Test withCompleted() returns new instance
- [ ] Test withPriority() returns new instance
- [ ] Test isOverdue() logic
- [ ] Test toFormattedString() output
- [ ] Test equals() and hashCode()
- [ ] Test immutability

### Documentation
- [ ] Create `package-info.java` for model package
- [ ] Verify JavaDoc completeness
- [ ] Run `./gradlew javadoc`

**Phase 2 Acceptance Criteria**:
- [ ] All tests pass
- [ ] Test coverage for model package ≥ 90%
- [ ] All validation rules working
- [ ] JavaDoc complete

---

## Phase 3: Business Logic & Services (8-12 hours)
**Status**: ⏳ Not Started  
**Risk**: 🟡 MEDIUM

### Result Type
- [ ] Create `Result.java` sealed interface
- [ ] Define `Success<T>` record
- [ ] Define `Failure<T>` record
- [ ] Add `isSuccess()` and `isFailure()` methods
- [ ] Add `getValue()` and `getError()` methods
- [ ] Add JavaDoc with usage examples

### Result Tests
- [ ] Create `ResultTest.java`
- [ ] Test Success creation
- [ ] Test Failure creation
- [ ] Test null handling
- [ ] Test pattern matching
- [ ] Test getValue() and getError() methods

### TodoValidator
- [ ] Create `TodoValidator.java`
- [ ] Implement `validateDescription()` method
- [ ] Implement `validateDueDate()` method
- [ ] Implement `validatePriority()` method
- [ ] Implement `validateTodoItem()` method
- [ ] Add JavaDoc documentation

### TodoValidator Tests
- [ ] Create `TodoValidatorTest.java`
- [ ] Test description validation (null, empty, too long)
- [ ] Test due date validation
- [ ] Test priority validation
- [ ] Test complete item validation

### TodoRepository Interface
- [ ] Create `TodoRepository.java` interface
- [ ] Define `save()` method
- [ ] Define `findById()` method
- [ ] Define `findAll()` method
- [ ] Define `delete()` method
- [ ] Define `update()` method
- [ ] Add JavaDoc documentation

### InMemoryTodoRepository
- [ ] Create `InMemoryTodoRepository.java`
- [ ] Implement all repository methods
- [ ] Use `Map<UUID, TodoItem>` for storage
- [ ] Handle edge cases (null IDs, not found, etc.)
- [ ] Add JavaDoc documentation

### InMemoryTodoRepository Tests
- [ ] Create `InMemoryTodoRepositoryTest.java`
- [ ] Test save() operation
- [ ] Test findById() with valid and invalid IDs
- [ ] Test findAll() with empty and populated repository
- [ ] Test delete() operation
- [ ] Test update() operation

### TodoService
- [ ] Create `TodoService.java`
- [ ] Add constructor with repository and validator dependencies
- [ ] Implement `createTodo()` method
- [ ] Implement `getTodo()` method
- [ ] Implement `getAllTodos()` method
- [ ] Implement `completeTodo()` method
- [ ] Implement `deleteTodo()` method
- [ ] Implement `updatePriority()` method
- [ ] Return `Result<T>` from all methods
- [ ] Add JavaDoc documentation

### TodoService Tests
- [ ] Create `TodoServiceTest.java`
- [ ] Test createTodo() success and failure cases
- [ ] Test getTodo() with valid and invalid IDs
- [ ] Test getAllTodos()
- [ ] Test completeTodo()
- [ ] Test deleteTodo()
- [ ] Test updatePriority()
- [ ] Use mock repository for testing

### Documentation
- [ ] Create `package-info.java` for service package
- [ ] Create `package-info.java` for repository package
- [ ] Verify JavaDoc completeness

**Phase 3 Acceptance Criteria**:
- [ ] All tests pass
- [ ] Service layer test coverage ≥ 85%
- [ ] All error cases handled via Result
- [ ] Business logic separated from data access

---

## Phase 4: Testing Infrastructure (8-10 hours)
**Status**: ⏳ Not Started  
**Risk**: 🟢 LOW

### JUnit 5 Migration
- [ ] Remove old `ATest.java` (move to archive)
- [ ] Verify all tests use JUnit 5 annotations
- [ ] Verify all tests use JUnit 5 assertions
- [ ] Run `./gradlew test` to verify

### JaCoCo Configuration
- [ ] Configure JaCoCo plugin in `build.gradle`
- [ ] Set minimum coverage threshold (80%)
- [ ] Configure coverage reports (XML and HTML)
- [ ] Exclude main classes from coverage
- [ ] Add `jacocoTestCoverageVerification` task

### Coverage Analysis
- [ ] Run `./gradlew jacocoTestReport`
- [ ] Review coverage report
- [ ] Identify uncovered code paths
- [ ] Add tests for uncovered areas
- [ ] Re-run coverage report

### Test Improvements
- [ ] Add parameterized tests where appropriate
- [ ] Add edge case tests
- [ ] Add integration tests for component interactions
- [ ] Verify test naming follows conventions
- [ ] Verify test organization

**Phase 4 Acceptance Criteria**:
- [ ] All tests pass
- [ ] Overall test coverage ≥ 80%
- [ ] JaCoCo report generates successfully
- [ ] Coverage verification passes

---

## Phase 5: Persistence & UI (10-14 hours)
**Status**: ⏳ Not Started  
**Risk**: 🟡 MEDIUM-HIGH

### JsonTodoRepository
- [ ] Create `JsonTodoRepository.java`
- [ ] Implement `TodoRepository` interface
- [ ] Use Jackson `ObjectMapper` for serialization
- [ ] Implement `loadFromFile()` method
- [ ] Implement `saveToFile()` method
- [ ] Handle file I/O exceptions gracefully
- [ ] Support custom file paths
- [ ] Add JavaDoc documentation

### JsonTodoRepository Tests
- [ ] Create `JsonTodoRepositoryTest.java`
- [ ] Test save and load operations
- [ ] Test file creation if not exists
- [ ] Test handling of corrupted files
- [ ] Test handling of missing files
- [ ] Test concurrent access (if applicable)
- [ ] Use temporary files for testing

### TodoCli
- [ ] Create `TodoCli.java`
- [ ] Implement menu display
- [ ] Implement command loop
- [ ] Add "add" command handler
- [ ] Add "list" command handler
- [ ] Add "complete" command handler
- [ ] Add "delete" command handler
- [ ] Add "filter" command handler (by priority)
- [ ] Add "exit" command handler
- [ ] Handle invalid input gracefully
- [ ] Use pattern matching for command handling
- [ ] Add JavaDoc documentation

### TodoApp
- [ ] Create `TodoApp.java` with main method
- [ ] Initialize `JsonTodoRepository`
- [ ] Initialize `TodoValidator`
- [ ] Initialize `TodoService`
- [ ] Initialize `TodoCli`
- [ ] Start CLI loop
- [ ] Add shutdown hook for saving data
- [ ] Handle exceptions at top level

### Module Updates
- [ ] Update `module-info.java` with Jackson dependencies
- [ ] Verify all exports are correct
- [ ] Test module compilation

### Integration Testing
- [ ] Create `TodoAppIntegrationTest.java`
- [ ] Test complete workflow (create → list → complete → delete)
- [ ] Test persistence (save → restart → load)
- [ ] Test error scenarios

### Manual Testing
- [ ] Build application: `./gradlew build`
- [ ] Run application: `./gradlew run`
- [ ] Test add command
- [ ] Test list command
- [ ] Test complete command
- [ ] Test delete command
- [ ] Test filter command
- [ ] Test exit command
- [ ] Verify JSON file creation
- [ ] Restart and verify data persistence

**Phase 5 Acceptance Criteria**:
- [ ] All automated tests pass
- [ ] CLI works correctly (manual testing)
- [ ] JSON persistence works
- [ ] Data survives application restart
- [ ] Error handling is robust

---

## Phase 6: Advanced Features & Polish (8-16 hours)
**Status**: ⏳ Not Started  
**Risk**: 🟢 LOW

### Search and Filter Features
- [ ] Add `searchByDescription()` to TodoService
- [ ] Add `filterByPriority()` to TodoService
- [ ] Add `filterByCompleted()` to TodoService
- [ ] Add `findOverdue()` to TodoService
- [ ] Update CLI to support search/filter commands
- [ ] Add tests for new features

### Code Quality Tools

#### Checkstyle
- [ ] Create `config/checkstyle/checkstyle.xml`
- [ ] Configure Checkstyle plugin in build.gradle
- [ ] Run `./gradlew checkstyleMain`
- [ ] Fix all violations
- [ ] Run `./gradlew checkstyleTest`
- [ ] Fix all violations

#### SpotBugs
- [ ] Configure SpotBugs plugin in build.gradle
- [ ] Run `./gradlew spotbugsMain`
- [ ] Review and fix high-priority issues
- [ ] Add suppressions for false positives (if any)

### Documentation

#### Architecture Documentation
- [ ] Complete ARCHITECTURE.md with:
  - [ ] Detailed layer descriptions
  - [ ] Class diagrams (ASCII art)
  - [ ] Sequence diagrams for main workflows
  - [ ] Design pattern explanations
  - [ ] Extension points

#### User Guide
- [ ] Create USER_GUIDE.md
- [ ] Document all CLI commands
- [ ] Add usage examples
- [ ] Document configuration options
- [ ] Add troubleshooting section

#### Developer Guide
- [ ] Update README.md with:
  - [ ] Building instructions
  - [ ] Testing instructions
  - [ ] Contributing guidelines
  - [ ] Code style guidelines

### Final Testing
- [ ] Run full test suite: `./gradlew test`
- [ ] Run coverage verification: `./gradlew jacocoTestCoverageVerification`
- [ ] Run Checkstyle: `./gradlew checkstyleMain checkstyleTest`
- [ ] Run SpotBugs: `./gradlew spotbugsMain`
- [ ] Build project: `./gradlew build`
- [ ] Run application end-to-end testing

### Final Verification
- [ ] Verify test coverage ≥ 80%
- [ ] Verify 0 Checkstyle violations
- [ ] Verify 0 high-priority SpotBugs issues
- [ ] Verify JavaDoc coverage 100% for public API
- [ ] Verify build time < 30 seconds
- [ ] Verify all documentation is complete

**Phase 6 Acceptance Criteria**:
- [ ] All quality gates pass
- [ ] Documentation is comprehensive
- [ ] Application is production-ready
- [ ] All acceptance criteria met

---

## Overall Progress Tracking

### Phase Completion
- [ ] Phase 1: Foundation & Structure (0%)
- [ ] Phase 2: Core Domain Model (0%)
- [ ] Phase 3: Business Logic & Services (0%)
- [ ] Phase 4: Testing Infrastructure (0%)
- [ ] Phase 5: Persistence & UI (0%)
- [ ] Phase 6: Advanced Features & Polish (0%)

### Overall Completion: 0%

### Milestones
- [ ] M1: Foundation Ready (End of Phase 1)
- [ ] M2: Domain Model Complete (End of Phase 2)
- [ ] M3: Business Logic Complete (End of Phase 3)
- [ ] M4: Testing Complete (End of Phase 4)
- [ ] M5: MVP Ready (End of Phase 5)
- [ ] M6: Production Ready (End of Phase 6)

---

## Notes and Blockers

### Current Blockers
- None

### Important Decisions
- None yet

### Deviations from Plan
- None yet

### Time Tracking
- **Estimated Total**: 44-66 hours
- **Actual Time Spent**: 0 hours
- **Remaining Estimate**: 44-66 hours

---

**Last Updated**: 2025-11-13  
**Current Phase**: Planning Complete  
**Next Action**: Begin Phase 1 - Foundation & Structure
