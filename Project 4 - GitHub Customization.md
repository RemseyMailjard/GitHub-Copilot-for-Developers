🎯 GitHub Copilot Customization Guide
When to Use: Instructions, Prompts, Skills & Agents

🌳 Quick Decision Tree
Start here when deciding which customization type to use:

❓ Should this apply to EVERY interaction?
Think: coding standards, project conventions, response style preferences

↓ YES
✅ Use: .github/copilot-instructions.md
Always loaded, applies universally across the entire repository

↓ NO
❓ Should it apply only to SPECIFIC file types or directories?
Think: Python rules for .py files, React conventions for components/

↓ YES
✅ Use: .github/instructions/*.instructions.md
Conditionally loaded based on applyTo glob patterns

↓ NO
❓ Is it a REUSABLE task you'll invoke manually?
Think: scaffolding components, generating tests, code reviews

↓ YES
✅ Use: .github/prompts/*.prompt.md
Loaded only when you type /prompt-name

↓ NO
❓ Is it a COMPLEX workflow with scripts/resources?
Think: debugging CI/CD, security reviews, multi-step processes

↓ YES
✅ Use: .github/skills/*/SKILL.md
Auto-loaded when description matches, includes bundled resources

↓ NO
❓ Is it a PERSISTENT persona for specific workflows?
Think: "Frontend Expert", "Security Reviewer", "Bug Hunter"

↓ YES
✅ Use: Custom Agent
Explicitly selected, orchestrates multiple skills and tools

📁 Type 1: Repository-Wide Instructions
.github/copilot-instructions.md
The Foundation: Always Active, Always Available
Loading:
ALWAYS Automatic, every interaction
Scope:
Entire repository, all files
Best For:
Universal standards and context
Priority:
Medium (after personal, before org)
When to Use
Project Overview
"Copilot doesn't understand our architecture"
Explain: Tech stack, folder structure, how services communicate

✓ Use copilot-instructions.md
Coding Standards
"Generated code doesn't follow our conventions"
Define: Naming conventions, code style, linting rules that apply everywhere

✓ Use copilot-instructions.md
Build/Test Commands
"Copilot suggests wrong build commands"
Document: How to build, run tests, deploy, common commands

✓ Use copilot-instructions.md
Response Style
"Copilot's answers are too verbose"
Set preferences: Response length, formatting, tone

✓ Use copilot-instructions.md
Real Example: E-commerce Platform
# .github/copilot-instructions.md

# Project Overview
Multi-tenant e-commerce platform built with Next.js, TypeScript, and Prisma.
Microservices architecture: API Gateway → Services → PostgreSQL.

# Tech Stack
- Frontend: Next.js 14, React 18, Tailwind CSS
- Backend: Node.js, Express, Prisma ORM
- Database: PostgreSQL 15
- Testing: Jest, React Testing Library, Playwright

# Universal Coding Standards
- Use TypeScript strict mode
- All functions must have JSDoc comments
- Prefer async/await over promises
- Use early returns for error handling
- Follow Airbnb style guide

# Build Commands
- Development: `npm run dev`
- Build: `npm run build`
- Test: `npm test`
- Lint: `npm run lint`

# Response Preferences
- Keep responses concise (under 10 lines when possible)
- Show code examples before explanations
- Use TypeScript, not JavaScript
🎯 Type 2: Path-Specific Instructions
.github/instructions/*.instructions.md
Targeted Rules: Auto-Loads for Matching Files
Loading:
CONDITIONAL Based on applyTo patterns
Scope:
Specific files/directories via glob
Best For:
Area-specific conventions
Combines With:
Repository-wide instructions
When to Use
Language-Specific Rules
"Python files need different conventions than TypeScript"
Create separate instruction files per language

✓ Use *.instructions.md with applyTo
Frontend vs Backend
"API code needs different patterns than UI code"
Separate instructions for src/api/ vs src/components/

✓ Use *.instructions.md with applyTo
Testing Conventions
"Test files need specific patterns and assertions"
Instructions that only apply to **/*.test.* files

✓ Use *.instructions.md with applyTo
Legacy Code Areas
"Don't refactor legacy/ folder, only bug fixes"
Special handling rules for specific directories

✓ Use *.instructions.md with applyTo
Real Example: Monorepo with Multiple Concerns
# .github/instructions/react.instructions.md
---
applyTo: "src/components/**/*.tsx,src/pages/**/*.tsx"
---

