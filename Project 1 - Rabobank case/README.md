# 🏦 GitHub Copilot Workshop — Rabobank Case Summary Tool

Welcome to the **GitHub Copilot for Developers** hands-on workshop! This workshop guides you through AI-powered development using a real Rabobank internal tool: a **Customer Case Summary** application built with **Vanilla HTML/CSS/JavaScript** (frontend) and **Java 17 + Spring Boot 3** (backend).

> **Note:** No expert-level JavaScript or Java knowledge is required. GitHub Copilot generates most of the code — this is your opportunity to learn how to steer AI effectively in a professional banking context.

---

## 🎯 Workshop Objectives

By the end of this workshop, you will master:

- **Core Copilot Features**: Chat, code completions, inline chat (`Ctrl+I`), and `/slash` commands
- **Context & Instructions**: Custom instructions, prompt files, and custom chat modes
- **Code Quality**: Security review, refactoring, documentation, and unit testing
- **Advanced Workflows**: Agent mode, vision input, and performance optimization
- **Banking Context**: How to critically evaluate AI-generated code in a regulated environment

---

## 📋 Prerequisites

### Required

1. **GitHub Copilot License**: Active GitHub Copilot subscription (pro or business tier)
2. **VS Code**: Latest version with the **GitHub Copilot** and **GitHub Copilot Chat** extensions installed
3. **Live Server**: Install the [Live Server extension](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer) in VS Code
4. **Verify Copilot works**: Open a `.js` file and confirm that inline suggestions appear

### For Backend Labs (optional)

5. **Java 17+**: Verify with:
   ```bash
   java -version
   ```
6. **Maven**: Verify with:
   ```bash
   mvn -version
   ```

---

## 🚀 Quick Start

### Frontend (no build step required)

1. **Open the project in VS Code:**
   ```bash
   cd "Project 1 - Rabobank case/frontend"
   code .
   ```

2. **Start Live Server:**
   - Click the **Go Live** button in the VS Code status bar
   - Or right-click `index.html` → _Open with Live Server_

