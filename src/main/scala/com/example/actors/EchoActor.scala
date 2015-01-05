package com.example.actors

import akka.actor.ActorLogging
import akka.camel.{CamelMessage, Consumer}

class EchoActor extends Consumer with ActorLogging {

  var tmpMsg = ""

  def receive = {
    // When we receive the 'Done' message, stop this actor
    // (which if this is still the initialActor will trigger the deathwatch and stop the entire ActorSystem)
    case camelMsg: CamelMessage =>
      val msg = camelMsg.bodyAs[String]
      if (msg != tmpMsg) {
        log.info("received %s" format msg)
        tmpMsg = msg
      }
  }

  def endpointUri: String = "stream:file?fileName=/Users/watanabemirai/WorkSpace/program/scala/BootableAkka/target/universal/stage/bin/in&scanStream=true&retry=true&scanStreamDelay=1000"
}

