ARG runtime_base_tag=1.23-alpine
ARG build_base_tag=jdk17-alpine

FROM nginxinc/nginx-unprivileged:${runtime_base_tag} AS base
USER root
RUN adduser --disabled-password --gecos "" pelatauser

RUN apk add openjdk17-jre-headless supervisor
WORKDIR /app
RUN chown pelatauser /app
RUN chown nginx /var/cache/nginx
COPY --chown=pelatauser supervisord.conf /etc/supervisor/supervisord.conf
COPY --chown=pelatauser build/libs/net.pelata.pace-all.jar .
COPY --chown=nginx etc /etc/

USER pelatauser

ENV PORT=8080

CMD ["/usr/bin/supervisord -c /etc/supervisor/supervisord.conf"]