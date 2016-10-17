package checkers.message

import akka.actor.ActorRef
import checkers.game.{Board, Move}
import checkers.player.Player

/**
  * Created by Łukasz Marek on 17.10.2016.
  */
sealed trait Message
case object NullMessage extends Message
final case class StateHolder(remaining:Long,sender:ActorRef,answers:List[ReplyMessage]) extends Message
final case class OrderMessage(move:Move,board:Board,player:Player) extends Message
final case class ReplyMessage(move:Move,summary:Map[Player,Long]) extends Message

