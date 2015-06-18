package seq

interface Seq<out T>

object nil : Seq<Nothing>

class Cons<T>(val head: T, val tail: () -> Seq<T>) : Seq<T>

fun natural(from: Int): Seq<Int> = Cons(from, { natural(from + 1) })

tailRecursive fun <T> Seq<T>.forEach(body: (T) -> Unit) {
    when (this) {
        is Cons -> {
            body(this.head)
            this.tail().forEach(body)
        }
    }
}

fun Seq<*>.take(count: Int): Seq<*> {
    fun impl(seq: Seq<*>, rem: Int): Seq<*> =
            when {
                seq == nil, rem == 0 -> nil
                seq is Cons -> Cons(seq.head, { -> impl(seq.tail(), rem - 1) })
                else -> throw AssertionError("Unexpected value")
            }

    return impl(this, count)
}

fun main(args: Array<String>) {
    natural(0).take(10).forEach { println(it) }
}