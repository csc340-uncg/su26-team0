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
          <form method="get" action="/customer/browse/search" class="grid grid-4" style="gap: 1rem;">
            <div>
              <label for="search" style="margin-bottom: 0.5rem;">Search by category or service name</label>
              <input id="search" name="query" type="text" value="${activeSearch!''}"
                placeholder="Try Strength Training, Weight Loss, or Yoga" style="width: 100%;">
            </div>
            <div style="display: flex; align-items: flex-end;">
              <button class="btn btn-primary" style="width: 100%;" type="submit">Apply Filters</button>
            </div>
          </form>
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
                  <img class="card-img-top" src="/trainer/picture/${trainer.id}"
                    alt="${trainer.name!'Trainer'} profile picture"
                    style="width: 100%; max-height: 190px; object-fit: cover; border-radius: 0.75rem;" />
                </div>
                <div class="trainer-info">
                  <span class="badge bg-success"
                    style="margin-bottom: 0.75rem; display: inline-block;">Recommended</span>
                  <h3 class="trainer-name">${trainer.name!'Trainer'}</h3>
                  <p class="trainer-specialty">${trainer.specialties!'Specialized coaching'}</p>
                  <div class="trainer-rating">${trainer.yearsOfExperience!'0'} years experience</div>
                  <div class="trainer-bio">${trainer.biography!'Certified coach ready to help you reach your goals.'}
                  </div>
                  <a class="btn btn-outline" style="text-decoration: none; color: gray;"
                    href="/customer/trainers/${trainer.id}">View Profile</a>
                  <a class="btn btn-outline"
                    style="width: 100%; text-align: center; text-decoration: none; color: gray;"
                    href="/customer/trainers/${trainer.id}/book">Book Session</a>
                </div>
              </div>
            </#list>
          </div>
        </#if>

        <#if trainers?has_content>
          <#if activeSearch?has_content>
            <h3 style="font-weight: 700; margin-bottom: 1rem;">Search results for "${activeSearch}"</h3>
          <#else>
            <h3 style="font-weight: 700; margin-bottom: 1rem;">All Trainers</h3>
          </#if>
          <div class="grid grid-3">
            <#list trainers as trainer>
              <div
                class="trainer-card<#if recommendedTrainer?? && recommendedTrainer.id == trainer.id> recommended-trainer</#if>">
                <div class="trainer-image">
                  <img class="card-img-top" src="/trainer/picture/${trainer.id}"
                    alt="${trainer.name!'Trainer'} profile picture"
                    style="width: 100%; max-height: 190px; object-fit: cover; border-radius: 0.75rem;" />
                </div>
                <div class="trainer-info">
                  <h3 class="trainer-name">${trainer.name!'Trainer'}</h3>
                  <p class="trainer-specialty">${trainer.specialties!'Specialized coaching'}</p>
                  <div class="trainer-rating">${trainer.yearsOfExperience!'0'} years experience</div>
                  <div class="trainer-bio">${trainer.biography!'Certified coach ready to help you reach your goals.'}
                  </div>
                  <a class="btn btn-outline" style="text-decoration: none; color: gray;"
                    href="/customer/trainers/${trainer.id}">View Profile</a>
                  <a class="btn btn-outline"
                    style="width: 100%; text-align: center; text-decoration: none; color: gray;"
                    href="/customer/trainers/${trainer.id}/book">Book Session</a>
                </div>
              </div>
            </#list>
          </div>
          <#else>
            <p class="text-muted">
              <#if activeSearch?has_content>No trainers match your search for "${activeSearch}". Try another category or
                service name.</#if>
              <#if !activeSearch?has_content>No trainers are available right now. Please check back later.</#if>
            </p>
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