# React Component Guidelines
- Use functional components with hooks (no class components)
- Prop types with TypeScript interfaces
- Tailwind for styling (no CSS modules)
- Use React.memo for performance-sensitive components
- Accessibility: all interactive elements need aria-labels

# .github/instructions/python-api.instructions.md
---
applyTo: "services/api/**/*.py"
---

# Python API Guidelines
- FastAPI for all endpoints
- Pydantic models for request/response validation
- Async handlers (async def) for all routes
- Dependency injection for database connections
- OpenAPI documentation with detailed descriptions

# .github/instructions/testing.instructions.md
---
applyTo: "**/__tests__/**/*.ts,**/*.test.tsx,**/*.spec.py"
---

# Testing Standards
- Use React Testing Library (no Enzyme)
- Prefer data-testid over class selectors
- Mock external services and APIs
- Test user-visible behavior, not implementation
- Each test should be independent
💡 Pro Tip:
Path-specific instructions combine with repository-wide instructions. If you're editing src/components/Button.tsx, both copilot-instructions.md AND react.instructions.md will be loaded together.
⚡ Type 3: Prompt Files
.github/prompts/*.prompt.md
Reusable Commands: Manual Invocation via Slash Commands
Loading:
MANUAL Only when you type /prompt-name
Scope:
Specific task you're invoking
Best For:
Repetitive templated tasks
Can Specify:
Model, tools, variables
When to Use
Component Scaffolding
"I create the same component structure repeatedly"
Template for generating React components with tests

✓ Use prompt file: /new-component
Code Reviews
"I need consistent review criteria"
Standardized security/performance review checklist

✓ Use prompt file: /security-review
Documentation Generation
"Generate API docs in our specific format"
Template for creating OpenAPI/JSDoc documentation

✓ Use prompt file: /generate-docs
Test Generation
"Create tests following our patterns"
Generate unit tests with arrange-act-assert pattern

✓ Use prompt file: /create-tests
Real Example: Team Workflow Automation
# .github/prompts/new-react-component.prompt.md
---
agent: 'agent'
model: Claude Sonnet 4
description: 'Generate a new React component with tests'
---

# Create React Component

Generate a new React component following our team standards:

**Component Name:** ${input:componentName:Enter component name (e.g., UserCard)}
**Component Type:** ${input:componentType:atom/molecule/organism}

Requirements:
1. Create component in src/components/${input:componentType}s/
2. Use TypeScript with strict typing
3. Include Props interface
4. Add JSDoc comments
5. Create test file with RTL
6. Include Storybook story
7. Follow accessibility guidelines

Files to create:
- ${input:componentName}.tsx
- ${input:componentName}.test.tsx
- ${input:componentName}.stories.tsx

# .github/prompts/api-security-review.prompt.md
---
agent: 'agent'
description: 'Security review for API endpoints'
---

# API Security Review

Review the selected API code for security vulnerabilities:

Check for:
1. **Authentication/Authorization**
   - Proper token validation
   - Role-based access control
   - Session management

2. **Input Validation**
   - SQL injection risks
   - XSS vulnerabilities
   - Command injection

3. **Data Exposure**
   - Sensitive data in responses
   - Error message information leakage
   - Logging of secrets

4. **Rate Limiting**
   - Endpoint protection
   - DDoS mitigation

Provide specific line numbers and remediation steps.
📝 How to Use:
In Copilot Chat, type /new-react-component and you'll be prompted for the component name and type. The prompt file guides Copilot to generate everything according to your standards.
🎓 Type 4: Agent Skills
.github/skills/*/SKILL.md
Smart Workflows: Auto-Loads with Bundled Resources
Loading:
CONDITIONAL Semantic match on description
Scope:
Complex multi-step workflows
Best For:
Tasks with scripts/resources
Includes:
Instructions + scripts + examples
When to Use
CI/CD Debugging
"GitHub Actions failures need systematic debugging"
Multi-step process with log analysis and fixes

✓ Use skill: github-actions-debugging
Database Migrations
"Complex migration workflow with validation"
Includes migration scripts, rollback procedures

✓ Use skill: database-migration
Performance Analysis
"Systematic profiling and optimization"
Bundle profiling scripts, benchmark data

✓ Use skill: performance-analyzer
Security Audits
"Comprehensive security scanning workflow"
Includes scanning tools, vulnerability databases

✓ Use skill: security-audit
Real Example: CI/CD Debugging Skill
# .github/skills/github-actions-debugging/SKILL.md
---
name: github-actions-debugging
description: Debug failing GitHub Actions workflows. Use when CI/CD fails, builds break, or Actions need troubleshooting.
---

