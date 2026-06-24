# FitMatch - High Fidelity Prototype

A complete, interactive HTML/CSS prototype of the **FitMatch** platform - a fitness trainer matching system that connects customers with certified personal trainers based on goals, constraints, and preferences.

## Overview

This is a fully functional high-fidelity prototype designed for classroom demonstrations, user testing, and stakeholder presentations. The prototype showcases the complete user interface and user flows for both **customers** and **trainers/providers** on the FitMatch platform.

## Features

### 🎯 Customer Features
- **Browse Trainers**: Search and filter trainers by specialization, availability, and ratings
- **Profile Management**: Set fitness goals, health constraints, and session preferences
- **Session Booking**: Schedule and manage training sessions
- **Session History**: Track completed, upcoming, and cancelled sessions
- **Reviews & Ratings**: Leave feedback for trainers and view their ratings

### 💼 Provider (Trainer) Features
- **Service Management**: Create and manage training offerings with pricing and scheduling
- **Client Management**: View and manage all active and past clients
- **Business Dashboard**: Track revenue, sessions completed, and client statistics
- **Profile Management**: Showcase certifications, experience, and specializations
- **Reviews & Feedback**: View client reviews and ratings

## File Structure

```
high-fidelity-prototype/
├── styles.css                 # Global CSS styling
├── index.html                 # Landing page
├── login.html                 # Login page (shared)
├── register.html              # Customer registration page
├── customer/
│   ├── dashboard.html         # Customer dashboard
│   ├── browse-trainers.html   # Browse and filter trainers
│   ├── my-sessions.html       # Manage sessions
│   └── profile.html           # Customer profile & settings
└── provider/
    ├── register.html          # Trainer registration page
    ├── dashboard.html         # Trainer dashboard
    ├── manage-services.html   # Manage training services
    ├── view-clients.html      # View client list
    └── profile.html           # Trainer profile & settings
```

## Quick Start

### Opening the Prototype

1. **Open index.html** - Start at the landing page to see the FitMatch homepage
2. **Navigate through the site** - Click on buttons to explore different user flows

### Key User Flows

#### Customer Flow
1. Start at `index.html` (landing page)
2. Click **"I'm a Customer"** → `register.html` (sign up)
3. Fill out the form → `customer/browse-trainers.html` (browse trainers)
4. Browse trainers and book sessions
5. Click navigation links to explore dashboard, sessions, and profile

#### Trainer Flow
1. Start at `index.html`
2. Click **"I'm a Trainer"** → `provider/register.html` (sign up)
3. Fill out professional information → `provider/dashboard.html`
4. Explore Dashboard, Manage Services, My Clients, and Profile

#### Quick Login
- Go to `login.html` from the navbar
- Demo credentials: `demo@example.com` / `demo123`
- Click "Sign In as Customer" or "Sign In as Trainer" to jump directly to dashboards

## Design Features

### Visual Design
- **Color Scheme**: Professional primary (indigo), secondary (orange), success (green)
- **Typography**: Clean, modern sans-serif fonts
- **Responsive Layout**: Grid-based layouts that adapt to different screen sizes
- **Interactive Elements**: Hover effects, buttons, forms, and tabs

### Components
- Navigation bar with branding and links
- Hero section with call-to-action
- Feature showcases with icons
- Trainer profile cards
- Session cards with status badges
- Form inputs and validation
- Dashboard stats cards
- Client/session management interfaces

### Navigation
All pages are fully interconnected with working navigation menus. You can:
- Navigate between customer and provider sections
- Move through different user journeys
- Return to the homepage from any page
- Use the navbar to jump to key sections

## Mock Data

The prototype includes realistic mock data throughout:
- **6 Sample Trainers** with specializations, ratings, and pricing
- **Active Sessions** with dates, times, and locations
- **Client Profiles** with fitness goals and health information
- **Business Statistics** showing revenue and performance
- **Reviews & Ratings** from simulated clients

## Using for Class Demonstration

### Setup
1. Open `index.html` in a web browser
2. No backend or server required - it's pure HTML/CSS
3. Use full-screen mode for best presentation experience

### Demo Script
1. **Introduction** - Show the landing page and explain the platform
2. **Customer Journey** - Register and browse trainers
3. **Provider Journey** - Register as trainer and manage services
4. **Key Features** - Highlight session booking, reviews, and management tools
5. **Mobile Responsiveness** - Demonstrate mobile layout

### Tips for Presentation
- Use the responsive design to show desktop and mobile views
- Interact with interactive elements (buttons, forms, tabs)
- Show both customer and trainer perspectives
- Use mock data to tell realistic user stories
- Highlight the clean, professional UI design

## Technologies Used

- **HTML5** - Semantic markup
- **CSS3** - Custom styling with CSS variables for theming
- **JavaScript** - Interactive elements and navigation (minimal vanilla JS)
- **No Dependencies** - Pure frontend, no frameworks or libraries

## Color Palette

- **Primary**: #4F46E5 (Indigo)
- **Primary Dark**: #4338CA
- **Secondary**: #F97316 (Orange)
- **Success**: #22C55E (Green)
- **Danger**: #EF4444 (Red)
- **Light Gray**: #F3F4F6
- **Dark Text**: #1F2937

## Future Enhancements

If developing beyond the prototype, consider:
- Backend API integration
- User authentication
- Real session booking and payment processing
- Database storage for user data
- Email notifications
- Real-time messaging
- Advanced analytics and reporting
- Mobile app versions

## Notes

- This is a **presentation prototype** - all interactions are simulated with JavaScript alerts and page navigation
- No data is persisted (no backend)
- Mock data resets on page refresh
- Interactive elements navigate to relevant pages or show alert confirmations

---

**Created for**: UNCG Summer 2026 - Team 0 Project
**Purpose**: Classroom demonstration and UI/UX showcase for FitMatch platform