3. **Open the app:**
   Navigate to [http://localhost:5500](http://localhost:5500) in your browser

4. **Verify the app loads:**
   You should see the Rabobank Case Summary dashboard with a sidebar listing customer cases.

> The app runs entirely in the browser using **mock data** by default — no backend needed to start.

### Backend (Spring Boot — optional for real API calls)

1. **Start the backend:**
   ```bash
   cd "Project 1 - Rabobank case/backend"
   mvn spring-boot:run
   ```
   The backend will run on [http://localhost:8080](http://localhost:8080)

2. **Switch to real backend in the frontend:**
   Open `js/config.js` and set:
   ```js
   export const USE_REAL_BACKEND = true;
   ```

3. **Reload the browser** — the mode badge in the header will change from _Mock data_ to _Live backend_.

---

## 🏦 Application Overview

**Rabobank Case Summary** — a tool for customer service agents handling daily cases:

- 📋 **Case List**: Browse open cases filtered by status, priority, and category
- 🔍 **Case Detail**: View full case info including customer IBAN (masked) and interaction timeline
- 🔔 **Notifications**: Dismissable banners for success, error, warning, and info messages
- 🔐 **Admin Panel**: Search across cases by customer name, subject, or case ID
- 📡 **Dual Mode**: Works with mock data (offline) or connected to the Spring Boot backend

---

## 🏗️ Architecture

```
Browser (http://localhost:5500)
    ↓ ES module imports
app.js  →  state.js (reactive store)
    ↓
api.js  →  mock data (data.js)  OR  Spring Boot backend (localhost:8080)
    ↓
components/
  ├── case-list.js      → renders sidebar case cards
  ├── case-card.js      → individual case card element
  ├── case-detail.js    → full case detail panel
  ├── admin.js          → admin search panel
  └── notification.js   → dismissable notification banners
utils/
  └── formatters.js     → date formatting, IBAN masking, priority scoring
```

### Backend (Spring Boot)
- **Port**: 8080
- **API Base**: `/api/v1/`
- **Endpoints**: `GET /api/v1/cases`, `GET /api/v1/cases/{id}`, `POST /api/v1/cases`, `PATCH /api/v1/cases/{id}/status`

---

## 🛠️ Technology Stack

### Frontend
- **Language**: Vanilla JavaScript (ES2022 modules) — geen React, geen bundler
- **Styling**: CSS3 with custom properties (design tokens in `variables.css`)
- **Data**: Mock mode (in-memory) or real REST API via `fetch()`
- **Testing**: No test runner by default — add Jest as part of Lab 5

### Backend
- **Framework**: Java 17 + Spring Boot 3
- **Database**: H2 in-memory (development), PostgreSQL-compatible
- **API Design**: RESTful, versioned at `/api/v1/`
- **Security**: CORS restricted, parameterized queries, PII masking

---

## 📁 Project Structure

```
Project 1 - Rabobank case/
├── frontend/                        ← Vanilla HTML/CSS/JS (geen build stap)
│   ├── index.html                   ← App shell — header, sidebar, main, footer
│   ├── css/
│   │   ├── reset.css                ← Minimale CSS reset
│   │   ├── variables.css            ← CSS custom properties (design tokens)
│   │   └── styles.css               ← Alle component- en layoutstijlen
│   ├── js/
│   │   ├── app.js                   ← Entry point — bootstrapt alles
│   │   ├── state.js                 ← Tiny reactive state store
│   │   ├── api.js                   ← API-laag: mock of real backend
│   │   ├── data.js                  ← In-memory mock data (10 cases)
│   │   ├── config.js                ← USE_REAL_BACKEND toggle + API_BASE_URL
│   │   ├── utils/
│   │   │   └── formatters.js        ← Datum, IBAN, prioriteit (Lab 5: bugs!)
│   │   └── components/
│   │       ├── notification.js      ← Lab 1 — stub, nog te implementeren
│   │       ├── case-card.js         ← Lab 2 — string concat, te refactoren
│   │       ├── case-detail.js       ← Detailweergave met notitietijdlijn
│   │       ├── case-list.js         ← Lab 6 — performanceproblemen
│   │       └── admin.js             ← Lab 4 — XSS-kwetsbaarheid
│   └── data/
│       └── cases.json               ← Mock data als JSON (ter referentie)
│
├── backend/                         ← Java 17 + Spring Boot 3
│   └── src/main/java/nl/rabobank/casesummary/
│       ├── controller/              ← REST-endpoints
│       ├── service/                 ← Bedrijfslogica
│       ├── repository/              ← Spring Data JPA
│       ├── model/                   ← JPA-entiteiten
│       ├── dto/                     ← Data Transfer Objects
│       └── config/                  ← CORS + DataLoader
│
└── .github/
    └── copilot-instructions.md      ← Custom Copilot instructies voor dit project
```

---

## 🔧 Development Commands

### Frontend
```bash
# Geen installatie nodig — open index.html via Live Server in VS Code
# Of via terminal met npx:
npx serve frontend/
```

### Backend
```bash
cd backend
mvn spring-boot:run          # Start op poort 8080
mvn test                     # Draai alle tests
mvn spring-boot:run -Dspring-boot.run.arguments=--debug   # Met debug logging
```

---

# 📖 Workshop Instructions

## Task 0 — Setup & Model Selection

### 0.1 Open het project in VS Code

1. Open de map `Project 1 - Rabobank case` in VS Code
2. Start **Live Server** voor de frontend (`index.html` → _Open with Live Server_)
3. Verifieer dat de app laadt op [http://localhost:5500](http://localhost:5500)
4. Verken de app: klik cases aan, bekijk de detail-weergave, probeer het Admin-paneel

### 0.2 Kies je AI-model

Selecteer het juiste model voor de taak:

- **GPT-4.1**: Geschikt voor component-generatie, refactoring, documentatie en bugfixes
- **Claude Sonnet**: Uitstekend voor complexe code-analyse, security reviews, en agent mode-taken

**Model wisselen:**
1. Open GitHub Copilot Chat
2. Klik op de model-selector dropdown
3. Selecteer het gewenste model per taak

---

## Task 1 — Core Copilot Basics

### 1.1 Repository Exploration

**Stel je voor: je bent een nieuwe developer die vandaag start bij Rabobank. Je moet het project en de architectuur begrijpen.**

Open Copilot Chat en stel de volgende vragen (typ ze één voor één, kopieer ze niet):

- `@workspace Can you explain the architecture of this application?`
- `@workspace How do the frontend components communicate with each other?`
- `@workspace Where is the state managed and how does a case selection flow through the app?`
- `@workspace What is the difference between mock mode and real backend mode?`
- `@workspace Which files are relevant for Lab 1 about the NotificationBanner?`

### 1.2 Technologieën verkennen met `@github`

Gebruik Copilot's webzoekfuncties om meer te leren over de gebruikte technologieën:

**Instructies:**
1. Open Copilot Chat in Ask-modus
2. Stel deze vragen één voor één:
   - `@github How do ES modules work in vanilla JavaScript without a bundler?`
   - `@github What are CSS custom properties and how do I use them for theming?`
   - `@github How does the Fetch API handle error states in JavaScript?`
   - `@github What is the difference between textContent and innerHTML and when is innerHTML dangerous?`
   - `@github How do I implement a debounce function in vanilla JavaScript?`

> **Tip:** Als je Bing als referentiebron ziet in de respons, doet `@github` een live webzoekopdracht.

### 1.3 Role Prompting & Custom Instructions

Custom instructions laten jou Copilot structureel aansturen zonder elke keer dezelfde context te herhalen.

**Instructies:**
1. Open `.github/copilot-instructions.md` in de `frontend/` map
2. Lees de bestaande instructies — deze zijn al geconfigureerd voor Rabobank-conventies
3. Test de instructies door Copilot Chat te vragen:
   - `"Write a new JavaScript utility function for formatting a Dutch phone number"`
   - Controleer of Copilot automatisch JSDoc toevoegt, `const`/`let` gebruikt, en Rabobank-stijl respecteert
4. Voeg een extra instructie toe aan het bestand, bijvoorbeeld:
   ```
   - Always use Dutch variable names for domain objects (klant, zaak, iban)
   - Never use `var` — always use `const` or `let`
   ```
5. Test opnieuw — merk op hoe de suggesties veranderen

### 1.4 Code Review met Copilot

**Instructies:**
1. Open `js/components/case-card.js`
2. Selecteer alle code en klik rechtermuisknop → **Copilot** → **Review and Comment**
3. Bekijk de feedback — let op opmerkingen over `innerHTML`, string-concatenatie, en XSS
4. Vraag Copilot Chat: _"What are the top 3 problems with this code from a security and maintainability perspective?"_
5. Herhaal voor `js/utils/formatters.js` — Copilot zou de opzettelijke bugs moeten opmerken

### 1.5 Documentatie genereren met `/doc`

**Instructies:**
1. Open `js/api.js`
2. Selecteer de functie `apiFetch` (regels ~50–70)
3. Druk op `Ctrl+I` en typ: `/doc`
4. Bekijk de gegenereerde JSDoc — klopt de beschrijving van de parameters en return-waarde?
5. Genereer ook documentatie voor `simulateFetch` en `slugify`
6. Open `js/components/case-detail.js` en genereer JSDoc voor de helperfuncties `buildInfoCard` en `buildTimeline`

> **Tip:** Copilot genereert betere documentatie als de bestandsnaam en omringende code duidelijk zijn. Houd gerelateerde bestanden open in VS Code.

### 1.6 Code fixeren — implementeer de NotificationBanner

De `showNotification()` functie in `js/components/notification.js` is een lege stub. De app gebruikt hem al overal, maar hij doet niets.

**Instructies:**
1. Open `js/components/notification.js`
2. Lees de taakomschrijving in de JSDoc-commentaren bovenaan het bestand
3. Open ook `css/variables.css` en `css/styles.css` zodat Copilot de CSS-klassen als context heeft
4. Druk op `Ctrl+I` en typ:
   ```
   Implement showNotification using the DOM API.
   Append a banner to #notification-area in index.html.
   Auto-dismiss after 4 seconds for info and success variants.
   Add a close (×) button. Use the .notification and .notification--{type} CSS classes.
   Animate in with a slide-in transition.
   ```
5. Controleer of de bestaande `.notification`-klassen in `styles.css` worden gebruikt (zoek op `.notification`)
6. Test in de browser: open de browser console en roep `showNotification('Test!', 'success')` aan

### 1.7 Unit Tests genereren

`formatters.js` bevat de functie `calculatePriorityScore()` met **drie opzettelijke bugs**. Gebruik Copilot om tests te genereren die de bugs blootleggen.

**Instructies:**
1. Open `js/utils/formatters.js` en lees de commentaren bij `calculatePriorityScore`
2. Maak een nieuw bestand `js/utils/formatters.test.js`
3. Selecteer alle code in `formatters.js` en druk op `Ctrl+I` → typ: `/tests`
4. Verbeter de gegenereerde tests door Copilot Chat te vragen:
   _"/tests Generate tests specifically for calculatePriorityScore covering: (1) daysOpen = 0, (2) priority = 'critical', (3) resolved cases with status 'resolved', (4) escalated cases. Show which tests currently fail due to bugs."_
5. Voeg een **describe-blok** toe en run de tests met Node.js:
   ```bash
   node --experimental-vm-modules js/utils/formatters.test.js
   ```
   Of installeer Jest: `npm init -y && npm install --save-dev jest`
6. Voor elke falende test: selecteer de foutmelding, druk op `Ctrl+I` → `/fix`

> **Verwacht gedrag:** Drie tests zouden moeten falen — dat is correct. De tests leggen de bestaande bugs bloot.

### 1.8 Code optimaliseren

De `renderCaseList()` in `case-list.js` veroorzaakt onnodige DOM-reflows bij elke keystroke in de zoekbalk.

**Instructies:**
1. Open `js/components/case-list.js` en lees de `TODO Lab 6`-commentaren
2. Selecteer de `renderCaseList` functie
3. Vraag Copilot Chat (gebruik **Claude Sonnet** voor beste resultaat):
   _"Optimize this renderCaseList function. Replace the append-one-by-one loop with DocumentFragment to batch all DOM insertions in a single operation. Explain why this is faster."_
4. Bekijk de optimalisatie en accepteer of pas aan
5. Vraag daarna: _"Also wrap the onFilterChange callback in bindSearchInput in a debounce function with a 300ms delay. Explain what debouncing is and why it matters for a search input."_

---

## Task 2 — Intermediate Copilot Features

### 2.1 Custom Chat Modes

Custom chat modes maken gespecialiseerde AI-assistenten voor specifieke taken.

**Instructies:**
1. Maak een nieuw bestand: `.github/chatmodes/security-reviewer.chatmode.md`
2. Voeg de volgende inhoud toe:
   ```markdown
   # Security Reviewer — Rabobank Frontend

   Je bent een security reviewer voor Rabobank front-end code.

   ## Instructies
   - Zoek altijd naar XSS-kwetsbaarheden (innerHTML met user-data)
   - Controleer op PII-blootstelling (IBAN, BSN, wachtwoorden in logs of UI)
   - Verifieer of server-side autorisatie aanwezig is voor gevoelige acties
   - Markeer client-side-only authenticatiechecks als onveilig
   - Stel DOM API-alternatieven voor in plaats van innerHTML
   - Geef je bevindingen in het formaat: RISICO | LOCATIE | AANBEVELING
   ```
3. Gebruik de nieuwe mode vanuit de Copilot Chat mode-picker
4. Laat de security reviewer `js/components/admin.js` beoordelen

### 2.2 Prompt Files

**Instructies:**
1. Maak een nieuw bestand: `.github/prompts/rabobank-component.prompt.md`
2. Voeg toe:
   ```markdown
   Rabobank frontend component checklist:
   - Gebruik de DOM API (createElement, textContent) — nooit innerHTML met user-data
   - Voeg aria-label toe aan alle interactieve elementen
   - Gebruik CSS-klassen uit variables.css voor kleuren (--color-primary, --color-accent)
   - Exporteer de hoofdfunctie als named export
   - Voeg JSDoc toe met @param en @returns
   - Zorg dat elk component werkt zonder een framework (pure DOM API)
   ```
3. Voeg dit prompt-bestand toe als context in Copilot Chat bij het maken van een nieuw component
4. Vraag Copilot: _"Create a new LoadingSpinner component following the Rabobank component checklist"_

### 2.3 Chat Rollback & Prompt Editing

**Instructies:**
1. Vraag Copilot Chat om een nieuwe `filterCases(cases, query)` utility-functie te maken
2. Klik op je originele prompt in de chatgeschiedenis en bewerk hem:
   _"Also handle filtering by status and priority, and make the function case-insensitive for all string comparisons"_
3. Wissel van model (bijv. van GPT-4.1 naar Claude Sonnet) en pas opnieuw toe
4. Vergelijk de gegenereerde implementaties van beide modellen

---

## Task 3 — Copilot Agent Mode

### 3.1 Nieuwe feature bouwen met Agent Mode

GitHub Copilot's agent mode kan zelfstandig over meerdere bestanden werken, fouten herkennen en zichzelf corrigeren.

**Instructies:**
1. Open Copilot Chat → kies **Agent mode** → selecteer **Claude Sonnet**
2. Zorg dat de frontend draait via Live Server
3. Geef deze prompt mee:

```
Let's add a Statistics page to the Rabobank Case Summary tool.

1. Create a new view section in frontend/index.html (data-view="stats") with a nav button "Statistieken"
2. Create a new file frontend/js/components/stats.js that renders case statistics:
   - Total number of cases per status (open, in-progress, resolved, escalated)
   - Number of cases per category (complaint, fraud-report, loan-request, etc.)
   - Average priority score using calculatePriorityScore from utils/formatters.js
3. Display the statistics as simple cards using existing CSS classes from styles.css
4. Wire it up in app.js so the stats view is rendered when the nav button is clicked
5. All DOM manipulation must use the DOM API — no innerHTML with data
```

**💡 Agent Mode tips:**
- Wees specifiek: geef exacte bestandspaden mee
- Nummer je stappen — agent mode werkt het beste sequentieel
- Noem de bestaande CSS-klassen zodat de stijl consistent blijft
- Eindig met: _"Implement step by step and let me review each change"_

4. Bekijk de voorgestelde wijzigingen per stap en accepteer of geef feedback
5. Test in de browser: klik op "Statistieken" in de navigatie

### 3.2 Vision — component maken vanuit een schets

**Instructies:**
1. Maak een schets (of screenshot) van een gewenst UI-component, bijvoorbeeld een "Case Status Badge" met kleurcodering
2. Open Copilot Chat met **Claude Sonnet**
3. Sleep de afbeelding naar het chatvenster
4. Vraag:
   ```
   Create a Rabobank Case Status Badge component based on this image.
   Use the DOM API (no innerHTML with data), apply CSS classes from variables.css,
   and export it as renderStatusBadge(status).
   ```
5. Integreer het component in `case-card.js`

---

## Task 4 — MCP Servers

### Voorbereiding
- GitHub Personal Access Token (PAT)

**Instructies:**
1. Open Copilot Chat → kies **Agent mode**
2. Klik op het gereedschapspictogram → **Add MCP server**
3. Voeg toe: **GitHub MCP** en **Playwright MCP**

### 4.1 End-to-end testen met Playwright MCP

**Doel:** Automatisch testen van de Rabobank-app zonder handmatig testcode te schrijven.

**Instructies:**
1. Zorg dat de app draait: Live Server op poort 5500
2. Vraag in Agent mode met Playwright MCP ingeschakeld:
   ```
   Using Playwright MCP, test the complete user flow of the Rabobank Case Summary app:
   1. Navigate to http://localhost:5500
   2. Verify the case list sidebar is visible and contains at least one case
   3. Click the first case card and verify the detail panel opens
   4. Verify the customer IBAN is masked (e.g. NL** **** **** XXXX)
   5. Click the Admin navigation button and verify the search input appears
   6. Type a customer name in the admin search and verify results appear
   7. Take a screenshot of each step
   8. Generate a test report with pass/fail results
   ```

### 4.2 GitHub Issue afhandelen

1. Maak een nieuw issue in de GitHub-repository:
   - **Titel**: `Add dark mode toggle for the Case Summary dashboard`
   - **Beschrijving**: `Add a dark mode toggle button to the app header. It should switch between light and dark theme using CSS custom properties defined in variables.css. The preference should be stored in localStorage.`
2. Wijs het issue toe aan **Copilot**
3. Bekijk de automatisch gegenereerde PR en review de wijzigingen

---

## Task 5 — GitHub Copilot CLI

### 5.1 Installatie

```bash
npm install -g @githubnext/github-copilot-cli
copilot --version
```

### 5.2 Hands-on: Verbeter de Rabobank app

1. **Start Copilot CLI vanuit de projectmap:**
   ```bash
   cd "Project 1 - Rabobank case/frontend"
   copilot
   ```

2. **Oefening 1 — Priority filter toevoegen:**
   ```
   Add a priority filter dropdown to the case list sidebar in index.html.
   It should filter cases by priority (all, low, medium, high, critical).
   Wire it up in app.js using the existing state management pattern in state.js.
   The filter should work independently from the existing status filter.
   ```

3. **Oefening 2 — Export functionaliteit:**
   ```
   Add an "Export to CSV" button to the admin panel in admin.js.
   When clicked, it should export all currently visible search results
   as a CSV file with columns: id, customerName, category, status, priority, createdAt.
   Mask the IBAN column using maskIBAN from formatters.js.
   ```

---

## 🔒 Security Guidelines

In een bancaire omgeving gelden hoge veiligheidsnormen. Gebruik deze checklist bij elke Copilot-gegenereerde code:

| Risico | Wat te controleren |
|--------|--------------------|
| **XSS** | Gebruik nooit `innerHTML` met user-data — gebruik `textContent` of de DOM API |
| **PII-blootstelling** | IBAN en BSN moeten worden gemaskeerd in de UI en logs |
| **Client-side auth** | UI-hides zijn cosmetisch — autorisatie hoort server-side |
| **Injection** | Zorg dat zoektermen niet als HTML of SQL worden geëvalueerd |
| **Gevoelige data in localStorage** | Sla geen rollen, tokens, of PII op in localStorage |

> Copilot begrijpt jouw autorisatiemodel niet. Beoordeel altijd gegenereerde beveiligingscode kritisch.

---

## 📚 Reference: Lab ↔ Bestand Mapping

| Lab | Bestand | Leerdoel |
|-----|---------|----------|
| FE Lab 1 | `js/components/notification.js` | Component genereren & DOM API |
| FE Lab 2 | `js/components/case-card.js` | Refactoring: string concat → DOM API |
| FE Lab 3 | `js/components/case-detail.js`, `js/utils/formatters.js` | Code review & documentatie |
| FE Lab 4 | `js/components/admin.js` | XSS-detectie & veilige DOM-manipulatie |
| FE Lab 5 | `js/utils/formatters.js` (`calculatePriorityScore`) | Unit tests & bugfixing |
| FE Lab 6 | `js/components/case-list.js` | Performance: DocumentFragment + debounce |

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
