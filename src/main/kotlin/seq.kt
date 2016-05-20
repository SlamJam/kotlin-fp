package seq

interface Seq<out T>

object nil : Seq<Nothing>

class Cons<T>(val head: T, val tail: () -> Seq<T>) : Seq<T>

infix fun<T> T.cons(tail: () -> Seq<T>): Seq<T> = Cons(this, tail)

fun natural(from: Int): Seq<Int> = from cons { natural(from + 1) }

fun <T> Seq<T>.take(count: Int): Seq<T> {
    fun impl(seq: Seq<T>, rem: Int): Seq<T> = when {
        seq == nil || rem == 0 -> nil
        seq is Cons -> seq.head.cons({ -> impl(seq.tail(), rem - 1) })
        else -> throw AssertionError("Unexpected state")
    }
    return impl(this, count)
}

tailrec fun <T> Seq<T>.forEach(body: (T) -> Unit) {
    when (this) {
        is Cons -> {
            body(this.head)
            this.tail().forEach(body)
        }
    }
}

fun main(args: Array<String>) {

    natural(0).take(10).forEach { println(it) }
}