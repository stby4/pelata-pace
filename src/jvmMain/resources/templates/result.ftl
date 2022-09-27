<#-- @ftlvariable name="result" type="net.pelata.features.pace.data.Result" -->
<#-- @ftlvariable name="form" type="net.pelata.features.pace.data.Form" -->
<#-- @ftlvariable name="footer" type="net.pelata.features.pace.data.Footer" -->

<#import "_layout.ftl" as layout>
<#import "/partials/form.ftl" as formpartial>

<@layout.doc isSmallHeader=true>
  <main>
    <section>
      <@formpartial.pace formdata=form />
    </section>

    <#if result?has_content>
      <section>
        <p>
          <span class="highlight">${result.average.minutes?string["##0"]}:${result.average.seconds?string["00"]} / ${result.unit.short}</span>
          <br/>
          Average pace. That's equal to <#if result.isFast()>an incredible speed<#else>a speed</#if> of ${result.averageSpeed?string["##0.0"]}
          <#if "MILES" == result.unit.name()>mph<#else>km/h</#if>.
        </p>
        <h2>Negative splits</h2>
        <p>
          <caption id="table-splits-caption">Calculated negative splits for your optimal race in minutes and seconds per kilometer:</caption>
        </p>
        <div class="wrapper">
          <table role="grid" aria-labelledby="table-splits-caption">
            <thead>
              <tr>
                <#list result.distances as distance>
                  <td>${distance?string["0.0"]} ${result.unit.short}</td>
                </#list>
              </tr>
            </thead>
            <tbody>
              <#list result.splits as splits>
                <tr>
                  <#list splits as split>
                    <td>${split.minutes?string["##0"]}:${split.seconds?string["00"]}</td>
                  </#list>
                </tr>
              </#list>
            </tbody>
          </table>
        </div>
      </section>
    </#if>
  </main>
</@layout.doc>
