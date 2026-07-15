<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="/../styles.css">
  <title>Review - FitMatch</title>
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
        <h1 style="font-size: 2rem; font-weight: 700;">Session Review</h1>
        <p class="text-muted">View and edit your review for the training session</p>
      </div>
    </section>
    <!-- Review Section -->
    <section class="section">
      <div class="container" style="max-width: 600px;">
        <div class="card">
          <div class="card-content">
            <h2 style="font-size: 1.5rem; font-weight: 700; margin-bottom: 1rem;">Leave a review for ${(trainer.name!'Trainer')}</h2>
            <p class="text-muted">Share how your session went and help other customers choose the right trainer.</p>
            <form action="/customer/trainers/${trainer.id}/review" method="post">
              <div class="form-group">
                <label for="rating">Rating (1-5)</label>
                <input type="number" id="rating" name="rating" min="1" max="5" value="5" required>
              </div>
              <div class="form-group">
                <label for="comments">Your Review</label>
                <textarea id="comments" name="comments" rows="4" required></textarea>
              </div>
              <button class="btn btn-primary" type="submit">Submit Review</button>
            </form>
          </div>
        </div>
      </div>
    </section>


  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
</body>

</body>

</html>