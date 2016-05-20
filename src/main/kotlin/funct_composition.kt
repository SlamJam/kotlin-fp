infix fun <IN1, OUT1, OUT2> ((IN1) -> OUT1).andThen(f: (OUT1) -> OUT2) = { arg: IN1 -> f(this(arg)) }

fun main(args: Array<String>) {
    val add2 = { x: Int -> x + 2 }
    val mul2 = { x: Int -> x * 2 }

    val addAndMultiply = add2 andThen mul2
    val multiplyAndAdd = mul2 andThen add2

    println(addAndMultiply(2))
    println(multiplyAndAdd(2))
}
