<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="/../styles.css">
  <title>My Sessions - FitMatch</title>
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

    <!-- Header -->
    <section class="section" style="background: var(--light-gray);">
      <div class="container">
        <h1 style="font-size: 2rem; font-weight: 700;">My Training Sessions</h1>
        <p class="text-muted">Track all your past, current, and upcoming training sessions</p>
      </div>
    </section>

    <!-- Tabs -->
    <section class="section">
      <div class="container">
        <#assign upcomingCount=0>
          <#assign completedCount=0>
            <#list sessions as session>
              <#assign status=(session.status!'Scheduled')?lower_case>
                <#if status=='completed'>
                  <#assign completedCount=completedCount + 1>
                    <#else>
                      <#assign upcomingCount=upcomingCount + 1>
                </#if>
            </#list>

            <div style="display: flex; gap: 1rem; margin-bottom: 2rem; border-bottom: 2px solid var(--medium-gray);">
              <button onclick="showTab('upcoming')" id="tab-upcoming" class="btn"
                style="background: none; color: var(--primary-color); border: none; border-bottom: 3px solid var(--primary-color); padding: 0.75rem 0; border-radius: 0;">
                Upcoming (${upcomingCount})
              </button>
              <button onclick="showTab('completed')" id="tab-completed" class="btn"
                style="background: none; color: var(--text-light); border: none; padding: 0.75rem 0; border-radius: 0;">
                Completed (${completedCount})
              </button>
            </div>

            <div id="upcoming-tab">
              <#assign hasUpcoming=false>
                <#list sessions as session>
                  <#assign status=(session.status!'Scheduled')?lower_case>
                    <#if status !='completed' && status !='cancelled'>
                      <#assign hasUpcoming=true>
                        <div class="session-item">
                          <div class="session-header">
                            <div>
                              <div class="session-title">${session.trainingService.name!"Training Session"} with
                                ${session.trainingService.trainer.name!"Trainer"}</div>
                              <span class="session-status status-upcoming">${session.status!'Scheduled'}</span>
                            </div>
                          </div>
                          <div class="session-details">
                            <div class="detail-item"><span class="detail-label">Date & Time:</span>
                              ${(session.timeslot.startTime?datetime.iso?string('yyyy-MM-dd hh:mm a'))!'TBD'}</div>
                            <div class="detail-item"><span class="detail-label">Location:</span> ${session.location!"To
                              be confirmed"}</div>
                            <div class="detail-item"><span class="detail-label">Price:$</span>
                              ${session.trainingService.price!"TBD"}</div>
                          </div>
                          <div style="display: flex; gap: 1rem; margin-top: 1rem;">
                            <form action="/customer/sessions/${session.id}/cancel" method="post"
                              style="display: inline;">
                              <button class="btn btn-outline" type="submit"
                                style="color: var(--danger-color); border-color: var(--danger-color);">Cancel
                                Session</button>
                            </form>
                          </div>
                        </div>
                    </#if>
                </#list>
                <#if !hasUpcoming>
                  <p class="text-muted">You do not have any upcoming sessions yet. <a href="/customer/browse"> Browse
                      Trainers!</a></p>
                </#if>
            </div>

            <div id="completed-tab" class="hidden">
              <#assign hasCompleted=false>
                <#list sessions as session>
                  <#assign status=(session.status!'Scheduled')?lower_case>
                    <#if status=='completed'>
                      <#assign hasCompleted=true>
                        <div class="session-item" style="border-left-color: var(--success-color);">
                          <div class="session-header">
                            <div>
                              <div class="session-title">${session.trainingService.name!"Training Session"} with
                                ${session.trainingService.trainer.name!"Trainer"}</div>
                              <span class="session-status status-completed">${session.status!'Completed'}</span>
                            </div>
                          </div>
                          <div class="session-details">
                            <div class="detail-item"><span class="detail-label">Date & Time:</span>
                              ${(session.timeslot.startTime?datetime.iso?string('yyyy-MM-dd hh:mm a'))!"TBD"}</div>
                            <div class="detail-item"><span class="detail-label">Location:</span> ${session.location!"To
                              be confirmed"}</div>
                            <div class="detail-item"><span class="detail-label">Price:</span>
                              ${session.trainingService.price!"TBD"}</div>
                          </div>
                          <div style="display: flex; gap: 1rem; margin-top: 1rem;">
                            <a class="btn btn-outline"
                              href="/customer/trainers/${session.trainingService.trainer.id}/book">Book Again</a>
                            <a class="btn btn-primary"
                              href="/customer/trainers/${session.trainingService.trainer.id}/review">Review Your Trainer</a>
                          </div>
                        </div>
                    </#if>
                </#list>
                <#if !hasCompleted>
                  <p class="text-muted">You do not have any completed sessions yet.</p>
                </#if>

                <div class="session-item" style="margin-top: 2rem; border-left-color: var(--primary-color);">
                  <div class="session-header">
                    <div>
                      <div class="session-title">My Reviews</div>
                      <span class="session-status status-upcoming">Your feedback</span>
                    </div>
                  </div>
                  <#if reviews?? && reviews?size gt 0>
                    <#list reviews as review>
                      <div style="margin-top: 1rem; padding: 0.75rem 0; border-top: 1px solid var(--medium-gray);">
                        <div style="font-weight: 700; margin-bottom: 0.25rem;">${review.trainer.name!"Trainer"}</div>
                        <div style="color: var(--primary-color); margin-bottom: 0.25rem;">Rating: ${review.rating}/5</div>
                        <div class="text-muted">${review.comments!"No comments provided."}</div>
                      </div>
                    </#list>
                  <#else>
                    <p class="text-muted" style="margin-top: 1rem;">You have not submitted any reviews yet.</p>
                  </#if>
                </div>
            </div>
      </div>
    </section>

    <!-- Footer -->
    <footer>
      <p>&copy; 2026 FitMatch. All rights reserved.</p>
    </footer>

    <script>
      function showTab(tab) {
        // Hide all tabs
        document.getElementById('upcoming-tab').classList.add('hidden');
        document.getElementById('completed-tab').classList.add('hidden');

        // Remove active styling
        document.getElementById('tab-upcoming').style.color = 'var(--text-light)';
        document.getElementById('tab-upcoming').style.borderBottom = 'none';
        document.getElementById('tab-completed').style.color = 'var(--text-light)';
        document.getElementById('tab-completed').style.borderBottom = 'none';

        // Show selected tab and set active styling
        if (tab === 'upcoming') {
          document.getElementById('upcoming-tab').classList.remove('hidden');
          document.getElementById('tab-upcoming').style.color = 'var(--primary-color)';
          document.getElementById('tab-upcoming').style.borderBottom = '3px solid var(--primary-color)';
        } else if (tab === 'completed') {
          document.getElementById('completed-tab').classList.remove('hidden');
          document.getElementById('tab-completed').style.color = 'var(--primary-color)';
          document.getElementById('tab-completed').style.borderBottom = '3px solid var(--primary-color)';
        }
      }
    </script>
  </div>
</body>

</html>