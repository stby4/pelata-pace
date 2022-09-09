<form action="/" method="post">
    <!-- distance -->
    <input
        type="number"
        id="distance"
        name="distance"
        placeholder="Race distance"
        value="${(form.distance)!5.0}"
        min="0"
        max="10000"
        step="0.1"
    >
    <label for="distance">km</label>

    <!-- goal time -->
    <input
        type="number"
        id="time"
        name="time"
        placeholder="Desired goal time"
        value="${(form.time)!30}"
        min="0"
        max="10000"
        step="0.5"
    >
    <label for="time">minutes</label>

    <!-- CSRF and submit -->
    <input type="hidden" name="csrfToken" value="${form.csrfToken}">
    <input type="submit" value="Submit">
</form>