<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="/../styles.css">
  <title>New Services - FitMatch</title>
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

    <section class="section" style="padding: 3rem 2rem;">
      <div class="container" style="max-width: 600px;">
        <div class="card">
          <div class="card-content">
            <h2 class="text-center mb-2" style="font-weight: 700; font-size: 1.8rem;">Add a new training service</h2>
            <p class="text-center text-muted mb-4">Register a new service to offer your client base</p>
            <form id="newServiceForm" action="/trainer/services/add" method="post">
              <!-- Service Information -->
              <h3 style="font-weight: 700; margin-top: 1.5rem; margin-bottom: 1rem; color: var(--primary-color);">
                Service Information</h3>

              <div class="form-group">
                <label for="serviceName">Service Name</label>
                <input type="text" id="serviceName" name="name" placeholder="Strength Training Program" required>
              </div>

              <div class="form-group">
                <label for="category">Category</label>
                <select id="category" required name="category">
                  <option value="">Select a category</option>
                  <option value="strength">Strength Training</option>
                  <option value="cardio">Cardiovascular Training</option>
                  <option value="flexibility">Flexibility Training</option>
                </select>
              </div>

              <div class="form-group">
                <label for="serviceDescription">Description</label>
                <textarea id="serviceDescription" name="description" placeholder="Describe your training service..."
                  required></textarea>
              </div>
              <div class="form-group">
                <label for="servicePrice">Price ($)</label>
                <input type="number" id="servicePrice" name="price" placeholder="50.00" step="0.01" required>
              </div>

              <button class="btn btn-primary" style="width: 100%;" type="submit">Add Service</button>
            </form>
          </div>
        </div>
      </div>
    </section>

    <!-- Footer -->
    <footer>
      <p>&copy; 2026 FitMatch. All rights reserved.</p>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous">  </script>
</body>

</html>