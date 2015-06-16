package list

interface List<out T> {

    val head: T
    val tail: List<T>

}

object nil : List<Nothing> {

    override val head: Nothing
        get() = throw UnsupportedOperationException()

    override val tail: List<Nothing>
        get() = throw UnsupportedOperationException()

}

class Cons<out T>(
        override val head: T,
        override val tail: List<T>
) : List<T>

fun <T> T.cons(tail: List<T>) = Cons(this, tail)

fun List<*>.size(): Int = when (this) {
    nil -> 0
    else -> 1 + this.tail.size()
}

fun List<*>.trSize(): Int {
    @tailRecursive fun impl(lst: List<*>, size: Int): Int = when (lst) {
        is nil -> size
        else -> impl(lst.tail, size + 1)
    }
    return impl(this, 0)
}

tailRecursive fun <T> List<T>.forEach(body: (T) -> Unit) {
    when (this) {
        is Cons -> {
            body(this.head)
            this.tail.forEach(body)
        }
    }
}
fun main(args: Array<String>) {
    val list = 1 cons (2 cons (3 cons nil))
    println(list.trSize())
    list.forEach {
        println(it)
    }
}
