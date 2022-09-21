<#-- @ftlvariable name="formdata" type="net.pelata.features.pace.data.Form" -->

<#macro pace formdata>
    <script src="/static/paceform.js"></script>
    <form action="/pace/result" method="get">
        <#-- distance -->
        <div>
            <div <#if formdata.errors?? && formdata.errors['.distance']??>class="input-group error"<#else>class="input-group"</#if>>
                <input
                    type="text"
                    inputmode="decimal"
                    id="distance"
                    name="distance"
                    placeholder="0.0"
                    size="8"
                    required
                    value="${(formdata.distance)!5.0}"
                    spellcheck="false"
                    autocomplete="off"
                >
                <label for="distance">km</label>
            </div>
            <#if formdata.errors?? && formdata.errors['.distance']??>
                <p class="error-message">
                    ${formdata.errors['.distance']?cap_first}.
                </p>
            </#if>
        </div>

        <#-- goal time -->
        <div>
            <div <#if formdata.errors?? && formdata.errors['.time']??>class="input-group time error"<#else>class="input-group time"</#if>>
                <input
                    type="text"
                    inputmode="numeric"
                    id="hours"
                    name="h"
                    placeholder="hh"
                    size="2"
                    required
                    <#--  value="${(formdata.time)!30.0}"  -->
                    spellcheck="false"
                    autocomplete="off"
                >
                :
                <input
                    type="text"
                    inputmode="numeric"
                    id="minutes"
                    name="m"
                    placeholder="mm"
                    size="2"
                    required
                    <#--  value="${(formdata.time)!30.0}"  -->
                    spellcheck="false"
                    autocomplete="off"
                >
                :
                <input
                    type="text"
                    inputmode="numeric"
                    id="seconds"
                    name="s"
                    placeholder="ss"
                    size="2"
                    required
                    <#--  value="${(formdata.time)!30.0}"  -->
                    spellcheck="false"
                    autocomplete="off"
                >
                <label for="seconds">time</label>
            </div>
            <#if formdata.errors?? && formdata.errors['.time']??>
                <p class="error-message">
                    ${formdata.errors['.time']?cap_first}.
                </p>
            </#if>
        </div>

        <#-- submit -->
        <div>
            <div class="submit-group">
                <input class="submit" type="submit" value="Calculate">
            </div>
        </div>
    </form>
</#macro>