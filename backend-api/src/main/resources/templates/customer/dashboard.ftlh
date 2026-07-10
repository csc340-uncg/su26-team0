<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="/../styles.css">
  <title>Customer Dashboard - FitMatch</title>
</head>

<body>
  <div class="container">
    <!-- Navigation -->
    <nav>
      <div class="navbar">
        <a href="/" class="navbar-brand"> <img src="/../images/logo.png" width="120" height="92"></a>

        <ul class="nav-links">
          <li><a href="/customer/browse" style="color: var(--primary-color); font-weight: 700;">Browse Trainers</a>
          </li>
          <li><a href="/customer/sessions">My Sessions</a></li>
          <li><a href="/customer/profile">My Profile</a></li>
          <li><a href="/customer/login" class="btn btn-outline">Logout</a></li>
        </ul>
      </div>
    </nav>

    <!-- Dashboard Header -->
    <section class="section">
      <div class="container">
        <div class="dashboard-header">
          <div class="dashboard-title">Welcome, ${customer.name!'Guest'}!</div>
          <div class="dashboard-subtitle">Track your fitness journey and manage your training sessions</div>
        </div>

        <!-- Stats Cards -->
        <div class="dashboard-grid">
          <div class="stat-card">
            <div class="stat-number">${trainers?size!'0'}</div>
            <div class="stat-label">Active Trainers</div>
          </div>
          <div class="stat-card">
            <div class="stat-number">${sessions?filter(s -> s.status?has_content && s.status == 'Completed')?size!'0'}
            </div>
            <div class="stat-label">Sessions Completed</div>
          </div>
          <div class="stat-card">
            <div class="stat-number">${sessions?filter(s -> s.status?has_content && s.status == 'Scheduled')?size!'0'}
            </div>
            <div class="stat-label">Upcoming Sessions</div>
          </div>
        </div>
      </div>
    </section>

    <!-- Upcoming Sessions -->
    <section class="section">
      <div class="container">
        <h2 style="font-size: 1.8rem; font-weight: 700; margin-bottom: 1.5rem;">Upcoming Sessions</h2>

        <#if upcomingSessions?has_content>
          <#list upcomingSessions as session>
            <div class="session-item">
              <div class="session-header">
                <div>
                  <div class="session-title">${session.trainingService.name!'Training Session'} with
                    ${session.trainingService.trainer.name!'Your Trainer'}</div>
                  <span class="session-status status-upcoming">${session.status!'Scheduled'}</span>
                </div>
              </div>
              <div class="session-details">
                <div class="detail-item"><span class="detail-label">Date & Time:</span>
                  ${(session.timeslot.startTime?datetime.iso?string('yyyy-MM-dd hh:mm a'))!'TBD'}</div>
                <div class="detail-item"><span class="detail-label">Level:</span> ${session.level!'Flexible'}</div>
                <div class="detail-item"><span class="detail-label">Location:</span> ${session.location!'To be
                  announced'}</div>
                <div class="detail-item"><span class="detail-label">Price:$</span>
                  ${session.trainingService.price!'Contact trainer'}</div>
              </div>
              <div style="display: flex; gap: 1rem; margin-top: 1rem;">
                <form action="/customer/sessions/${session.id}/cancel" method="post" style="display: inline;">
                  <button class="btn btn-outline" type="submit"
                    style="color: var(--danger-color); border-color: var(--danger-color);">Cancel Session</button>
                </form>
              </div>
            </div>
          </#list>
          <#else>
            <p class="text-muted">No upcoming sessions in the future. Browse trainers to book a new appointment.</p>
        </#if>
      </div>
    </section>

    <!-- Quick Actions -->
    <section class="section section-light">
      <div class="container">
        <h2 style="font-size: 1.8rem; font-weight: 700; margin-bottom: 1.5rem;">Quick Actions</h2>
        <div class="grid grid-3">
          <a href="/customer/browse" class="card" style="text-decoration: none; color: inherit; text-align: center;">
            <div class="card-content">
              <h3 style="font-weight: 700; margin-bottom: 0.5rem;">Browse Trainers</h3>
              <p class="text-muted">Find and book new trainers based on your goals</p>
            </div>
          </a>
          <a href="/customer/sessions" class="card" style="text-decoration: none; color: inherit; text-align: center;">
            <div class="card-content">
              <h3 style="font-weight: 700; margin-bottom: 0.5rem;">Session History</h3>
              <p class="text-muted">View all past and upcoming sessions</p>
            </div>
          </a>
          <a href="/customer/profile" class="card" style="text-decoration: none; color: inherit; text-align: center;">
            <div class="card-content">
              <h3 style="font-weight: 700; margin-bottom: 0.5rem;">My Profile</h3>
              <p class="text-muted">Update your goals and preferences</p>
            </div>
          </a>
        </div>
      </div>
    </section>

    <!-- Footer -->
    <footer>
      <p>&copy; 2026 FitMatch. All rights reserved.</p>
    </footer>
  </div>
</body>

</html>