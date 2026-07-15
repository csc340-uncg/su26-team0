<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="/../styles.css">
  <title>My Profile - FitMatch</title>
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
        <h1 style="font-size: 2rem; font-weight: 700;">My Profile</h1>
        <p class="text-muted">View and update your fitness goals and preferences</p>
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
                <p class="text-muted" style="margin-bottom: 0.25rem;">Full Name</p>
                <p style="font-weight: 600;">${customer.name!'Customer'}</p>
              </div>
              <div>
                <p class="text-muted" style="margin-bottom: 0.25rem;">Email</p>
                <p style="font-weight: 600;">${customer.email!'No email on file'}</p>
              </div>
              <div>
                <p class="text-muted" style="margin-bottom: 0.25rem;">Phone</p>
                <p style="font-weight: 600;">${customer.phoneNumber!'Not provided'}</p>
              </div>
              <div>
                <p class="text-muted" style="margin-bottom: 0.25rem;">Account Status</p>
                <p style="font-weight: 600;">${customer.accountStatus!'Active'}</p>
              </div>
            </div>
            <a class="btn btn-outline" style="margin-top: 1rem;" href="/customer/profile/edit">Edit Personal Info</a>
          </div>
        </div>

        <!-- Fitness Goals Card -->
        <div class="card" style="margin-bottom: 2rem;">
          <div class="card-header">
            <h2 style="margin: 0; font-size: 1.3rem; font-weight: 700;">Fitness Goals</h2>
          </div>
          <div class="card-content">
            <div class="form-group">
              <p class="text-muted" style="margin-bottom: 0.5rem;"><strong>Primary Goal:</strong></p>
              <p style="font-weight: 600; margin-bottom: 1rem;">${customer.fitnessGoals!'Set a goal'}</p>

              <p class="text-muted" style="margin-bottom: 0.5rem;"><strong>Current Fitness Level:</strong></p>
              <p style="font-weight: 600; margin-bottom: 1rem;">${customer.fitnessLevel!'Not provided'}</p>

              <p class="text-muted" style="margin-bottom: 0.5rem;"><strong>Target Weight:</strong></p>
              <p style="font-weight: 600; margin-bottom: 1rem;">${customer.goalWeight!'Not set'} (currently
                ${customer.currentWeight!'Not set'})</p>
            </div>
            <a class="btn btn-outline" href="/customer/fitness/edit">Edit Fitness Goals</a>
          </div>
        </div>

        <!-- Constraints Card -->
        <div class="card" style="margin-bottom: 2rem;">
          <div class="card-header">
            <h2 style="margin: 0; font-size: 1.3rem; font-weight: 700;">Health Constraints</h2>
          </div>
          <div class="card-content">
            <p class="text-muted" style="margin-bottom: 0.5rem;"><strong>Injuries or Conditions:</strong></p>
            <p style="font-weight: 600; margin-bottom: 1rem;">${customer.injuriesOrHealthConcerns!'None listed'}</p>

            <a class="btn btn-outline" href="/customer/fitness/edit">Edit Constraints</a>
          </div>
        </div>

        <!-- Account Settings Card -->
        <div class="card" style="margin-bottom: 2rem;">
          <div class="card-header">
            <h2 style="margin: 0; font-size: 1.3rem; font-weight: 700;">Account Settings</h2>
          </div>
          <div class="card-content">
            <a class="btn btn-outline"
              style="width: 100%; text-align: left; padding: 0.75rem 1rem; margin-bottom: 0.5rem;"
              href="/customer/profile/edit">Edit Personal Info</a>
            <a class="btn btn-outline"
              style="width: 100%; text-align: left; padding: 0.75rem 1rem; color: var(--danger-color); border-color: var(--danger-color);"
              href="/customer/delete"
              onclick="return confirm('Are you sure you want to delete your account?')">Delete Account </a>
          </div>
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