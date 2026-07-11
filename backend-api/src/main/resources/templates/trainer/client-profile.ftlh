<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="/../styles.css">
  <title>Client Profile - FitMatch</title>
</head>

<body>
  <div class="container">
    <!-- Navigation -->
    <nav>
      <div class="navbar">
        <a href="/" class="navbar-brand"> <img src="/../images/logo.png" width="120" height="92"></a>
        <ul class="nav-links">
          <li><a href="/trainer/dashboard">Dashboard</a></li>
          <li><a href="/trainer/services">My Services</a></li>
          <li><a href="/trainer/clients">My Clients</a></li>
          <li><a href="/trainer/profile">Profile</a></li>
          <li><a href="/trainer/login" class="btn btn-outline">Logout</a></li>
        </ul>
      </div>
    </nav>

    <!-- Profile Header -->
    <section class="section" style="background: var(--primary-color); color: white;">
      <div class="container">
        <div class="grid grid-2" style="align-items: center;">
          <div>
            <div
              style="width: 150px; height: 150px; background: rgba(255,255,255,0.15); border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 3rem; margin-bottom: 1.5rem; color: white;">
              C</div>
          </div>
          <div>
            <h1 style="font-size: 2.5rem; font-weight: 700; margin-bottom: 0.5rem; color: white;">${client.name}</h1>
            <p style="font-size: 1.2rem; opacity: 0.9; margin-bottom: 1rem; color: white;">
              ${client.accountStatus?cap_first} Client</p>
            <div style="display: flex; gap: 2rem;">
              <div>
                <p style="opacity: 0.8; margin-bottom: 0.25rem;">Total Sessions</p>
                <p style="font-size: 1.5rem; font-weight: 700;">${sessionsCount}</p>
              </div>
              <div>
                <p style="opacity: 0.8; margin-bottom: 0.25rem;">Client Rating</p>
                <p style="font-size: 1.5rem; font-weight: 700;">${clientRating?string("0.0")} / 5.0</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Main Content -->
    <section class="section">
      <div class="container">
        <div class="grid grid-3" style="gap: 2rem;">

          <!-- Left Column - Client Info -->
          <div style="grid-column: span 2;">

            <!-- Contact Information -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Contact Information</h2>
              </div>
              <div class="card-content">
                <div style="margin-bottom: 1rem;">
                  <p style="color: #999; margin-bottom: 0.25rem;">Email</p>
                  <p style="font-weight: 600;">${client.email}</p>
                </div>
                <div style="margin-bottom: 1rem;">
                  <p style="color: #999; margin-bottom: 0.25rem;">Phone</p>
                  <p style="font-weight: 600;">${client.phoneNumber!"Not provided"}</p>
                </div>
                <div>
                  <p style="color: #999; margin-bottom: 0.25rem;">Preferred Location</p>
                  <p style="font-weight: 600;">${client.preferredLocation!"Not set"}</p>
                </div>
              </div>
            </div>

            <!-- Fitness Profile -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Fitness Profile</h2>
              </div>
              <div class="card-content">
                <div style="margin-bottom: 1.5rem;">
                  <p style="color: #999; margin-bottom: 0.5rem;">Primary Goal</p>
                  <p style="font-weight: 600; font-size: 1.1rem;">${client.fitnessGoals!"Not set"}</p>
                </div>
                <div style="margin-bottom: 1.5rem;">
                  <p style="color: #999; margin-bottom: 0.5rem;">Fitness Level</p>
                  <p style="font-weight: 600; font-size: 1.1rem;">${client.fitnessLevel!"Not set"}</p>
                </div>
              </div>
            </div>

            <!-- Health & Constraints -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Health & Constraints</h2>
              </div>
              <div class="card-content">
                <div style="margin-bottom: 1.5rem;">
                  <p style="color: #999; margin-bottom: 0.5rem;">Injuries / Conditions</p>
                  <p style="font-weight: 600;">${client.injuriesOrHealthConcerns!"None reported"}</p>
                </div>
                <div>
                </div>
              </div>
            </div>

            <!-- Session History -->
            <div class="card">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Sessions</h2>
              </div>
              <div class="card-content">

                <#if sessions?has_content>
                  <#list sessions as s>
                    <div style="padding: 1.5rem; border: 1px solid var(--light-gray);">
                      <div
                        style="display: flex; justify-content: space-between; align-items: start; margin-bottom: 0.5rem;">
                        <p style="font-weight: 600;">${s.trainingService.name}</p>
                        <p style="font-weight: 600; color: var(--primary-dark);">
                          <#if s.status?has_content>
                            ${s.status}
                          </#if>
                        </p>
                        <#if s.status=="Scheduled" && s.timeslot.startTime?datetime.iso?long < .now?long>
                          <a class="btn btn-outline" style="margin-top: 1rem; "
                            href="/trainer/sessions/${s.id}/complete">Mark As Complete</a>
                        </#if>

                      </div>
                      <p style="font-size: 0.9rem; color: #999; margin-bottom: 0.5rem;">
                        ${(s.timeslot.startTime?datetime.iso?string('yyyy-MM-dd hh:mm a'))!"TBD"} -
                        ${(s.timeslot.endTime?datetime.iso?string('yyyy-MM-dd hh:mm a'))!"TBD"}</p>
                      <#if s.status == "Scheduled">
                        <p style="color: #444; font-size: 0.95rem; margin-bottom: 0.5rem;"><strong>Location:</strong> ${s.location!"To be confirmed"}</p>
                        <form method="post" action="/trainer/sessions/${s.id}/location">
                          <div style="display: flex; gap: 0.5rem; align-items: center; margin-bottom: 0.75rem;">
                            <input type="text" name="location" value="${s.location!''}" placeholder="Update location"
                              style="flex: 1; padding: 0.65rem; border: 1px solid #ccc; border-radius: 0.4rem;" />
                            <button type="submit" class="btn btn-outline" style="white-space: nowrap;">Update</button>
                          </div>
                        </form>
                      </#if>
                      <p style="color: #666; font-size: 0.9rem;">${s.notes!"No notes"}</p>
                      <#if s.status=="Completed">
                        <div style="margin-top: 1rem;">
                          <form method="post" action="/trainer/sessions/${s.id}/notes">
                            <div style="margin-bottom: 0.5rem;">
                              <label for="notes-${s.id}"
                                style="font-weight: 600; color: #999; display:block; margin-bottom:0.25rem;">Session
                                Notes</label>
                              <textarea id="notes-${s.id}" name="notes" rows="3"
                                style="width:100%; padding:0.5rem;">${s.notes!""}</textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Save Notes</button>
                          </form>
                        </div>
                      </#if>

                    </div>
                  </#list>
                  <#else>
                    <p>No sessions yet for this client.</p>
                </#if>
              </div>
            </div>
          </div>


          <!-- Right Column - Actions & Notes -->
          <div>

            <!-- Next Session -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Next Session</h2>
              </div>
              <div class="card-content">
                <div
                  style="background: var(--primary-dark); color: white; padding: 1.5rem; border-radius: var(--border-radius);">
                  <p style="opacity: 0.9; margin-bottom: 0.5rem; font-weight: 600;">Upcoming Session</p>
                  <#if nextSession??>
                    <p style="font-weight: 700; font-size: 1.2rem; margin-bottom: 1rem;">
                      ${nextSession.timeslot.startTime}</p>
                    <p style="opacity: 0.8; font-size: 0.9rem;">${nextSession.trainingService.name}</p>

                    <#else>
                      <p style="font-weight: 700; font-size: 1.2rem; margin-bottom: 1rem;">No upcoming sessions</p>
                  </#if>
                </div>
              </div>
            </div>

            <!-- Client Status -->
            <div class="card">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Client Status</h2>
              </div>
              <div class="card-content">
                <p style="color: #999; margin-bottom: 0.5rem;">Current Status</p>
                <p style="font-weight: 600; font-size: 1.1rem;">
                  ${client.accountStatus?cap_first}
                </p>
              </div>
            </div>

          </div>

        </div>
      </div>
    </section>

    <!-- Footer -->
    <footer>
      <p>&copy; 2026 FitMatch. All rights reserved.</p>
    </footer>

    <script>
      function scheduleSession() {
        alert('Opening scheduling interface');
      }

      function messageClient() {
        alert('Opening message conversation ');
      }

      function saveNotes() {
        alert('Notes saved ');
      }
    </script>
  </div>
</body>

</html>