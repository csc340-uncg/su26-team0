<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="/../styles.css">
  <title>My Clients - FitMatch</title>
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
          <li><a href="/trainer/clients" style="color: var(--primary-color); font-weight: 700;">My Clients</a></li>
          <li><a href="/trainer/profile">Profile</a></li>
          <li><a href="/trainer/login" class="btn btn-outline">Logout</a></li>
        </ul>
      </div>
    </nav>

    <!-- Header -->
    <section class="section" style="background: var(--light-gray);">
      <div class="container">
        <h1 style="font-size: 2rem; font-weight: 700;">My Clients</h1>
        <p class="text-muted">View details and manage your active and past clients</p>
      </div>
    </section>

    <!-- Search & Filter -->
    <section class="section">
      <div class="container">
        <div
          style="background: white; padding: 1.5rem; border-radius: var(--border-radius); box-shadow: var(--box-shadow); margin-bottom: 2rem;">
          <div class="grid grid-3" style="gap: 1rem;">
            <div>
              <input type="text" placeholder="Search clients..." style="width: 100%;">
            </div>
            <div>
              <select style="width: 100%;">
                <option>All Clients</option>
                <option>Active</option>
                <option>Inactive</option>
                <option>Past Clients</option>
              </select>
            </div>
            <div style="display: flex; align-items: flex-end;">
              <button class="btn btn-primary" style="width: 100%;">Search</button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Clients List -->
    <section class="section">
      <div class="container">
        <div class="grid grid-2">
          <#if clients?has_content>
            <#list clients as client>
              <div class="card">
                <div class="card-header">
                  <h3 class="card-title">${client.name}</h3>
                  <p class="card-subtitle">${client.accountStatus?cap_first} Client</p>
                </div>
                <div class="card-content">
                  <div style="margin-bottom: 1rem;">
                    <p class="text-muted" style="margin-bottom: 0.25rem;"><strong>Email:</strong></p>
                    <p style="font-weight: 600;">${client.email}</p>
                  </div>
                  <div style="margin-bottom: 1rem;">
                    <p class="text-muted" style="margin-bottom: 0.25rem;"><strong>Phone:</strong></p>
                    <p style="font-weight: 600;">${client.phoneNumber!"Not provided"}</p>
                  </div>
                  <div style="display: flex; gap: 1rem; margin-top: 1.5rem;">
                    <a class="btn btn-primary" style="flex: 1; text-decoration: none; color: white;" href="/trainer/clients/${client.id}">View Profile</a>
                  </div>
                </div>
              </div>
            </#list>
          <#else>
            <div class="card" style="width: 100%;">
              <div class="card-content">
                <p>No clients found yet. Once customers book a session, they will appear here.</p>
              </div>
            </div>
          </#if>
        </div>
      </div>
    </section>

    <!-- Footer -->
    <footer>
      <p>&copy; 2026 FitMatch. All rights reserved.</p>
    </footer>

  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
  <script>
    function messageClient() {
      document.querySelector('.message-modal').style.display = 'block';
    }
  </script>
</body>
</body>

</html>