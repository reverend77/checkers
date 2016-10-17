package checkers.actors.states

/**
  * Created by Łukasz Marek on 17.10.2016.
  */
sealed trait State
case object Uninitialized extends State
case object Waiting extends State

