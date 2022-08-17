<form action="/" method="post">
    <input
        type="number"
        id="distance"
        name="distance"
        placeholder="Enter a distance"
        value="${(form.distance)!21.2}"
        min="0"
        max="1000"
        step="0.1"
    >
    <label for="distance">km</label>
    <input type="submit" value="Submit">
</form>