package com.intel.cluster.member

import akka.actor._

class Member extends Actor with ActorLogging {
  def receive = {
    case e =>
      log.info(s"from member : $e : $sender")
      sender ! "member : how are you?"
  }
}
