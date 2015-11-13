FROM java:7

ENV CONTAINERBUDDY_VERSION=0.0.1-alpha

RUN curl -Lo /tmp/containerbuddy-distro.tar.gz https://github.com/joyent/containerbuddy/releases/download/$CONTAINERBUDDY_VERSION/containerbuddy-$CONTAINERBUDDY_VERSION.tar.gz && \
    tar -xvzf /tmp/containerbuddy-distro.tar.gz -C /tmp && \
    mv /tmp/build/* /bin/

