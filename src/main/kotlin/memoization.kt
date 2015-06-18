package memoization

class Memo<T, R>(val f: (T) -> R) : Function1<T, R> {

    val cache = hashMapOf<T, R>()
    override fun invoke(p1: T) = cache.getOrPut(p1, { -> f(p1) })

}

//val fib: (Int) -> Int = { x ->
val fib: (Int) -> Int = Memo<Int, Int> { x ->
    println("Calculating $x")
    when (x) {
        0, 1 -> 1
        else -> fib(x - 1) + fib(x - 2)
    }
}

fun main(args: Array<String>) {
    fib(10)
}