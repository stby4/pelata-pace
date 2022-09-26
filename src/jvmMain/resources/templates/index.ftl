<#-- @ftlvariable name="form" type="net.pelata.features.pace.data.Form" -->
<#-- @ftlvariable name="footer" type="net.pelata.features.pace.data.Footer" -->

<#import "_layout.ftl" as layout>
<#import "/partials/form.ftl" as formpartial>

<@layout.doc>
  <main>
    <section>
      <h2>Calculate your pace now</h2>
      <@formpartial.pace formdata=form />
      <p>Enter your race distance and goal time to get the required average pace and as well as possible negative splits.
      <p>Why don't you try to run a <a href="/pace/result?distance=42.195&time=119.67">marathon in 1:59:40,2</a> like Eliud Kipchoge did in 2019?</div>
    </section>
  </main>
</@layout.doc>
