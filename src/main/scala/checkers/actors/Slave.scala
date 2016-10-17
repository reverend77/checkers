package checkers.actors

import akka.actor.{FSM, PoisonPill}
import checkers.actors.states.{State, Uninitialized, Waiting}
import checkers.message._

/**
  * Created by Łukasz Marek on 17.10.2016.
  */
class Slave extends FSM[State,Message]{

  startWith(Uninitialized,NullMessage)

  when(Uninitialized){
    case Event(OrderMessage(move,board,player),_) =>
      val toCheck = spawnChildren
      goto(Waiting) using StateHolder(toCheck,sender,List())
    case _ => null
  }

  when(Waiting){
    case Event(msg @ ReplyMessage(move,summary),StateHolder(1,parent,answers)) =>{
      parent ! summarize
      self ! PoisonPill
      stay
    }
    case Event(msg @ReplyMessage(move,summary),StateHolder(remaining,parent,answers)) =>{
      stay using StateHolder(remaining - 1,parent,msg :: answers)
    }
    case _ => null
  }



  private def spawnChildren:Long = 0
  private def summarize:ReplyMessage = null

}
