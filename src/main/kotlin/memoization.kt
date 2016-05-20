package memoization

class Memo<T, R>(val f: (T) -> R) : Function1<T, R> {

    val cache = hashMapOf<T, R>()
    override fun invoke(p1: T) = cache.getOrPut(p1, { f(p1) })

}

fun fib(x: Int): Int {
    println("Calculating $x")
    return when (x) {
        0, 1 -> 1
        else -> fib(x - 1) + fib(x - 2)
    }
}

val memo_fib = Memo<Int, Int>({ fib(it) })

fun main(args: Array<String>) {
    memo_fib(10)
}
