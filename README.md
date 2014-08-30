Cluster
========

Sets up simple cluster

###How to Build
  ```bash
  ## Build Gearpump
  sbt clean pack
  ```
  This will generate scripts under target/pack/bin

##How to Package for distribution
  ```bash
  ## Package Gearpump
  sbt clean pack-archive
  ```
  This will produce target/gearpump${version}.tar.gz which contains the ./bin and ./lib files.

##How to Install to /usr/local
  ```bash
  ## Run Build step above
  cd target/pack
  sudo make install PREFIX="/usr/local"
  ```
  This will install scripts to /usr/local/bin and jars to /usr/local/lib

###How to run after building
1. Start master, member and client
  ```bash
  ## Start master
  target/pack/bin/master
  ## Start member
  target/pack/bin/member
  ## Start client
  target/pack/bin/client
  ```
