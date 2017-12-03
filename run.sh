docker build -t clojure-post .

docker run \
  -v ${PWD}/src:/app/src:ro \
  -it clojure-post

