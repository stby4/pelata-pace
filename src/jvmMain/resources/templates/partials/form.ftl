<#-- @ftlvariable name="formdata" type="net.pelata.features.pace.data.Form" -->

<#macro pace formdata>
    <form id="paceform" action="/pace/result" method="get">
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
                    pattern="^[0-9,]+\.?[0-9]*$"
                    title="Distance in kilometers. Decimal values are allowed."
                    required
                    value="${(formdata.distance)!5.0}"
                    spellcheck="false"
                    autocomplete="off"
                    enterkeyhint="done"
                    aria-label="Distance in kilometers. Decimal values are allowed."
                    aria-labelledby="unit"
                >
                <select class="label" name="unit" id="unit" aria-label="Distance unit.">
                    <option value="KILOMETERS" <#if formdata.unit == "KILOMETERS">selected</#if>>km</option>
                    <option value="MILES" <#if formdata.unit == "MILES">selected</#if>>mi</option>
                </select>
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
                    pattern="^[0-9]{0,2}$"
                    title="Target time hours. Values from 0 to 99 are valid. Optional."
                    value="${(formdata.hours?string['00'])!00}"
                    spellcheck="false"
                    autocomplete="off"
                    enterkeyhint="done"
                    aria-labelledby="time-label"
                >
                :
                <input
                    type="text"
                    inputmode="numeric"
                    id="minutes"
                    name="m"
                    placeholder="mm"
                    size="2"
                    pattern="^[0-9]{0,2}$"
                    title="Target time minutes. Values from 0 to 59 are valid. Optional."
                    value="${(formdata.minutes?string['00'])!30}"
                    spellcheck="false"
                    autocomplete="off"
                    enterkeyhint="done"
                    aria-labelledby="time-label"
                >
                :
                <input
                    type="text"
                    inputmode="numeric"
                    id="seconds"
                    name="s"
                    placeholder="ss"
                    size="2"
                    pattern="^[0-9]{0,2}$"
                    title="Target time seconds. Values from 0 to 59 are valid. Optional."
                    value="${(formdata.seconds?string['00'])!00}"
                    spellcheck="false"
                    autocomplete="off"
                    enterkeyhint="done"
                    aria-labelledby="time-label"
                >
                <label id="time-label" class="label" for="seconds">time</label>
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