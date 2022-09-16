<#-- @ftlvariable name="formdata" type="net.pelata.features.pace.data.Form" -->

<#macro pace formdata>
    <form action="/pace/result" method="get">
        <#-- distance -->
        <div class="input-group">
            <input
                type="text"
                inputmode="decimal"
                id="distance"
                name="distance"
                placeholder="0.0"
                size="5"
                required
                value="${(formdata.distance)!5.0}"
                spellcheck="false"
                autocomplete="off"
            >
            <label for="distance">km</label>
        </div>

        <#-- goal time -->
        <div class="input-group">
            <input
                type="text"
                inputmode="decimal"
                id="time"
                name="time"
                placeholder="0.0"
                size="5"
                required
                value="${(formdata.time)!30.0}"
                spellcheck="false"
                autocomplete="off"
            >
            <label for="time">minutes</label>
        </div>

        <#-- submit -->
        <div class="submit-group">
            <input class="submit" type="submit" value="Calculate">
        </div>
    </form>
</#macro>