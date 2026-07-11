<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="/../styles.css">
  <title>Browse Trainers - FitMatch</title>
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
    <section class="section" style="background: var(--primary-color); color: white;">
      <div class="container">
        <h1 style="font-size: 2rem; font-weight: 700; margin-bottom: 1rem; color: white;">Find Your Perfect Trainer</h1>

        <p style="font-size: 1.1rem; opacity: 0.9; color: white;">Browse certified fitness professionals matched to your
          strength training goals</p>
      </div>
    </section>

    <!-- Filters -->
    <section class="section">
      <div class="container">
        <div
          style="background: white; padding: 1.5rem; border-radius: var(--border-radius); box-shadow: var(--box-shadow); margin-bottom: 2rem;">
          <h3 style="font-weight: 700; margin-bottom: 1rem;">Filter Trainers</h3>
          <div class="grid grid-4" style="gap: 1rem;">
            <div>
              <label style="margin-bottom: 0.5rem;">Specialization</label>
              <select style="width: 100%;">
                <option>All Specialties</option>
                <option>Strength Training</option>
                <option>Weight Loss</option>
                <option>Flexibility</option>
                <option>Rehabilitation</option>
                <option>Cardio</option>
              </select>
            </div>
            <div>
              <label style="margin-bottom: 0.5rem;">Availability</label>
              <select style="width: 100%;">
                <option>Any Time</option>
                <option>Morning (6-11 AM)</option>
                <option>Afternoon (12-5 PM)</option>
                <option>Evening (5-10 PM)</option>
              </select>
            </div>
            <div>
              <label style="margin-bottom: 0.5rem;">Minimum Rating</label>
              <select style="width: 100%;">
                <option>Any Rating</option>
                <option>4.8+ Stars</option>
                <option>4.5+ Stars</option>
                <option>4.0+ Stars</option>
              </select>
            </div>
            <div style="display: flex; align-items: flex-end;">
              <button class="btn btn-primary" style="width: 100%;">Apply Filters</button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Trainers Grid -->
    <section class="section">
      <div class="container">
        <#if customerGoals?has_content>
          <div class="alert alert-info" style="margin-bottom: 1.5rem; border-left: 4px solid var(--primary-color);">
            <strong>Recommended for you:</strong> based on your goal of "${customerGoals}".
          </div>
        </#if>

        <#if specialtyMatches?has_content>
          <h3 style="font-weight: 700; margin-bottom: 1rem;">Specialty Matches</h3>
          <div class="grid grid-3" style="margin-bottom: 2rem;">
            <#list specialtyMatches as trainer>
              <div class="trainer-card recommended-trainer">
                <div class="trainer-image">
                  <img class="card-img-top" src="/trainer/picture/${trainer.id}" alt="${trainer.name!'Trainer'} profile picture" style="width: 100%; max-height: 190px; object-fit: cover; border-radius: 0.75rem;" />
                </div>
                <div class="trainer-info">
                  <span class="badge bg-success" style="margin-bottom: 0.75rem; display: inline-block;">Recommended</span>
                  <h3 class="trainer-name">${trainer.name!'Trainer'}</h3>
                  <p class="trainer-specialty">${trainer.specialties!'Specialized coaching'}</p>
                  <div class="trainer-rating">${trainer.yearsOfExperience!'0'} years experience</div>
                  <div class="trainer-bio">${trainer.biography!'Certified coach ready to help you reach your goals.'}</div>
                  <a class="btn btn-outline" style="text-decoration: none; color: gray;" href="/customer/trainers/${trainer.id}">View Profile</a>
                  <a class="btn btn-outline" style="width: 100%; text-align: center; text-decoration: none; color: gray;" href="/customer/trainers/${trainer.id}/book">Book Session</a>
                </div>
              </div>
            </#list>
          </div>
        </#if>

        <#if trainers?has_content>
          <h3 style="font-weight: 700; margin-bottom: 1rem;">All Trainers</h3>
          <div class="grid grid-3">
            <#list trainers as trainer>
              <div class="trainer-card<#if recommendedTrainer?? && recommendedTrainer.id == trainer.id> recommended-trainer</#if>">
                <div class="trainer-image">
                  <img class="card-img-top" src="/trainer/picture/${trainer.id}" alt="${trainer.name!'Trainer'} profile picture" style="width: 100%; max-height: 190px; object-fit: cover; border-radius: 0.75rem;" />
                </div>
                <div class="trainer-info">
                  <h3 class="trainer-name">${trainer.name!'Trainer'}</h3>
                  <p class="trainer-specialty">${trainer.specialties!'Specialized coaching'}</p>
                  <div class="trainer-rating">${trainer.yearsOfExperience!'0'} years experience</div>
                  <div class="trainer-bio">${trainer.biography!'Certified coach ready to help you reach your goals.'}</div>
                  <a class="btn btn-outline" style="text-decoration: none; color: gray;" href="/customer/trainers/${trainer.id}">View Profile</a>
                  <a class="btn btn-outline" style="width: 100%; text-align: center; text-decoration: none; color: gray;" href="/customer/trainers/${trainer.id}/book">Book Session</a>
                </div>
              </div>
            </#list>
          </div>
        <#else>
          <p class="text-muted">No trainers are available right now. Please check back later.</p>
        </#if>
      </div>
    </section>

    <!-- Footer -->
    <footer>
      <p>&copy; 2026 FitMatch. All rights reserved.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"></script>
  </div>
</body>

</html>