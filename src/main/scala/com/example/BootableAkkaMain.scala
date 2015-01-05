package com.example

import akka.actor.{ActorSystem, Props}
import akka.kernel.Bootable
import com.example.actors.EchoActor

/**
 * This is actually just a small wrapper around the generic launcher
 * class akka.Main, which expects only one argument: the class name of
 * the application?s main actor. This main method will then create the
 * infrastructure needed for running the actors, start the given main
 * actor and arrange for the whole application to shut down once the main
 * actor terminates.
 *
 * Thus you could also run the application with a
 * command similar to the following:
 * java -classpath  akka.Main com.example.actors.HelloWorldActor
 *
 * @author alias
 */
class BootableAkkaMain extends Bootable {

  val system = ActorSystem("BootableAkka")

  override def startup(): Unit = {
    system.actorOf(Props[EchoActor], "echo")
  }

  override def shutdown(): Unit = {
    system.shutdown()
  }


}

