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
  import Queue.empty

  def isEmpty: Boolean = elem.isEmpty

  def enQueue(t: T): Queue[T] = new ImmutableQueue[T](t :: elem)

  def deQueue(): Queue[T] = elem match {
      // TODO Should throw an exception when the Queue is empty?
    case Nil => empty[T]
      // TODO Can be optimized further to have O(1) most of the time
    case l: List[T] => new ImmutableQueue[T](l.take(l.size - 1)).asInstanceOf[Queue[T]]
  }

  def head: Option[T] = elem match {
    case Nil => None
    case l: List[T] => Some(l.last) // The list is reversed
  }
}
