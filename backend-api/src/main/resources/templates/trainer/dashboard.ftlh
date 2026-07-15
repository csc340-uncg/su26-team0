<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="/../styles.css">
  <title>Trainer Dashboard - FitMatch</title>
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

    <!-- Dashboard Header -->
    <section class="section">
      <div class="container">
        <div class="dashboard-header">
          <div class="dashboard-title">Welcome Back, ${trainer.name!'Trainer'}!</div>
          <div class="dashboard-subtitle">Manage your training business and serve your clients</div>
        </div>

        <!-- Stats Cards -->
        <div class="dashboard-grid">
          <div class="stat-card">
            <div class="stat-number">${services?size!'0'}</div>
            <div class="stat-label">Active Services</div>
          </div>
          <div class="stat-card">
            <div class="stat-number">${trainerStats.totalTrainerSessions()}</div>
            <div class="stat-label">Sessions</div>
          </div>
          <div class="stat-card">
            <div class="stat-number">${trainerStats.totalTrainerReviews()}</div>
            <div class="stat-label">Reviews</div>
          </div>
          <div class="stat-card">
            <div class="stat-number">${trainer.yearsOfExperience} yrs</div>
            <div class="stat-label">Experience</div>
          </div>
        </div>
      </div>
    </section>

    <!-- Upcoming Sessions -->
    <section class="section">
      <div class="container">
        <h2 style="font-size: 1.8rem; font-weight: 700; margin-bottom: 1.5rem;">Your Services</h2>

        <#if services?has_content>
          <#list services as service>
            <div class="session-item">
              <div class="session-header">
                <div>
                  <div class="session-title">${service.name!'Training Service'}</div>
                  <span class="session-status status-upcoming">${service.status!'Active'}</span>
                </div>
              </div>
              <div class="session-details">
                <div class="detail-item"><span class="detail-label">Category:</span> ${service.category!'General'}</div>
                <div class="detail-item"><span class="detail-label">Price:$ </span> ${service.price!'Contact for pricing'}</div>
                <div class="detail-item"><span class="detail-label">Description:</span> ${service.description!'No description yet.'}</div>
              </div>
            </div>
          </#list>
        <#else>
          <p class="text-muted">You have not added any services yet.</p>
        </#if>
      </div>
    </section>

    <!-- Business Stats -->
    <section class="section section-light">
      <div class="container">
        <h2 style="font-size: 1.8rem; font-weight: 700; margin-bottom: 1.5rem;">Account Performance</h2>
        <div class="grid grid-4">
          <div class="card">
            <div class="card-content text-center">
              <div style="font-size: 2rem; color: var(--primary-color); font-weight: 700; margin-bottom: 0.5rem;">$ ${trainerStats.totalTrainerRevenue()!0}
              </div>
              <div class="text-muted">Revenue</div>
            </div>
          </div>
          <div class="card">
            <div class="card-content text-center">
              <div style="font-size: 2rem; color: var(--secondary-color); font-weight: 700; margin-bottom: 0.5rem;">${trainerStats.totalTrainerSessions()!0}
              </div>
              <div class="text-muted">Sessions</div>
            </div>
          </div>
          <div class="card">
            <div class="card-content text-center">
              <div style="font-size: 2rem; color: var(--success-color); font-weight: 700; margin-bottom: 0.5rem;">${trainerStats.totalTrainerCustomers()!0}
              </div>
              <div class="text-muted">New Clients</div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Quick Actions -->
    <section class="section">
      <div class="container">
        <h2 style="font-size: 1.8rem; font-weight: 700; margin-bottom: 1.5rem;">Quick Actions</h2>
        <div class="grid grid-3">
          <a href="/trainer/services" class="card"
            style="text-decoration: none; color: inherit; text-align: center;">
            <div class="card-content">
              <div style="font-size: 2.5rem; margin-bottom: 1rem;">1</div>
              <h3 style="font-weight: 700; margin-bottom: 0.5rem;">Manage Services</h3>
              <p class="text-muted">Update your training offerings and availability</p>
            </div>
          </a>
          <a href="/trainer/clients" class="card" style="text-decoration: none; color: inherit; text-align: center;">
            <div class="card-content">
              <div style="font-size: 2.5rem; margin-bottom: 1rem;">2</div>
              <h3 style="font-weight: 700; margin-bottom: 0.5rem;">My Clients</h3>
              <p class="text-muted">View and manage all your clients</p>
            </div>
          </a>
          <a href="/trainer/profile" class="card" style="text-decoration: none; color: inherit; text-align: center;">
            <div class="card-content">
              <div style="font-size: 2.5rem; margin-bottom: 1rem;">3</div>
              <h3 style="font-weight: 700; margin-bottom: 0.5rem;">My Profile</h3>
              <p class="text-muted">Update your professional information</p>
            </div>
          </a>
        </div>
      </div>
    </section>


    <!-- Recent Reviews -->
    <section class="section section-light">
      <div class="container">
        <h2 style="font-size: 1.8rem; font-weight: 700; margin-bottom: 1.5rem;">Recent Client Reviews</h2>

        <#if reviews?has_content>
          <#list reviews as review>
            <div class="card" style="margin-bottom: 1rem;">
              <div class="card-content">
                <div style="display: flex; justify-content: space-between; align-items: start; margin-bottom: 1rem;">
                  <div>
                    <p style="font-weight: 700; margin-bottom: 0.25rem;">${review.customer.name!"Anonymous"}</p>
                    <p class="text-muted" style="font-size: 0.9rem;">${""?left_pad(review.rating, '⭐')} ${review.rating!0}/5</p>
                  </div>
                </div>
                <p style="margin: 0 0 0.75rem;">${review.comments!""}</p>
                <#if review.replyText?has_content>
                  <div style="background: #f8f9f9; border-left: 4px solid var(--primary-color); padding: 1rem; border-radius: 0.5rem; margin-bottom: 1rem;">
                    <p style="font-weight: 700; margin-bottom: 0.5rem;">Your reply</p>
                    <p style="margin: 0;">${review.replyText}</p>
                  </div>
                </#if>
                <form method="post" action="/trainer/reviews/${review.id}/reply">
                  <textarea name="replyText" rows="3" placeholder="Reply to this review"
                    style="width:100%; padding:0.75rem; border:1px solid #ccc; border-radius:0.5rem; margin-bottom:0.75rem;">${review.replyText!""}</textarea>
                  <button type="submit" class="btn btn-primary">Post Reply</button>
                </form>
              </div>
            </div>
          </#list>
        <#else>
          <p class="text-muted">No reviews yet.</p>
        </#if>
      </div>
    </section>

    <!-- Footer -->
    <footer>
      <p>&copy; 2026 FitMatch. All rights reserved.</p>
    </footer>
  </div>
</body>

</html>