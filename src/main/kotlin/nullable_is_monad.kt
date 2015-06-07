package nullable_is_monad

data class Pet(val name: String?)

data class Person(val pet: Pet?)

inline fun <T> ifLucky(body: () -> T) =
        if (Math.random() < 0.5) body()
        else null

fun createName() = ifLucky { "name" }

fun createPet() = ifLucky { Pet(createName()) }

fun createPerson() = ifLucky { Person(createPet()) }

public inline fun <T : Any> T?.filter(f: (T) -> Boolean): T? = if (this != null && f(this)) this else null

fun main(args: Array<String>) {

    val person = createPerson()
    println("#0: $person")

    person?.let {
        it.pet?.let {
            it.name?.let {
                println("#1: $it")
            }
        }
    }
    person.filter { it.pet != null }?.
            let { println("#2: $it") }

    val personsPetsNameLength = person?.let { it.pet }?.let { it.name?.length() }
    val nameLengthStr = "Name length = ${personsPetsNameLength ?: "undefined"}"
    println("#3: $nameLengthStr")

    val moreLikelyPerson = createPerson() ?: createPerson() ?: createPerson()
    when (moreLikelyPerson) {
        null -> println("#4: Not now:(")
        is Person -> println("#4: I have the $moreLikelyPerson")
    }

}