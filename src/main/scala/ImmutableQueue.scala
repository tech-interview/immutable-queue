trait Queue[T] {
  def isEmpty: Boolean
  def enQueue(t: T): Queue[T]
  def deQueue(): Queue[T]
  def head: Option[T]
}

object Queue {
  def empty[T]: Queue[T] = EmptyQueue.asInstanceOf[Queue[T]]
  private object EmptyQueue extends ImmutableQueue[Nothing](List.empty) { }
}

class ImmutableQueue[T](val elem: List[T] = List.empty[T]) extends Queue[T] {
  def isEmpty: Boolean = elem.isEmpty
  def enQueue(t: T): Queue[T] = new ImmutableQueue[T](t :: elem)
  def deQueue(): Queue[T] = elem match {
      // TODO Should throw exception when Queue is empty
    case Nil => new ImmutableQueue[T]()
      // TODO Inefficient solution, can be optimized to have O(1) most of the time
    case l: List[T] => new ImmutableQueue[T](l.reverse.tail.reverse).asInstanceOf[Queue[T]]
  }

  def head: Option[T] = elem match {
    case Nil => None
    case l: List[T] => Some(l.last)
  }
}
