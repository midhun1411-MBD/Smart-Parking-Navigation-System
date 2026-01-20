# Contributing to Smart Parking Navigation System

Thank you for your interest in contributing to the Smart Parking Navigation System! This document provides guidelines and instructions for contributing to the project.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [How to Contribute](#how-to-contribute)
- [Development Setup](#development-setup)
- [Coding Standards](#coding-standards)
- [Commit Guidelines](#commit-guidelines)
- [Pull Request Process](#pull-request-process)

## Code of Conduct

We are committed to maintaining a welcoming and inclusive community. Please be respectful and professional in all interactions.

## How to Contribute

### Reporting Bugs
1. Check if the bug has already been reported in Issues
2. If not, create a new issue with:
   - Clear title describing the bug
   - Detailed description of the issue
   - Steps to reproduce
   - Expected vs. actual behavior
   - Screenshots or logs if applicable

### Suggesting Features
1. Check existing issues and discussions
2. Create a new issue with:
   - Clear title describing the feature
   - Detailed description of use case
   - Proposed implementation approach
   - Examples or mockups if applicable

### Improving Documentation
1. Fork the repository
2. Update documentation files
3. Submit a pull request with clear description

## Development Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Git
- IDE (IntelliJ IDEA, VS Code, or Eclipse)

### Setup Instructions

1. **Fork the Repository**
   ```bash
   # Go to https://github.com/midhun1411-MBD/Smart-Parking-Navigation-System
   # Click Fork button
   ```

2. **Clone Your Fork**
   ```bash
   git clone https://github.com/YOUR_USERNAME/Smart-Parking-Navigation-System.git
   cd Smart-Parking-Navigation-System
   ```

3. **Add Upstream Remote**
   ```bash
   git remote add upstream https://github.com/midhun1411-MBD/Smart-Parking-Navigation-System.git
   ```

4. **Install Dependencies**
   ```bash
   mvn clean install
   ```

5. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

## Coding Standards

### Java Code Style
- Follow Google Java Style Guide
- Use meaningful variable names
- Keep methods focused and concise
- Add javadoc comments for public methods
- Maximum line length: 100 characters

### Frontend Code Style
- Use meaningful class and variable names
- Keep functions modular and reusable
- Add comments for complex logic
- Use consistent indentation (2 spaces)

### Example Java Method
```java
/**
 * Finds the nearest available parking slot.
 * 
 * @return the slot ID of the nearest available slot, or -1 if no slots available
 */
public int findNearestSlot() {
    for (ParkingSlot slot : slots) {
        if (!slot.isOccupied()) {
            return slot.getSlotId();
        }
    }
    return -1;
}
```

## Commit Guidelines

### Commit Message Format
```
<type>: <subject>

<body>

<footer>
```

### Types
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (no logic changes)
- `refactor`: Code refactoring
- `test`: Test additions or modifications
- `chore`: Build, dependencies, tools

### Examples
```
feat: Add email notification for booking confirmation

fix: Resolve issue with slot availability calculation

docs: Update API documentation with new endpoints

refactor: Simplify payment calculation logic
```

### Good Commit Messages
✅ DO:
- Be concise and descriptive
- Use imperative mood ("Add" not "Added")
- Reference issues (#123)
- Keep commits atomic (one logical change)

❌ DON'T:
- Mix multiple unrelated changes
- Write vague messages like "update" or "fix"
- Include debugging or temporary code

## Pull Request Process

### Before Submitting
1. **Update Your Branch**
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```

2. **Test Your Changes**
   ```bash
   mvn clean install
   mvn test
   mvn spring-boot:run
   ```

3. **Push to Your Fork**
   ```bash
   git push origin your-branch-name
   ```

### Creating a Pull Request
1. Go to the original repository
2. Click "New Pull Request"
3. Select your branch
4. Fill in the PR template:
   - **Title**: Clear, descriptive title
   - **Description**: Detailed explanation of changes
   - **Related Issues**: Link to related issues
   - **Type**: Feature, Bug Fix, Documentation, etc.
   - **Screenshots**: If UI changes
   - **Testing**: How you tested the changes

### PR Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex logic
- [ ] Documentation updated
- [ ] No new warnings generated
- [ ] Tests added/updated
- [ ] Tests pass locally
- [ ] Commits are clean and descriptive

### Review Process
1. Maintainers will review your PR
2. Address any requested changes
3. Push updates to the same branch (don't create new PR)
4. PR will be merged once approved

## Project Structure

```
src/
├── main/
│   ├── java/com/smartparking/
│   │   ├── controller/     # REST API controllers
│   │   ├── model/          # Entity classes
│   │   ├── repository/     # Database access layer
│   │   ├── service/        # Business logic
│   │   └── Application.java
│   └── resources/
│       ├── application.properties
│       └── static/         # Frontend files
└── test/                    # Unit tests
```

## Testing

### Running Tests
```bash
mvn test
```

### Writing Tests
- Create test class in `src/test/java`
- Use JUnit 4 or 5
- Test file name: `*Test.java`
- One assertion per test is preferred
- Use descriptive test names

Example:
```java
@Test
public void testFindNearestSlotReturnsValidSlotId() {
    // Arrange
    SmartParkingSystem system = new SmartParkingSystem(10);
    
    // Act
    int slotId = system.findNearestSlot();
    
    // Assert
    assertTrue(slotId > 0);
}
```

## Need Help?

- 📧 Email: Contact project maintainers
- 💬 Discussions: Use GitHub Discussions for questions
- 📝 Documentation: Check README.md and docs/

## License

By contributing to this project, you agree that your contributions will be licensed under the same license as the project.

---

Thank you for contributing! Your efforts help make this project better. 🎉
