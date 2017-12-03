from clojure:alpine

WORKDIR /app

RUN apk update

RUN wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein && \
  chmod a+x ./lein

CMD ["./lein", "repl"]

