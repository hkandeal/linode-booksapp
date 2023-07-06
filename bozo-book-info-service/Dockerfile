FROM --platform=linux/amd64 registry.access.redhat.com/ubi8/ubi-minimal:8.5-243.1651231653
WORKDIR /work/
RUN chown 1001 /work \
    && chmod "g+rwX" /work \
    && chown 1001:root /work
COPY --chown=1001:root target/bookinfoservice-1.0.0-SNAPSHOT-runner /work/bookinfoservice

EXPOSE 8080
USER 1001

CMD ["./bookinfoservice", "-Dquarkus.http.host=0.0.0.0"]
