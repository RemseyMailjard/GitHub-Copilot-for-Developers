# 🏦 Customer Case Summary Tool — Rabobank Copilot Labs

A starter project for the **GitHub Copilot for Developers** hands-on labs.

## What is this?

Rabobank customer service agents handle thousands of cases daily — complaints, questions, loan requests, and fraud reports. This tool helps agents:

- **View** open customer cases with status and priority
- **Summarize** case interactions into structured notes
- **Search** cases by customer, date, or category
- **Manage** case assignments and escalations

## Project structure

```
project-1-rabobank-case/
├── frontend/                        → Vanilla HTML/CSS/JS (geen build stap vereist)
│   ├── index.html                   → App shell — header, sidebar, main, footer
│   ├── css/
│   │   ├── reset.css                → Minimale CSS reset
│   │   ├── variables.css            → CSS custom properties (design tokens)
│   │   └── styles.css               → Alle component- en layoutstijlen
│   ├── js/
│   │   ├── app.js                   → Entry point — bootstrapt alles
│   │   ├── state.js                 → Reactieve state store
│   │   ├── api.js                   → Mock API-laag (retourneert Promises)
│   │   ├── data.js                  → In-memory mock data (10 cases)
│   │   ├── config.js                → Configuratie-instellingen
│   │   ├── utils/
│   │   │   └── formatters.js        → Datum-, IBAN- en prioriteithelpers
│   │   └── components/
│   │       ├── notification.js      → Lab 1 — NotificationBanner stub
│   │       ├── case-card.js         → Lab 2 — Legacy string concat (te refactoren)
│   │       ├── case-list.js         → Lab 6 — Lijstrenderer (te optimaliseren)
│   │       ├── case-detail.js       → Detailweergave met notitietijdlijn
│   │       └── admin.js             → Lab 4 — XSS-kwetsbaar adminpaneel
│   └── data/
│       └── cases.json               → Mock data als JSON (ter referentie)
│
├── backend/                         → Java 17 + Spring Boot 3
│   ├── src/main/java/nl/rabobank/casesummary/
│   │   ├── controller/              → REST-endpoints
│   │   ├── service/                 → Bedrijfslogica
│   │   ├── repository/              → Data-toegang (Spring Data JPA)
│   │   ├── model/                   → JPA-entiteiten
│   │   ├── dto/                     → Data transfer objects
│   │   └── config/                  → CORS-configuratie en DataLoader
│   └── pom.xml
│
└── .github/
    └── copilot-instructions.md      → Rabobank-codestandaarden voor Copilot
```

## Getting started

### Frontend

Geen installatie vereist. Open `frontend/index.html` via **VS Code Live Server**:

1. Installeer de extensie **Live Server** in VS Code (indien nog niet aanwezig).
2. Klik op `Go Live` in de statusbalk.
3. De app is beschikbaar op `http://localhost:5500` (of `5501`).

> Of dubbelklik direct op `index.html`. Let op: ES modules vereisen een server — gebruik Live Server voor de volledige werking.

### Backend

Vereisten: **Java 17** en **Maven**.

```bash
cd backend
mvn spring-boot:run   # http://localhost:8080
```

- REST API: `http://localhost:8080/api/v1/cases`
- H2-consolepagina: `http://localhost:8080/h2-console`

## How this connects to the labs

This project is your **sandbox**. Each lab exercise asks you to create or modify files in this project. The existing code gives Copilot context about:

- The domain (banking, customer cases, Dutch financial terms)
- Our conventions (vanilla JS components, Spring Boot patterns)
- The component structure (so Copilot generates consistent code)

**Tip:** Keep related files open in VS Code tabs while doing the labs — Copilot uses open files as context for better suggestions.

## Lab exercise mapping

| Lab | Bestand | Taak |
|-----|---------|------|
| FE L1 | `js/components/notification.js` | Implementeer `showNotification` met auto-dismiss |
| FE L2 | `js/components/case-card.js` | Refactor string-concatenatie naar DOM API (XSS-veilig) |
| FE L3 | `js/validate.js` (aan te maken) | Schrijf een formuliervalidatiemodule |
| FE L4 | `js/components/admin.js` | Herstel XSS-kwetsbaarheden in het adminpaneel |
| FE L5 | `js/utils/formatters.js` | Vind en herstel drie bugs in `calculatePriorityScore` |
| FE L6 | `js/components/case-list.js` | Voeg debounce toe en gebruik `DocumentFragment` |
| BE L1 | `controller/CaseController.java` | Nieuw REST-endpoint voor case summary CRUD |
| BE L2 | `.github/copilot-instructions.md` | Maak teamstandaarden aan voor Copilot |
| BE L3 | Architectuurdocumentatie | Genereer docs voor de case service |
| BE L4 | `service/CaseService.java` | Herstel SQL-injectie in case-zoekfunctie |
| BE L5 | Tests voor `CaseService` | Schrijf unit tests met JUnit 5 + Mockito |
| BE L6 | Maandrapportgenerator | Verbeter de rapportlogica |
