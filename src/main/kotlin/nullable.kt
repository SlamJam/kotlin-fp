package nullable

data class Pet(val name: String?)

data class Person(val pet: Pet?)

inline fun <T> ifLucky(body: () -> T) =
        if (Math.random() < 0.5) body()
        else null

fun createName() = ifLucky { "name" }

fun createPet() = ifLucky { Pet(createName()) }

fun createPerson() = ifLucky { Person(createPet()) }

inline fun <T : Any> T?.filter(f: (T) -> Boolean): T? = if (this != null && f(this)) this else null

fun main(args: Array<String>) {
    val person = createPerson()
    person?.let {
        it.pet?.let {
            it.name?.let {
                println("#1: $it")
            }
        }
    }

    val existsPerson = Person(createPet())
    person.filter { it.pet != null }?.
            let { println("2: $it") }

    val personsPersNameLength = person?.let { it.pet }?.let {  it.name?.length() }
    val nameLengthStr = personsPersNameLength ?: "unknown"
    println("3: $nameLengthStr")

    if (person != null) {
        println("4: ${person.pet}")
    }
}





