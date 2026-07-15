**Project Name:** FitMatch \
**Version:** 1.0 \
**Date:** 2026-07-15 \
**Purpose:** Test plan for the FitMatch application

## Actors

- Provider P: Trainer, Coach, or Fitness Professional
- Customer C: User seeking fitness services
- Service S: Training Service offered by a Provider

## Use Cases

#### 1. Provider US-5 - Create and update trainer profile, US-6 - Define services and pricing.

1. Provider P1 logs in and creates a profile with credentials.
2. P1 updates their profile to add specialties and availability.
3. P1 creates a new service S1 with searchable criteria (title, category, description).
4. P1 logs out.

#### 2. Customer: US‑CUST‑001 — Register & manage profile

1. Customer C1 logs in for the first time and creates a profile.
2. C1 edits their profile to add preferences.
3. C1 exits.

#### 3. Customer: US‑CUST‑003 — Book a training session, US‑CUST‑004 — Write a review after a session

1. Customer C2 logs in for the first time and creates a profile.
2. C2 edits their profile to add preferences.
3. Customer C2 selects a recommended provider and books a session for service S1 with provider P1 at a specific time.
4. Customer C2 completes a training session with the provider.
5. C2 writes a review R1 for the session and submits it.
6. The review R1 is saved and visible to other customers.

#### 4. Customer: US‑CUST‑002 — Search for providers

1. Customer C1 logs in and navigates to the search page.
2. C1 enters search criteria (e.g., location, service type).
3. C1 views the list of providers matching the criteria.
4. C1 selects a provider P1 to view their profile, service S1, and review R1.
5. C1 books a session with provider P1 for service S1 at a specific time.

#### 5. Provider: US-7 - Respond to reviews

1. Provider P1 logs in and navigates to the reviews section.
2. P1 selects review R1 and responds to it.

#### 6. Provider: US-8 - View customer statistics

1. Provider P1 logs in and navigates to the statistics dashboard.
2. P1 views aggregated data on customer engagement, session attendance, and feedback.
3. P1 uses the insights to adjust their services and offerings.
4. P1 logs out.

## CROSS-CUTTING TEST SCENARIOS (Non-Functional Requirements)

### Performance Requirements

**Scenario P1: Discover page response time < 2 seconds**

- **Setup:** Server under typical load
- **Steps:**
  1. Measure response time for "Browse" page load with 5 active providers, 10+ services
  2. Repeat 10 times
- **Expected Outcome:** 95% of requests ≤ 2 seconds

**Scenario P2:** Trainer profile details load < 1.5 seconds

- **Setup:** Server under typical load
- **Steps:**
  1. Measure response time for loading a trainer profile with 5 services and 10 reviews
  2. Repeat 10 times
- **Expected Outcome:** 95% of requests ≤ 1.5 seconds

### Security & Privacy Requirements

**Scenario S1:** Role based access control for trainer and customer data

- **Setup:** Customer tries to access trainer-only data and vice versa
- **Steps:**
  1. Customer logs in.
  2. Attempts to navigate to "/trainer/dashboard" and access trainer-only data.
  3. Observe the response.
- **Expected Outcome:**
  - Access is denied.
  - Customer is redirected to an error page or login page.
  - No sensitive trainer data is exposed to the customer.

**Scenario S2:** Farmer cannot edit customer's review

- **Setup:** Trainer logs in, customer has written a critical review for a session.
- **Steps:**
  1. Trainer logs in.
  2. Views the customer's review.
  3. Attempts to edit or delete the customer's review (via URL or API).
- **Expected Outcome:**
  - Access is denied.
  - Trainer is redirected to an error page or login page.
  - No sensitive customer data is exposed to the trainer.

### Usability Requirements

**Scenario U1:** New customer completes registration and books a session within 5 minutes

- **Setup:** New customer participates in hallway testing with a facilitator observing the process.
- **Steps:**
  1. New customer logs in for the first time and creates a profile.
  2. Customer edits their profile to add preferences.
  3. Customer browses available services and selects one.
  4. Customer books a session with a provider.
  5. Record the time taken from login to booking confirmation.
- **Expected Outcome:**
  - Time to complete registration and book a session is ≤ 5 minutes.
