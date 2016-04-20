import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ImmutableQueueSuite extends FunSuite {
  test("Queue basic functionality") {
    val q1 = new ImmutableQueue[Int]()
    assert(q1.isEmpty)

    val q2 = q1.enQueue(5)
    assert(q2.head === Some(5) )

    val q3 = q2.deQueue()
    assert(q3.isEmpty)

    val q4 = new ImmutableQueue[Int]().enQueue(1).enQueue(2).enQueue(3)
    assert(q4.head === Some(1))

    val q5 = q4.deQueue()
    assert(q5.head === Some(2))

    val q6 = q5.deQueue()
    assert(q6.head === Some(3))

    assert (q6.deQueue().isEmpty)
  }

  // TODO Test other data types and boundary cases
}
