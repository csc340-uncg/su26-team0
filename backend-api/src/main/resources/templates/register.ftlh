<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link rel="stylesheet" href="/styles.css">
  <title>Sign Up - FitMatch</title>
</head>

<body>
  <!-- Navigation -->
  <nav>
    <div class="navbar">
      <a href="/" class="navbar-brand"> <img src="/images/logo.png" width="120" height="92"></a>

      <ul class="nav-links">
        <li><a href="/">Home</a></li>
        <li><a href="/customer/login" class="btn btn-outline">Login</a></li>
      </ul>
    </div>
  </nav>

  <!-- Registration Section -->
  <section class="section" style="padding: 3rem 2rem;">
    <div class="container" style="max-width: 600px;">
      <div class="card">
        <div class="card-content">
          <h2 class="text-center mb-2" style="font-weight: 700; font-size: 1.8rem;">Join FitMatch</h2>
          <p class="text-center text-muted mb-4">Create your customer profile and find your perfect trainer</p>

          <form id="customerForm" action="/customer/signup" method="post">
            <!-- Personal Information -->
            <h3 style="font-weight: 700; margin-top: 1.5rem; margin-bottom: 1rem; color: var(--primary-color);">Personal
              Information</h3>

            <div class="form-group">
              <label for="name">Full Name</label>
              <input type="text" id="name" name="name" placeholder="John B Doe" required>
            </div>

            <div class="form-group">
              <label for="email">Email Address</label>
              <input type="email" id="email" name="email" placeholder="john@example.com" required>
            </div>

            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" id="password" name="password" placeholder="••••••••" required>
            </div>

            <!-- Fitness Goals -->
            <h3 style="font-weight: 700; margin-top: 1.5rem; margin-bottom: 1rem; color: var(--primary-color);">Fitness
              Goals</h3>

            <div class="form-group">
              <label for="fitnessGoals">What Are Your Fitness Goals</label>
              <textarea id="fitnessGoals" name="fitnessGoals"
                placeholder="e.g., Strength Training, Flexibility and Mobility, Rehab, Cardio and Endurence, ... "
                style="min-height: 80px;"></textarea>
            </div>
            <div class="form-group">
              <label for="currentWeight">Current Weight</label>
              <input type="number" id="currentWeight" name="currentWeight" required>
            </div>
            <div class="form-group">
              <label for="goalWeight">Goal Weight</label>
              <input type="number" id="goalWeight" name="goalWeight" required>
            </div>

            <div class="form-group">
              <label>Current Fitness Level *</label>
              <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(100px, 1fr)); gap: 1rem;">
                <label style="display: flex; align-items: center; margin: 0; font-weight: 500;">
                  <input type="radio" name="fitnessLevel" value="beginner" required style="width: auto; margin-right: 0.5rem;">
                  Beginner
                </label>
                <label style="display: flex; align-items: center; margin: 0; font-weight: 500;">
                  <input type="radio" name="fitnessLevel" value="intermediate" style="width: auto; margin-right: 0.5rem;">
                  Intermediate
                </label>
                <label style="display: flex; align-items: center; margin: 0; font-weight: 500;">
                  <input type="radio" name="fitnessLevel" value="advanced" style="width: auto; margin-right: 0.5rem;"> Advanced
                </label>
              </div>
            </div>

            <!-- Constraints & Preferences -->
            <h3 style="font-weight: 700; margin-top: 1.5rem; margin-bottom: 1rem; color: var(--primary-color);">
              Constraints & Preferences</h3>

            <div class="form-group">
              <label>Any Injuries or Health Constraints?</label>
              <textarea id="constraints" name="injuriesOrHealthConcerns" placeholder="e.g., Lower back pain, Recent shoulder surgery..."
                style="min-height: 80px;"></textarea>
            </div>
            <!-- Terms -->
            <div class="form-group" style="margin-top: 1.5rem;">
              <label style="display: flex; align-items: flex-start; margin: 0;">
                <input type="checkbox" id="terms" required
                  style="width: auto; margin-right: 0.5rem; margin-top: 0.25rem;">
                <span>I agree to the Terms of Service and Privacy Policy</span>
              </label>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary btn-large" style="width: 100%; margin-top: 1.5rem;">
              Create Customer Account
            </button>
          </form>
          <!-- Provider Link -->
          <p class="text-center mt-4">
            Are you a trainer? <a href="/trainer/register"
              style="color: var(--primary-color); font-weight: 600; text-decoration: none;">Sign up as a provider</a>
          </p>

          <!-- Already has account -->
          <p class="text-center">
            Already have an account? <a href="/customer/login"
              style="color: var(--primary-color); font-weight: 600; text-decoration: none;">Login here</a>
          </p>

        </div>
      </div>
    </div>
  </section>

  <!-- Footer -->
  <footer>
    <p>&copy; 2026 FitMatch. All rights reserved.</p>
  </footer>

  <script>
  </script>
</body>

</html>