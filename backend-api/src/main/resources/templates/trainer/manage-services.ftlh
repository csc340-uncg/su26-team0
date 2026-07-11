<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="/../styles.css">
  <title>Manage Services - FitMatch</title>
</head>

<body>
  <div class="container">
    <!-- Navigation -->
    <nav>
      <div class="navbar">
        <a href="/" class="navbar-brand"> <img src="/../images/logo.png" width="120" height="92"></a>
        <ul class="nav-links">
          <li><a href="/trainer/dashboard">Dashboard</a></li>
          <li><a href="/trainer/services" style="color: var(--primary-color); font-weight: 700;">My Services</a></li>
          <li><a href="/trainer/clients">My Clients</a></li>
          <li><a href="/trainer/profile">Profile</a></li>
          <li><a href="/trainer/login" class="btn btn-outline">Logout</a></li>
        </ul>
      </div>
    </nav>

    <!-- Header -->
    <section class="section" style="background: var(--light-gray);">
      <div class="container">
        <h1 style="font-size: 2rem; font-weight: 700;">Manage Your Services</h1>
        <p class="text-muted">Create and manage your training offerings</p>
      </div>
    </section>

    <!-- Content -->
    <section class="section">
      <div class="container">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem;">
          <h2 style="font-size: 1.5rem; font-weight: 700;">Your Training Services</h2>
          <a class="btn btn-primary" style="text-decoration: none; color: gray;" href="/trainer/services/new">Add New
            Service</a>
        </div>

        <#if services?has_content>
          <#list services as service>
            <div class="card" style="margin-bottom: 1.5rem;">
              <div class="card-header">
                <div>
                  <h3 class="card-title">${service.name!'Training Service'}</h3>
                  <p class="card-subtitle">${service.category!'General'}</p>
                </div>
                <div>
                  <span class="badge"
                    style="background: var(--secondary-color); color: white; padding: 0.35rem 0.75rem; border-radius: 0.75rem;">${service.status!'Active'}</span>
                </div>
              </div>
              <div class="card-content">
                <div class="grid grid-2">
                  <div>
                    <p class="text-muted" style="margin-bottom: 0.25rem;"><strong>Price:</strong></p>
                    <p style="font-weight: 600; margin-bottom: 1rem;">${service.price!'Contact for pricing'}</p>

                    <p class="text-muted" style="margin-bottom: 0.25rem;"><strong>Category:</strong></p>
                    <p style="font-weight: 600; margin-bottom: 1rem;">${service.category!'General'}</p>

                    <p class="text-muted" style="margin-bottom: 0.25rem;"><strong>Status:</strong></p>
                    <p style="font-weight: 600;">${service.status!'Active'}</p>
                  </div>
                  <div>
                    <p class="text-muted" style="margin-bottom: 0.25rem;"><strong>Description:</strong></p>
                    <p style="font-weight: 600; margin-bottom: 1rem;">${service.description!'No description yet.'}</p>

                    <p class="text-muted" style="margin-bottom: 0.25rem;"><strong>Sessions Booked:</strong></p>
                    <p style="font-weight: 600;">${service.trainingSessions?size!'0'}</p>
                  </div>
                </div>
                <div style="display: flex; gap: 1rem; margin-top: 1.5rem;">
                  <button class="btn btn-outline"
                    onclick="window.location.href='/trainer/services/${service.id}/edit'">Edit</button>
                  <a class="btn btn-outline" style="color: var(--danger-color); border-color: var(--danger-color);"
                    onclick="return confirm('Are you sure you want to deactivate this service?');"
                    href="/trainer/services/${service.id}/deactivate">Deactivate</a>
                </div>
              </div>
            </div>
          </#list>
          <#else>
            <div class="card" style="margin-bottom: 1.5rem;">
              <div class="card-content">
                <p class="text-muted">You have not added any services yet.</p>
              </div>
            </div>
        </#if>

        <!-- Footer -->
        <footer>
          <p>&copy; 2026 FitMatch. All rights reserved.</p>
        </footer>
      </div>
</body>

</html>