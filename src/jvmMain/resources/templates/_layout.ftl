<#import "/partials/header.ftl" as headerpartial>
<#import "/partials/footer.ftl" as footerpartial>

<#macro doc isSmallHeader=false>
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
        <@headerpartial.header isSmall=isSmallHeader />

        <#nested>
        
        <@footerpartial.footer />
    </body>
    </html>
</#macro>