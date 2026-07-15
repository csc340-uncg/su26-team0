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
          <li><a href="/trainer/dashboard">Dashboard</a></li>
          <li><a href="/trainer/services">My Services</a></li>
          <li><a href="/trainer/clients">My Clients</a></li>
          <li><a href="/trainer/profile" style="color: var(--primary-color); font-weight: 700;">Profile</a></li>
          <li><a href="/trainer/login" class="btn btn-outline">Logout</a></li>
        </ul>
      </div>
    </nav>

    <!-- Header -->
    <section class="section" style="background: var(--light-gray);">
      <div class="container" style="text-align: center;">
        <h1 style="font-size: 2rem; font-weight: 700;">My Trainer Profile</h1>
        <p class="text-muted">Manage how you appear to potential clients</p>
        <img src="/trainer/picture/${trainer.id}" alt="${trainer.name!'Trainer'} profile photo" style="width: 180px; height: 180px; object-fit: cover; border-radius: 50%; margin: 1rem auto 0; display: block;" />
      </div>
    </section>

    <!-- Profile Content -->
    <section class="section">
      <div class="container" style="max-width: 800px;">

        <!-- Personal Info Card -->
        <div class="card" style="margin-bottom: 2rem;">
          <div class="card-header">
            <h2 style="margin: 0; font-size: 1.3rem; font-weight: 700;">Personal Information</h2>
          </div>
          <div class="card-content">
            <div class="grid grid-2">
              <div>
                <p class="text-muted" style="margin-bottom: 0.25rem;">Name</p>
                <p style="font-weight: 600;">${trainer.name!""}</p>
              </div>
              <div>
                <p class="text-muted" style="margin-bottom: 0.25rem;">Email</p>
                <p style="font-weight: 600;">${trainer.email!""}</p>
              </div>
              <div>
                <p class="text-muted" style="margin-bottom: 0.25rem;">Phone</p>
                <p style="font-weight: 600;">${trainer.phoneNumber!""}</p>
              </div>
            </div>
            <button class="btn btn-outline" onclick="window.location.href='/trainer/profile/edit'">Edit
              Personal Info</button>
          </div>
        </div>

        <!-- Professional Info Card -->
        <div class="card" style="margin-bottom: 2rem;">
          <div class="card-header">
            <h2 style="margin: 0; font-size: 1.3rem; font-weight: 700;">Professional Information</h2>
          </div>
          <div class="card-content">
            <div style="margin-bottom: 1rem;">
              <p class="text-muted" style="margin-bottom: 0.5rem;"><strong>Certifications:</strong></p>
              <p style="font-weight: 600; margin-bottom: 1rem;">${trainer.certifications!"Not provided"}</p>

              <p class="text-muted" style="margin-bottom: 0.5rem;"><strong>Years of Experience:</strong></p>
              <p style="font-weight: 600; margin-bottom: 1rem;">${trainer.yearsOfExperience!"Not provided"} years</p>

              <p class="text-muted" style="margin-bottom: 0.5rem;"><strong>Specializations:</strong></p>
              <p style="font-weight: 600; margin-bottom: 1rem;">${trainer.specialties!"Not provided"}</p>

              <p class="text-muted" style="margin-bottom: 0.5rem;"><strong>Professional Bio:</strong></p>
              <p style="font-weight: 600; margin-bottom: 1rem;">${trainer.biography!""}</p>
            </div>
            <button class="btn btn-outline" onclick="window.location.href='/trainer/profile/edit'">Edit
              Professional Info</button>
          </div>
        </div>

        <!-- Rates & Services Card -->
        <div class="card" style="margin-bottom: 2rem;">
          <div class="card-header">
            <h2 style="margin: 0; font-size: 1.3rem; font-weight: 700;">Rates & Services <a href="/trainer/services"
                class="btn btn-outline" style="margin-left: auto;">Manage Services</a></h2>

          </div>
        </div>

        <!-- Availability Card -->
        <div class="card" style="margin-bottom: 2rem;">
          <div class="card-header">
            <h2 style="margin: 0; font-size: 1.3rem; font-weight: 700;">Availability</h2>
          </div>
          <div class="card-content">
            <div class="grid grid-2">
              <#if availableTimeslots?has_content>
                <#list availableTimeslots as ts>
                  <div>
                    <p class="text-muted" style="margin-bottom: 0.5rem;">${ts.startTime?datetime.iso?string["EEEE"]}</p>
                    <p style="font-weight: 600;">${ts.startTime?datetime.iso?string('yyyy-MM-dd hh:mm a')} -
                      ${ts.endTime?datetime.iso?string('yyyy-MM-dd hh:mm a')}</p>
                  </div>
                </#list>
                <#else>
                  <div>
                    <p class="text-muted" style="margin-bottom: 0.5rem;">No availability</p>
                    <p style="font-weight: 600;">You have no available timeslots scheduled.</p>
                  </div>
              </#if>
            </div>
            <a href="/trainer/timeslots/new" class="btn btn-outline" style="margin-left: auto;">Add Availability
              Timeslot </a>
          </div>
        </div>

        <!-- Account Settings Card -->
        <div class="card">
          <div class="card-header">
            <h2 style="margin: 0; font-size: 1.3rem; font-weight: 700;">Account Settings</h2>
          </div>
          <div class="card-content">
            <a class="btn btn-outline"
              style="width: 100%; text-align: left; padding: 0.75rem 1rem; margin-bottom: 0.5rem;"
              href="/trainer/profile/edit">Edit Personal Info</a>
            <a class="btn btn-outline"
              style="width: 100%; text-align: left; padding: 0.75rem 1rem; color: var(--danger-color); border-color: var(--danger-color);"
              href="/trainer/delete" onclick="return confirm('Are you sure you want to delete your account?')">Delete
              Account </a>
          </div>
        </div>

      </div>
    </section>

    <!-- Footer -->
    <footer>
      <p>&copy; 2026 FitMatch. All rights reserved.</p>
    </footer>
  </div>
  <script>

    function openServices() {
      window.location.href = '/trainer/services';
    }
  </script>
</body>

</html>