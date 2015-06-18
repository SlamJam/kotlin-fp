package memo

class Memo<T, R>(val f: (T) -> R) : Function1<T, R> {

    val cache = hashMapOf<T, R>()
    override fun invoke(arg: T) = cache.getOrPut(arg) { f(arg) }

}

val fib: (Int) -> Int = Memo<Int, Int>({ x ->
    println("Calculating $x")
    when (x) {
        0, 1 -> 1
        else -> fib(x - 1) + fib(x - 2)
    }
})

fun main(args: Array<String>) { println(fib(100)) }