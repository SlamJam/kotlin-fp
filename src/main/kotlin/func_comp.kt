package func_comp

fun <IN1, OUT1, OUT2> Function1<IN1, OUT1>.andThen(f: (OUT1) -> OUT2) = { arg: IN1 -> f(this(arg))}

fun main(args: Array<String>) {
    val add2 = { x: Int -> x + 2}
    val mul2 = { x: Int -> x * 2}

    val addAndMul = add2 andThen mul2
    val mulAndAdd = mul2 andThen add2

    println(addAndMul(2))
    println(mulAndAdd(2))
}