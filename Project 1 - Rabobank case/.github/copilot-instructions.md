# Copilot Instructions — Customer Case Summary (Rabobank)

## Project Context
Internal tool for Rabobank customer service agents to manage and summarize customer cases.
Built with React/TypeScript (frontend) and Java 17 + Spring Boot 3 (backend).

## Coding Standards

### Java / Spring Boot
- **Logging**: Always use SLF4J (`LoggerFactory.getLogger()`). Never use `System.out.println()`.
- **Return types**: Controllers must return `ResponseEntity<T>`, never raw objects.
- **Money/amounts**: Use `BigDecimal` for financial values, never `double` or `float`.
- **Database queries**: Use parameterized queries or Spring Data derived query methods. Never concatenate user input into SQL strings.
- **Sensitive data**: Mask BSN (burgerservicenummer) and IBAN in log output. Use `maskIban()` and `maskBsn()` helper methods.
- **Naming**: Dutch domain terms in comments, English in code. Class names in PascalCase, methods in camelCase.
- **Validation**: Use Jakarta Bean Validation (`@NotBlank`, `@NotNull`, etc.) on DTOs. Validate at controller layer.
- **Error handling**: Throw domain-specific exceptions (e.g., `CaseNotFoundException`). Use `@ControllerAdvice` for centralized error responses.
- **Testing**: Use JUnit 5 + Mockito. Test service layer with mocked repositories. Test edge cases: null inputs, empty collections, boundary values.

### TypeScript / React
- **Components**: Functional components with TypeScript interfaces for props 
- **State management**: React hooks (`useState`, `useEffect`, custom hooks). No class components.
- **Styling**: Tailwind CSS utility classes. Use Rabobank brand colors: primary `#0066FF`, secondary `#003380`, accent `#FF6600`.
- **Accessibility**: All interactive elements must have `aria-label` attributes. Use semantic HTML (`<main>`, `<nav>`, `<section>`).
- **API calls**: Centralize in `services/` directory. Handle loading, error, and empty states.
- **Date formatting**: Use `Intl.DateTimeFormat('nl-NL')` for Dutch date/time display.
- **Testing**: React Testing Library + Jest. Test user interactions, not implementation details.

### Security
- Never expose raw database IDs in URLs (use UUIDs)
- Sanitize all user input before display (XSS prevention)
- Mask PII (IBAN, BSN) in API responses and logs
- Use CORS configuration to restrict allowed origins

### API Conventions
- RESTful endpoints: `/api/v1/cases`, `/api/v1/cases/{id}`
- Versioned API paths (`/api/v1/...`)
- Standard HTTP status codes: 200 OK, 201 Created, 400 Bad Request, 404 Not Found, 409 Conflict
- Paginated list responses using Spring Data `Page<T>`
