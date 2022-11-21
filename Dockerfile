ARG runtime_base_tag=3.17

FROM alpine:${runtime_base_tag} AS base
USER root
RUN adduser --disabled-password --gecos "" pelatauser

RUN apk add openjdk17-jre-headless supervisor
WORKDIR /app
RUN chown pelatauser /app
COPY --chown=pelatauser build/libs/net.pelata.pace-all.jar .

USER pelatauser

ENV PORT=5000
EXPOSE 5000/tcp

CMD java -jar net.pelata.pace-all.jar
