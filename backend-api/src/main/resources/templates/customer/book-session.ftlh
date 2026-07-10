<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="/../styles.css">
  <title>Book Session - FitMatch</title>
</head>

<body>
  <div class="container">
    <nav>
      <div class="navbar">
        <a href="/" class="navbar-brand"><img src="/../images/logo.png" width="120" height="92"></a>
        <ul class="nav-links">
          <li><a href="/customer/browse">Browse Trainers</a></li>
          <li><a href="/customer/sessions">My Sessions</a></li>
          <li><a href="/customer/profile">My Profile</a></li>
        </ul>
      </div>
    </nav>

    <section class="section">
      <div class="container" style="max-width: 700px;">
        <div class="card">
          <div class="card-header">
            <h1 style="font-size: 1.8rem; font-weight: 700; margin: 0;">Book a session with ${trainer.name!'Trainer'}
            </h1>
          </div>
          <div class="card-content">
            <p class="text-muted" style="margin-bottom: 1.5rem;">Choose one of the trainer's currently available
              timeslots.</p>

            <form action="/customer/sessions/book" method="post">
              <input type="hidden" name="trainerId" value="${trainer.id}" />

              <div class="form-group">
                <label for="serviceId">Select a service</label>
                <select id="serviceId" name="serviceId" class="form-control" required>
                  <#if services?has_content>
                    <#list services as service>
                      <option value="${service.id}">${service.name!'Service'} - ${service.category!'General'} -$
                        ${service.price!'Contact trainer'}</option>
                    </#list>
                    <#else>
                      <option value="" disabled selected>No services available</option>
                  </#if>
                </select>
              </div>

              <div class="form-group">
                <label for="timeslotId">Available timeslots</label>
                <select id="timeslotId" name="timeslotId" class="form-control" required>
                  <#if timeslots?has_content>
                    <#list timeslots as timeslot>${(session.timeslot.startTime?datetime.iso?string('yyyy-MM-dd HH:mm
                      a'))!'TBD'}
                      <option value="${timeslot.id}">
                        ${(timeslot.startTime?datetime.iso?string('yyyy-MM-dd hh:mm a'))!'TBD'} to
                        ${(timeslot.endTime?datetime.iso?string('yyyy-MM-dd hh:mm a'))!'TBD'}
                      </option>
                    </#list>
                    <#else>
                      <option value="" disabled selected>No available timeslots right now</option>
                  </#if>
                </select>
              </div>
              <div class="form-group">
                <label> Level *</label>
                <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(100px, 1fr)); gap: 1rem;">
                  <label style="display: flex; align-items: center; margin: 0; font-weight: 500;">
                    <input type="radio" name="level" value="beginner" required
                      style="width: auto; margin-right: 0.5rem;"> Beginner
                  </label>
                  <label style="display: flex; align-items: center; margin: 0; font-weight: 500;">
                    <input type="radio" name="level" value="intermediate" style="width: auto; margin-right: 0.5rem;">
                    Intermediate
                  </label>
                  <label style="display: flex; align-items: center; margin: 0; font-weight: 500;">
                    <input type="radio" name="level" value="advanced" style="width: auto; margin-right: 0.5rem;">
                    Advanced
                  </label>
                </div>
              </div>

              <div class="form-group">
                <label for="notes">Notes for the trainer</label>
                <textarea id="notes" name="notes" class="form-control" rows="4"
                  placeholder="Tell the trainer about your goals or any concerns."></textarea>
              </div>

              <button type="submit" class="btn btn-primary" style="width: 100%;">Book Session</button>
            </form>
          </div>
        </div>
      </div>
    </section>
  </div>
</body>

</html>