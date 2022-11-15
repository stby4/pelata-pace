ARG runtime_base_tag=1.23-alpine
ARG build_base_tag=jdk17-alpine

FROM nginxinc/nginx-unprivileged:${runtime_base_tag} AS base
USER root
RUN adduser --disabled-password --gecos "" pelatauser

RUN apk add openjdk17-jre-headless supervisor
WORKDIR /app
RUN chown pelatauser /app
COPY --chown=pelatauser supervisord.conf /etc/supervisor/conf.d/supervisord.conf
COPY --chown=pelatauser build/libs/net.pelata.pace-all.jar .
COPY --chown=pelatauser etc /etc/

USER pelatauser

ENV PORT=5000

EXPOSE 443/tcp
EXPOSE 80/tcp

CMD /usr/bin/supervisord -c /etc/supervisor/conf.d/supervisord.conf