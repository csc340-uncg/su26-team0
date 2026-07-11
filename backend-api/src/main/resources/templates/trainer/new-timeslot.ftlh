<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="/../styles.css">
  <title>New Timeslot - FitMatch</title>
</head>

<body>
  <div class="container">
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

    <section class="section">
      <div class="container">
        <h1 style="font-weight:700">Add Availability Timeslot</h1>
        <div style="background:white; padding:1.25rem; border-radius:8px; margin-top:1rem;">
          <form method="post" action="/trainer/timeslots/add">
            <div class="mb-3">
              <label for="startTime" class="form-label">Start (local)</label>
              <input id="startTime" name="startTime" type="datetime-local" class="form-control" required />
            </div>
            <div class="mb-3">
              <label for="endTime" class="form-label">End (local)</label>
              <input id="endTime" name="endTime" type="datetime-local" class="form-control" required />
            </div>
            <button class="btn btn-primary" type="submit">Add Timeslot</button>
            <a class="btn btn-outline" href="/trainer/services">Cancel</a>
          </form>
        </div>
      </div>
    </section>

  </div>
</body>

</html>