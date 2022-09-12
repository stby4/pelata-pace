<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Pelata</title>
    <link rel="icon" type="image/svg+xml" href="/static/favicon.svg">
    <link rel="icon" type="image/png" href="/static/favicon.png">
    <link rel="stylesheet" href="/static/style.css">
    <meta name="viewport" content="width=device-width,initial-scale=1">
  </head>
  <body>
    <#include "/partials/header.ftl">

    <main>
      <section>
        <h2>Calculate your pace now</h2>
        <#include "/partials/form.ftl">
        <p>Enter your race distance and goal time to get the required average pace and as well as possible negative splits.
      </section>
    </main>
    
    <#include "/partials/footer.ftl">
  </body>
</html>