# GitHub Actions Debugging Skill

## When to Use
- GitHub Actions workflow fails
- Build or test failures in CI
- Deployment pipeline issues
- Need to analyze workflow logs

## Debugging Process

1. **Identify the failure**
   - Check workflow run status
   - Identify failed job and step
   - Review error messages

2. **Analyze logs**
   - Use scripts/extract-error.sh to parse logs
   - Look for common patterns in references/common-errors.md
   - Check environment variables and secrets

3. **Common Issues & Fixes**
   
   Issue: Missing dependencies
   - Check package.json/requirements.txt changes
   - Verify cache key matches
   - Run `scripts/validate-deps.sh`

   Issue: Test failures
   - Check for environment-specific issues
   - Review test fixtures
   - Verify test data in references/test-data/

   Issue: Permission errors
   - Check GITHUB_TOKEN permissions
   - Verify workflow permissions in yaml
   - Use `scripts/check-permissions.sh`

4. **Fix and validate**
   - Apply fix locally first
   - Test with act (GitHub Actions local runner)
   - Push and monitor workflow

## Available Resources
- `scripts/extract-error.sh` - Parse workflow logs for errors
- `scripts/check-permissions.sh` - Validate token permissions  
- `scripts/validate-deps.sh` - Check dependency conflicts
- `references/common-errors.md` - Known issues and solutions
- `references/workflow-patterns.md` - Best practices
📂 Skill Directory Structure
.github/skills/github-actions-debugging/ ├── SKILL.md # Main instructions (loaded when skill activates) ├── scripts/ │ ├── extract-error.sh # Parse workflow logs │ ├── check-permissions.sh # Validate permissions │ └── validate-deps.sh # Check dependencies ├── references/ │ ├── common-errors.md # Known issues database │ ├── workflow-patterns.md # Best practices │ └── test-data/ # Sample test fixtures └── examples/ └── fixed-workflow.yml # Working examples
When you say "Why is my GitHub Actions workflow failing?", Copilot:

Matches description semantically
Loads the entire SKILL.md
Can execute scripts in scripts/
References documentation as needed
🎯 Key Difference from Prompts:
Skills are
automatic
(Copilot decides when to use) and include
bundled resources
(scripts, docs). Prompts are
manual
(you invoke) and are just text templates.
🤖 Type 5: Custom Agents
Custom Agents
Persistent Personas: Explicitly Selected for Workflows
Loading:
MANUAL You select the agent explicitly
Scope:
Entire conversation session
Best For:
Consistent workflow personas
Orchestrates:
Multiple skills and tools together
When to Use
Role-Based Workflows
"Need consistent frontend expert behavior"
Agent maintains React/accessibility focus throughout session

✓ Use agent: Frontend Expert
Quality Focus
"Hunt bugs with systematic approach"
Agent prioritizes testing, edge cases, error handling

✓ Use agent: Bug Hunter
Code Review Persona
"Need thorough security-focused reviews"
Agent examines code through security lens consistently

✓ Use agent: Security Reviewer
Teaching Mode
"Onboarding junior developers"
Agent explains concepts, provides learning resources

✓ Use agent: Mentor
Real Example: Frontend Expert Agent
# Custom Agent Configuration

Name: Frontend Expert
Description: Specialized in React, TypeScript, and accessibility

## Behavior
You are a frontend development expert specializing in:
- React 18+ best practices
- TypeScript strict mode
- Web accessibility (WCAG 2.1)
- Performance optimization
- Modern CSS (Tailwind/CSS Modules)

## Approach
1. Always consider component reusability
2. Prioritize accessibility in every suggestion
3. Optimize for performance (lazy loading, memoization)
4. Use semantic HTML
5. Consider mobile-first responsive design

## When helping with code:
- Suggest TypeScript interfaces for props
- Add aria-labels and roles
- Recommend React.memo for heavy components
- Point out potential accessibility issues
- Suggest Lighthouse audits for performance

