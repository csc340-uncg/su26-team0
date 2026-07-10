<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="/styles.css">
  <title>Login - FitMatch</title>
</head>

<body>
  <div class="container">
    <!-- Navigation -->
    <nav>
      <div class="navbar">
        <a href="/" class="navbar-brand"> <img src="/images/logo.png" width="120" height="92"></a>

        <ul class="nav-links">
          <li><a href="/">Home</a></li>
          <li><a href="/customer/register" class="btn btn-outline">Sign Up</a></li>
        </ul>
      </div>
    </nav>

    <!-- Login Section -->
    <section class="section"
      style="padding: 4rem 2rem; display: flex; align-items: center; justify-content: center; min-height: 80vh;">
      <div class="container" style="max-width: 500px;">
        <div class="card">
          <div class="card-content">
            <h2 class="text-center mb-4" style="font-weight: 700; font-size: 1.8rem;">Welcome Back to FitMatch</h2>
            <p class="text-center text-muted mb-4">Sign in to your account to manage your training sessions</p>

            <form method="post">
              <div class="form-group">
                <label for="email">Email Address</label>
                <input type="email" id="email" name="email" placeholder="you@example.com" required>
              </div>

              <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="••••••••" required>
              </div>

              <div class="form-group" style="display: flex; justify-content: space-between; align-items: center;">
                <label style="margin: 0;">
                  <input type="checkbox" style="width: auto; margin-right: 0.5rem;">
                  Remember me
                </label>
                <a href="#" style="color: var(--primary-color); text-decoration: none;">Forgot password?</a>
              </div>

              <button type="submit" class="btn btn-primary btn-large" formaction="/customer/login" formmethod="post"
                style="width: 100%; margin-top: 1.5rem;">
                Sign In as Customer
              </button>

              <button type="submit" class="btn btn-outline btn-large" formaction="/trainer/login" formmethod="post"
                style="width: 100%; margin-top: 1rem;">
                Sign In as Trainer/Provider
              </button>
            </form>

            <p class="text-center mt-4">
              Don't have an account? <a href="/customer/register"
                style="color: var(--primary-color); font-weight: 600; text-decoration: none;">Sign up here</a>
            </p>
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