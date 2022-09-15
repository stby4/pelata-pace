<form action="/pace" method="post">
    <!-- distance -->
    <div class="input-group">
        <input
            type="number"
            id="distance"
            name="distance"
            placeholder="Race distance"
            required
            value="${(form.distance)!5.0}"
            min="0"
            max="10000"
            step="0.1"
        >
        <label for="distance">km</label>
    </div>

    <!-- goal time -->
    <div class="input-group">
        <input
            type="number"
            id="time"
            name="time"
            placeholder="Desired goal time"
            required
            value="${(form.time)!30}"
            min="0"
            max="10000"
            step="0.5"
        >
        <label for="time">minutes</label>
    </div>

    <!-- CSRF and submit -->
    <input type="hidden" name="csrfToken" value="${form.csrfToken}">
    <input class="submit" type="submit" value="Submit">
</form>