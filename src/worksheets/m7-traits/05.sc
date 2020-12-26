case class Event(id: Int,
                 location: String,
                 dayOfWeek: String,
                 sessionTimeInSeconds: Integer,
                 source: String)

val e1 = Event(1, "US", "Sun", 10, "Twitter")
val e2 = Event(2, "China", "Mon", 10, "WeChat")
val e3 = Event(3, "New Zealand", "Sun", 10, "Twitter")
val e4 = Event(4, "India", "Wed", 10, "Facebook")
val e5 = Event(5, "UK", "Fri", 10, "LinkedIn")
val e6 = Event(6, "Norway", "Sat", 10, "Facebook")

trait EventInterface {
  def get(eventId: Int): Option[Event]

  def all: List[Event]
}

trait RichEventsInterface extends EventInterface {
  def getUniqueLocation: Set[String] = all.map(_.location).toSet

  def getUniqueSources: Set[String] = all.map(_.source).toSet

  def getAverageSessionTimeInSeconds: Float = all.map(_.sessionTimeInSeconds.toInt).sum / all.size
}

class Events(val events: List[Event]) extends RichEventsInterface {
  override def get(eventId: Int) =
    events.find(_.id == eventId)

  override def all = events
}

val events = new Events(List(e1, e2, e3, e4, e5, e6))
events.get(4)
events.getAverageSessionTimeInSeconds
events.getUniqueSources
events.getUniqueLocation