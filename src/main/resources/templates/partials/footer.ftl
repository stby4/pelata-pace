<#-- @ftlvariable name="footerdata" type="net.pelata.features.pace.data.Footer" -->

<#macro footer footerdata>
    <footer>
        <i>
            Copyright © ${footerdata.year?string["0"]} <a href="https://www.pelata.net">Pelata</a> | <a href="https://github.com/stby4/pelata-pace" target="_blank" referrerpolicy="no-referrer">GitHub</a>
        </i>
    </footer>
</#macro>