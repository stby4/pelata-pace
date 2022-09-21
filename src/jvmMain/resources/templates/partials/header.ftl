<#import "/partials/logo.ftl" as logopartial>

<#macro header isSmall=false>
    <header <#if isSmall>class="small"</#if>>
        <a href="/">
            <@logopartial.logo />
            <h1 class="title">Pelata</h1>
        </a>
    </header>
</#macro>