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

class Cons<T>(override val head: T, override val tail: List<T>) : List<T>

fun <T> T.cons(tail: List<T>) = Cons(this, tail)

fun List<*>.size(): Int = when (this) {
    nil -> 0
    else -> 1 + this.tail.size()
}

fun List<*>.trSize(): Int {
    @tailRecursive fun impl(lst: List<*>, size: Int): Int = when (lst) {
        nil -> size
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
    val lst = 1 cons (2 cons (3 cons nil))
    println(lst.size())

    var bigList: List<Int> = nil
    (1..10000).forEach {
        bigList = it cons bigList
    }
    println(bigList.trSize())

    lst.forEach { println(it) }
}















