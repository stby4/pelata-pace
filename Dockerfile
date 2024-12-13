ARG runtime_base_tag=21-jre-headless-latest

FROM azul/zulu-openjdk-alpine:${runtime_base_tag} AS base
USER root
RUN adduser --disabled-password --gecos "" pelatauser

RUN apk add supervisor
WORKDIR /app
RUN chown pelatauser /app
COPY --chown=pelatauser build/libs/net.pelata.pace-all.jar .

USER pelatauser

ENV PORT=5000
EXPOSE 5000/tcp

CMD java -jar net.pelata.pace-all.jar
