<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Pelata</title>
    <link rel="icon" type="image/svg+xml" href="/static/favicon.svg">
    <link rel="icon" type="image/png" href="/static/favicon.png">
    <link rel="stylesheet" href="static/style.css">
    <meta name="robots" content="noindex">
    <meta name="viewport" content="width=device-width,initial-scale=1">
  </head>
  <body>
    <#include "/partials/header.ftl">

    <main>
    <#include "/partials/form.ftl">
    <h2>Results</h2>
    <p>
      ${form.distance}
    </p>
    </main>
    
    <#include "/partials/footer.ftl">
  </body>
</html>