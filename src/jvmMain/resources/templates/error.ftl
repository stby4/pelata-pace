
<#import "_layout.ftl" as layout>

<@layout.doc>
  <style>
  img.logo {
    animation-duration: 2.8s;
    animation-delay: 2s;
    animation-name: falldown;
    animation-iteration-count: 1;
    animation-fill-mode: forwards;
    transform-origin: bottom left;
    animation-timing-function: cubic-bezier(.26,.58,.36,.87);
  }

  @keyframes falldown {
    from {
        transform: rotate(0);
    }
    20% {
        transform: rotate(0.002turn);
    }

    60% {
        transform: rotate(0.13turn);
    }

    70% {
        transform: rotate(0.105turn);
    }

    80% {
        transform: rotate(0.13turn);
    }

    90% {
        transform: rotate(0.12turn);
    }

    to {
        transform: rotate(0.13turn);
    }
  }
  </style>
  <main>
    <section>
        <h2>Ooops</h2>
        <p>An error occurred.</p>
        <#if message??>
            <p>${message}</p>
        </#if>
        <div>
            <a href="/" class="input submit">
                Return to the homepage
            </a>
        </div>
    </section>
  </main>
</@layout.doc>