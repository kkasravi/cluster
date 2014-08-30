package com.intel.cluster

import akka.actor._

class Master extends Actor with ActorLogging {
  def receive = {
    case e =>
      log.info(s"from master : $e : $sender")
      sender ! "master : how are you?"
  }
}
