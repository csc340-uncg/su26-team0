<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="/../styles.css">
  <title>Edit Trainer Profile - FitMatch</title>
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
      <div class="container">
        <h1 style="font-size: 2rem; font-weight: 700;">Edit Profile</h1>
        <p class="text-muted">Update your personal and professional details below</p>
      </div>
    </section>

    <section class="section">
      <div class="container" style="max-width: 900px; display: flex; gap: 1rem;">
        <div class="card" style="flex:1;">
          <div class="card-header"><h3 style="margin:0;">Personal Info</h3></div>
          <div class="card-content" style="padding:1rem;">
            <form method="post" action="/trainer/profile/edit" enctype="multipart/form-data">
              <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" class="form-control" value="${trainer.name!''}" required />
              </div>
              <div class="form-group" style="margin-top:0.75rem;">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" class="form-control" value="${trainer.email!''}" required />
              </div>
              <div class="form-group" style="margin-top:0.75rem;">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" />
                <small class="text-muted">Leave blank to keep current password</small>
              </div>
              <div class="form-group">
                <label for="profilePictureFile">Profile Picture</label>
                <input type="file" name="profilePictureFile" id="profilePictureFile" accept="image/*">
              </div>
              <div style="margin-top:1rem; display:flex; gap:0.5rem;">
                <button type="submit" class="btn btn-primary">Save Personal Info</button>
                <a class="btn btn-outline" href="/trainer/profile">Cancel</a>
              </div>
            </form>
          </div>
        </div>

        <div class="card" style="flex:1;">
          <div class="card-header"><h3 style="margin:0;">Professional Info</h3></div>
          <div class="card-content" style="padding:1rem;">
            <form method="post" action="/trainer/professional/edit">
              <div class="form-group">
                <label for="biography">Professional Bio</label>
                <textarea id="biography" name="biography" class="form-control" rows="4">${trainer.biography!''}</textarea>
              </div>
              <div class="form-group" style="margin-top:0.75rem;">
                <label for="certifications">Certifications</label>
                <input type="text" id="certifications" name="certifications" class="form-control" value="${trainer.certifications!''}" />
              </div>
              <div class="form-group" style="margin-top:0.75rem;">
                <label for="specialties">Specialties</label>
                <input type="text" id="specialties" name="specialties" class="form-control" value="${trainer.specialties!''}" />
              </div>
              <div class="form-group" style="margin-top:0.75rem;">
                <label for="yearsOfExperience">Years of Experience</label>
                <input type="number" id="yearsOfExperience" name="yearsOfExperience" class="form-control" value="${trainer.yearsOfExperience!''}" min="0" />
              </div>
              <div style="margin-top:1rem; display:flex; gap:0.5rem;">
                <button type="submit" class="btn btn-primary">Save Professional Info</button>
                <a class="btn btn-outline" href="/trainer/profile">Cancel</a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>

  </div>
</body>

</html>