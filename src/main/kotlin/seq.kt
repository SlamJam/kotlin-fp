package seq

interface Seq<out T>

object nil : Seq<Nothing>

class Cons<T>(val head: T, val tail: () -> Seq<T>) : Seq<T>

fun <T> T.cons(tail: () -> Seq<T>) = Cons(this, tail)

fun natural(from: Int): Seq<Int> = from cons { natural(from + 1) }

tailRecursive fun <T> Seq<T>.forEach(body: (T) -> Unit) {
    when (this) {
        is Cons -> {
            body(this.head)
            this.tail().forEach(body)
        }
    }
}

fun <T> Seq<T>.take(count: Int): Seq<T> {
    fun impl(seq: Seq<T>, rem: Int): Seq<T> = when {
        seq == nil, rem == 0 -> nil
        seq is Cons -> seq.head cons { -> impl(seq.tail(), rem - 1)}
        else -> throw AssertionError("Should not happen")
    }
    return impl(this, count)
}

fun main(args: Array<String>) {
    natural(0).take(10).forEach { println(it) }
}