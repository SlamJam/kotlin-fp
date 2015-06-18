package curryng

// (c) https://github.com/MarioAriasC/funKTionale
public fun<P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R {
    return { p1: P1 -> { p2: P2 -> this(p1, p2) } }
}

public fun<P1, P2, R> ((P1) -> (P2) -> R).uncurried(): (P1, P2) -> R {
    return { p1: P1, p2: P2  -> this(p1)(p2) }
}

fun main(args: Array<String>) {
    val adder = { arg1: Int, arg2: Int -> arg1 + arg2}
    val curriedAdder = adder.curried()
    val add2 = curriedAdder(2)
    println(add2(2))
    val uncurriedAdder = curriedAdder.uncurried()
    println(uncurriedAdder(2, 2))
}
