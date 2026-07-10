<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="/../styles.css">
  <title>Edit Profile - FitMatch</title>
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
    <section class="section" style="background: var(--light-gray);">
      <div class="container">
        <h1 style="font-size: 2rem; font-weight: 700;">Edit Information</h1>
        <p class="text-muted">Update your details below</p>
      </div>
    </section>
    <section class="section">
      <div class="container" style="max-width: 600px;">
        <div class="card">
          <div class="card-content">
            <#if section=='fitness'>
              <form id="fitnessSectionForm" action="/customer/fitness/edit" method="post">
                <div class="form-group">
                  <label for="currentWeight">Current Weight</label>
                  <input type="number" step="0.01" id="currentWeight" name="currentWeight"
                    value="${customer.currentWeight!'0'}">
                </div>
                <div class="form-group">
                  <label for="goalWeight">Goal Weight</label>
                  <input type="number" step="0.01" id="goalWeight" name="goalWeight" value="${customer.goalWeight!'0'}">
                </div>
                <div class="form-group">
                  <label for="fitnessLevel">Fitness Level</label>
                  <input type="text" id="fitnessLevel" name="fitnessLevel" value="${customer.fitnessLevel!''}">
                </div>
                <div class="form-group">
                  <label for="fitnessGoals">Fitness Goals</label>
                  <input type="text" id="fitnessGoals" name="fitnessGoals" value="${customer.fitnessGoals!''}">
                </div>
                <div class="form-group">
                  <label for="injuriesOrHealthConcerns">Injuries / Health Concerns</label>
                  <textarea id="injuriesOrHealthConcerns" name="injuriesOrHealthConcerns"
                    rows="3">${customer.injuriesOrHealthConcerns!''}</textarea>
                </div>
                <button type="submit" class="btn btn-primary">Save Changes</button>
              </form>
              <#else>
                <form id="personalSectionForm" action="/customer/profile/edit" method="post">
                  <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" value="${customer.name!'Customer'}" required>
                  </div>
                  <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="${customer.email!'email@example.com'}" required>
                  </div>
                  <div class="form-group">
                    <label for="phoneNumber">Phone</label>
                    <input type="tel" id="phoneNumber" name="phoneNumber" value="${customer.phoneNumber!''}">
                  </div>
                  <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" value="${customer.password!''}" required>
                  </div>
                  <button type="submit" class="btn btn-primary">Save Changes</button>
                </form>
            </#if>
          </div>
        </div>
      </div>
    </section>
  </div>
</body>

</html>