package funct_compositon

fun <IN1, OUT1, OUT2> ((IN1) -> OUT1).times(f: (OUT1) -> OUT2) = { arg: IN1 -> f(this(arg)) }

fun main(args: Array<String>) {
    val add2 = { x: Int -> x + 2 }
    val mul2 = { x: Int -> x * 2 }

    val addAndMul = add2 * mul2
    val mulAndAdd = mul2 * add2

    println(addAndMul(2))
    println(mulAndAdd(2))
}