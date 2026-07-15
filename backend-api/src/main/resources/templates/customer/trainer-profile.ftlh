<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="/../styles.css">
  <title>Trainer Profile - FitMatch</title>
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

    <!-- Profile Header -->
    <section class="section" style="background: var(--primary-color); color: white;">
      <div class="container">
        <div class="grid grid-2" style="align-items: center;">
          <div>
            <img class="card-img-top" src="/trainer/picture/${trainer.id}" alt="${trainer.name!'Trainer'} profile picture" style="width: 200px; height: 200px; object-fit: cover; border-radius: 50%;" />
          </div>
          <div>
            <h1 style="font-size: 2.5rem; font-weight: 700; margin-bottom: 0.5rem; color: white;" id="trainer-name">
              ${trainer.name!'Trainer'}</h1>
            <p style="font-size: 1.2rem; opacity: 0.9; margin-bottom: 1rem; color: white;" id="trainer-specialty">
              ${trainer.specialties!'Specialized training'}</p>
            <div style="display: flex; gap: 2rem;">
              <div>
                <p style="opacity: 0.8; margin-bottom: 0.25rem;">Rating</p>
                <p style="font-size: 1.5rem; font-weight: 700;">${trainerAvgRating}</p>
              </div>
              <div>
                <p style="opacity: 0.8; margin-bottom: 0.25rem;">Reviews</p>
                <p style="font-size: 1.5rem; font-weight: 700;">${trainer.reviews?size!'0'}</p>
              </div>
              <div>
                <p style="opacity: 0.8; margin-bottom: 0.25rem;">Hourly Rate</p>
                <p style="font-size: 1.5rem; font-weight: 700;">$ ${(services[0].price)!'Contact trainer'}</p>
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

          <!-- Left Column - About & Experience -->
          <div style="grid-column: span 2;">

            <!-- About -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">About</h2>
              </div>
              <div class="card-content">
                <p id="trainer-bio" style="line-height: 1.6; color: #666;">
                  ${trainer.biography!'This trainer has not added a biography yet.'}
                </p>
              </div>
            </div>

            <!-- Certifications -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Certifications</h2>
              </div>
              <div class="card-content">
                <div style="display: flex; flex-wrap: wrap; gap: 0.75rem;">
                  <#if trainer.certifications?has_content>
                    <#list trainer.certifications?split(',') as certification>
                      <span class="badge"
                        style="background: var(--primary-light); color: var(--primary-color); padding: 0.5rem 1rem; border-radius: 20px;">${certification}</span>
                    </#list>
                    <#else>
                      <span class="badge"
                        style="background: var(--primary-light); color: var(--primary-color); padding: 0.5rem 1rem; border-radius: 20px;">Certification
                        details coming soon</span>
                  </#if>
                </div>
              </div>
            </div>

            <!-- Experience -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Experience</h2>
              </div>
              <div class="card-content">
                <p><strong>Years of Experience:</strong> ${trainer.yearsOfExperience!'0'}+ years</p>
                <p style="margin-top: 1rem; color: #666;">
                  ${trainer.biography!'This trainer has not added experience details yet.'}
                </p>
              </div>
            </div>

            <!-- Specializations -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Specializations</h2>
              </div>
              <div class="card-content">
                <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 1rem;">
                  <#if trainer.specialties?has_content>
                    <#list trainer.specialties?split(',') as specialty>
                      <div style="padding: 1rem; background: var(--light-gray); border-radius: var(--border-radius);">
                        <p style="font-weight: 600;">${specialty}</p>
                      </div>
                    </#list>
                    <#else>
                      <div style="padding: 1rem; background: var(--light-gray); border-radius: var(--border-radius);">
                        <p style="font-weight: 600;">Specialties coming soon</p>
                      </div>
                  </#if>
                </div>
              </div>
            </div>

            <!-- Client Reviews -->
            <div class="card">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Client Reviews</h2>
              </div>
              <div class="card-content">
                <#if trainer.reviews?has_content>
                  <#list trainer.reviews as review>
                    <div style="padding: 1.5rem; border-bottom: 1px solid var(--light-gray);">
                      <div
                        style="display: flex; justify-content: space-between; align-items: start; margin-bottom: 0.5rem;">
                        <p style="font-weight: 600;">${review.customer.name!'Anonymous'}</p>
                        <p style="color: var(--secondary-color); font-weight: 600;">${""?left_pad(review.rating, '⭐')}
                        </p>
                      </div>
                      <p style="font-size: 0.9rem; color: #999; margin-bottom: 0.75rem;">${review.comments!'No comment
                        provided.'}</p>
                    </div>
                  </#list>
                  <#else>
                    <p class="text-muted">No reviews yet for this trainer.</p>
                </#if>
              </div>
            </div>

          </div>

          <!-- Right Column - Booking & Availability -->
          <div>

            <!-- Services -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Services</h2>
              </div>
              <div class="card-content">
                <#if services?has_content>
                  <#list services as service>
                    <div style="margin-bottom: 1rem; padding-bottom: 1rem; border-bottom: 1px solid var(--light-gray);">
                      <p style="font-weight: 700; margin-bottom: 0.25rem;">${service.name!'Service'}</p>
                      <p style="color: #666; margin-bottom: 0.25rem;">${service.description!'No description provided.'}</p>
                      <p style="color: var(--primary-color); font-weight: 600;">$${service.price!'Contact trainer'}</p>
                    </div>
                  </#list>
                  <#else>
                    <p class="text-muted">No services listed yet.</p>
                </#if>
              </div>
            </div>

            <!-- Availability -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Availability</h2>
              </div>
              <div class="card-content">
                <#if trainer.timeslots?has_content>
                  <#list trainer.timeslots as timeslot>
                    <#if timeslot.isAvailable>
                      <div style="margin-bottom: 1rem;">
                        <p style="font-weight: 600; margin-bottom: 0.25rem;">
                          ${(timeslot.startTime?datetime.iso?string('yyyy-MM-dd hh:mm a'))}</p>
                        <p style="color: #666;">Available</p>
                      </div>
                    </#if>
                  </#list>
                  <#else>
                    <p class="text-muted">No upcoming availability listed yet.</p>
                </#if>
              </div>
            </div>

            <!-- Session Rate -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-header">
                <h2 style="font-size: 1.3rem; font-weight: 700;">Session Rates</h2>
              </div>
              <div class="card-content">
                <div style="margin-bottom: 1rem;">
                  <p style="font-size: 1.2rem; font-weight: 700; color: var(--primary-color);"> $
                    ${(services[0].price)!'Contact trainer'} <span style="font-size: 1rem; color: #666;">/session</span>
                  </p>
                </div>
                <p style="font-size: 0.85rem; color: #999;">Prices include program design & form coaching</p>
              </div>
            </div>

            <!-- Book Session Button -->
            <div class="card" style="margin-bottom: 2rem;">
              <div class="card-content">
                <a class="btn btn-primary" href="/customer/trainers/${trainer.id}/book"
                  style="width: 100%; padding: 1rem; font-size: 1.1rem; font-weight: 600; text-decoration: none; display: inline-block; text-align: center;">
                  Book a Session
                </a>
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

      function bookSession() {
        alert('Session booked! Redirecting to My Sessions page.');
        window.location.href = '/customer/sessions';
      }

      function messageTrainer(name) {
        alert('Opening message conversation with ' + name);
      }
    </script>
  </div>
</body>

</html>