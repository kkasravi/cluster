package com.intel.cluster.client

import akka.actor._
import com.typesafe.config.ConfigFactory
import akka.contrib.pattern.ClusterClient

object Main {

  def main(args : Array[String]) {
    val config = ConfigFactory.parseString("""
     akka {
       actor {
         provider = "akka.remote.RemoteActorRefProvider"
       }

       remote {
         transport = "akka.remote.netty.NettyRemoteTransport"
         log-remote-lifecycle-events = off
         netty.tcp {
          hostname = "127.0.0.1"
          port = 5000
         }
       }
     }""")

    val system = ActorSystem("OTHERSYSTEM", ConfigFactory.load(config))
    val initialContacts = Set(
      system.actorSelection("akka.tcp://ClusterSystem@127.0.0.1:2551/user/receptionist"),
      system.actorSelection("akka.tcp://ClusterSystem@127.0.0.1:3000/user/receptionist"))

    val c = system.actorOf(ClusterClient.props(initialContacts), "os-client")

    (1 to 1000).map { i =>
      c ! ClusterClient.Send("/user/master", s"hello - $i", localAffinity = true)
      c ! ClusterClient.Send("/user/member", s"hello - $i", localAffinity = true)

      Thread.sleep(1000)
    }
  }
}
