ARG runtime_base_tag=1.23-alpine
ARG build_base_tag=jdk17-alpine

FROM nginx:${runtime_base_tag} AS base
RUN adduser --disabled-password --gecos "" pelatauser

RUN apk add openjdk17-jre-headless supervisor
WORKDIR /app
RUN chown pelatauser /app
COPY --chown=pelatauser supervisord.conf /etc/supervisor/supervisord.conf
COPY --chown=pelatauser build/libs/net.pelata.pace-all.jar .

USER pelatauser

ENV PORT=8080
EXPOSE 8080

CMD ["/usr/bin/supervisord -c /etc/supervisor/supervisord.conf"]