package com.intel.cluster

import akka.actor._
import com.typesafe.config.ConfigFactory
import akka.contrib.pattern.{ClusterClient, ClusterReceptionistExtension}

object Main {

  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.parseString("""
     akka {
       actor {
         provider = "akka.cluster.ClusterActorRefProvider"
       }
       remote {
         transport = "akka.remote.netty.NettyRemoteTransport"
         log-remote-lifecycle-events = off
         netty.tcp {
           hostname = "127.0.0.1"
           port = 2551
         }
       }

       cluster {
         seed-nodes = [
           "akka.tcp://ClusterSystem@127.0.0.1:2551"
           ]

         roles = [master]

         auto-down = on
       }
     }""")

    val system = ActorSystem("ClusterSystem", ConfigFactory.load(config))
    val master = system.actorOf(Props[Master], "master")
    ClusterReceptionistExtension(system).registerService(master)
  }

}
