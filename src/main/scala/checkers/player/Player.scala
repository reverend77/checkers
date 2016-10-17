package checkers.player

/**
  * Created by Łukasz Marek on 17.10.2016.
  */
sealed trait Player {
  def next : Player = _
}
case object Human extends Player{
  override def next: Player = ArtificialIntelligence
}

case object ArtificialIntelligence extends Player{
  override def next: Player = Human
}
