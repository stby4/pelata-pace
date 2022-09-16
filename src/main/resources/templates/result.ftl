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

    <section>
      <p>
        <span class="highlight">${result.average.minutes?string["##0"]}:${result.average.seconds?string["00"]} / km</span>
        <br/>
        Average pace. That's equal to a speed of ${result.averageSpeed?string["##0.0"]} km/h.
      </p>
      <h2>Negative splits</h2>
      <p>
        <caption>Calculated negative splits for your optimal race in minutes and seconds per kilometer:</caption>
      </p>
      <div class="wrapper">
        <table>
          <thead>
            <tr>
              <#list result.distances as distance>
                <td>${distance?string["0.0"]} km</td>
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
  </main>
</@layout.doc>
