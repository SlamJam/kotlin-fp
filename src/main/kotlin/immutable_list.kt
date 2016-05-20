package list

interface List<out T>

object nil : List<Nothing>

class Cons<out T>(val head: T, val tail: List<T>) : List<T>

infix fun <T> T.cons(tail: List<T>) = Cons(this, tail)

fun size(lst: List<*>): Int = when (lst) {
    is Cons -> 1 + size(lst.tail)
    else -> 0
}


fun List<*>.trSize(): Int {
    tailrec fun impl(lst: List<*>, size: Int): Int = when (lst) {
        is Cons -> impl(lst.tail, size + 1)
        else -> size
    }

    return impl(this, 0)
}

tailrec fun <T> List<T>.forEach(body: (T) -> Unit) {
    when (this) {
        is Cons -> {
            body(this.head)
            this.tail.forEach(body)
        }
    }
}

fun main(args: Array<String>) {
    val lst = "1" cons ("2" cons (3 cons nil))
    println(size(lst))

    var bigLst: List<Int> = nil
    (1..100000).forEach {
        bigLst = it cons bigLst
    }
    println(bigLst.trSize())

    var sum = 0
    bigLst.forEach { sum += it }
    println(sum)
}