## Skills to use:
- react-component-generator
- accessibility-checker
- performance-analyzer
🎭 How Agents Differ:
Unlike skills that activate automatically for specific tasks, agents are
personas you select
that maintain consistent behavior across an entire session. They can orchestrate multiple skills and have persistent priorities.
📊 Complete Comparison
Feature	Repository Instructions	Path-Specific Instructions	Prompt Files	Skills	Custom Agents
File Pattern	.github/copilot-instructions.md	.github/instructions/*.instructions.md	.github/prompts/*.prompt.md	.github/skills/*/SKILL.md	UI configuration
Activation	Always Active	File Match	Manual (/name)	Semantic Match	Explicit Select
Scope	All files in repo	Matching paths only	Current task	Relevant workflows	Entire session
Can Include	Text only	Text only	Text + variables	Text + scripts + files	Config + skills
Best For	Universal standards	Area-specific rules	Repeated templates	Complex workflows	Persistent personas
Examples	Tech stack, coding style	Python vs React rules	Component scaffolding	CI/CD debugging	Frontend Expert
Token Usage	Every interaction	When files match	When invoked	When matched	During session
Team Sharing	✅ Version control	✅ Version control	✅ Version control	✅ Version control	⚠️ Export/import
🏢 Real-World Example: Complete Setup
Scenario: SaaS Platform with Microservices
Here's how a real team might structure all customization types together:

# Repository Structure .github/ ├── copilot-instructions.md # ALWAYS LOADED - Universal context │ # Contains: Project overview, tech stack, │ # universal coding standards │ ├── instructions/ # CONDITIONAL - File-specific rules │ ├── python-api.instructions.md # applyTo: "services/**/*.py" │ ├── react-frontend.instructions.md # applyTo: "web/**/*.tsx" │ ├── testing.instructions.md # applyTo: "**/*.test.*" │ └── migrations.instructions.md # applyTo: "db/migrations/**/*" │ ├── prompts/ # MANUAL - Invoke with /command │ ├── new-api-endpoint.prompt.md # /new-api-endpoint │ ├── new-component.prompt.md # /new-component │ ├── generate-migration.prompt.md # /generate-migration │ └── code-review.prompt.md # /code-review │ └── skills/ # AUTOMATIC - Semantic matching ├── github-actions-debug/ │ ├── SKILL.md # CI/CD debugging workflow │ ├── scripts/ # Log analysis scripts │ └── references/ # Known issues database │ ├── performance-optimization/ │ ├── SKILL.md # Performance analysis workflow │ ├── scripts/ # Profiling tools │ └── benchmarks/ # Baseline data │ └── security-audit/ ├── SKILL.md # Security review workflow ├── scripts/ # Scanning tools └── references/ # Vulnerability database # Custom Agents (configured in UI) - Frontend Expert: React/TypeScript/Accessibility specialist - API Developer: Backend API development focus - Bug Hunter: Quality and edge case focus - DevOps Engineer: Infrastructure and deployment focus
Usage Scenarios:
Scenario 1: Adding New API Endpoint
What loads:

✅ copilot-instructions.md (always)
✅ python-api.instructions.md (file is .py)
💬 Type /new-api-endpoint for scaffolding
Scenario 2: Debugging Failed CI
What loads:

✅ copilot-instructions.md (always)
🤖 github-actions-debug skill (semantic match)
📂 Can execute scripts, reference docs
Scenario 3: Building React Component
What loads:

✅ copilot-instructions.md (always)
✅ react-frontend.instructions.md (file is .tsx)
💬 Type /new-component to scaffold
🎭 Select "Frontend Expert" agent for session
Scenario 4: Writing Tests
What loads:

✅ copilot-instructions.md (always)
✅ testing.instructions.md (file is .test.tsx)
✅ react-frontend.instructions.md (also .tsx)
🎯 Final Decision Matrix
Ask yourself these questions:
Question	Yes → Use This	No → Consider This
Does it apply to EVERY file/interaction?	copilot-instructions.md	→ Next question
Does it apply only to specific file types/paths?	*.instructions.md with applyTo	→ Next question
Will I invoke it repeatedly as a command?	*.prompt.md as /command	→ Next question
Does it need scripts or bundled resources?	SKILL.md with resources	→ Next question
Is it a multi-step complex workflow?	SKILL.md	→ Next question
Do I want a persistent persona for the session?	Custom Agent	Use simpler option above
📝 Quick Reference Cheat Sheet
Always On
.github/copilot-instructions.md

Universal standards, tech stack, response style

File-Specific
.github/instructions/*.instructions.md

Language rules, area conventions, test patterns

On Command
.github/prompts/*.prompt.md

Scaffolding, reviews, doc generation

Smart Workflows
.github/skills/*/SKILL.md

CI/CD, security, complex processes

Personas
Custom Agents (UI)

Frontend Expert, Bug Hunter, roles