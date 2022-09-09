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
      <section>
        <#include "/partials/form.ftl">
      </section>

      <section>
        <p>
          <span class="highlight">${result.average} min/km</span> average pace
        </p>
        <h2>Negative splits</h2>
        <table>
          <tbody>
            <#list result.splits as splits>
              <tr>
                <#list splits as pairs>
                  <td>${pairs.second}</td>
                </#list>
              </tr>
            </#list>
          </tbody>
        </table>
      </section>
    </main>
    
    <#include "/partials/footer.ftl">
  </body>
</html>