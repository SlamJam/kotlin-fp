package nullable

data class Pet(val name: String?)

data class Person(val pet: Pet?)

inline fun <T : Any> ifLucky(body: () -> T): T? =
        if (Math.random() < 0.5) body()
        else null

fun createName() = ifLucky { "name" }

fun createPet() = ifLucky { Pet(createName()) }

fun createPerson() = ifLucky { Person(createPet()) }

inline fun <T : Any> T?.filter(f: (T) -> Boolean) = if (this != null && f(this)) this else null

fun main(args: Array<String>) {
    val person: Person? = createPerson()
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

    val personPetNameLength = person?.pet?.name?.length()
    val nameStr: Any = personPetNameLength ?: "unknown"
    println("#3: nameStr")

    when (person) {
        is Person -> println("#4: $person")
    }
}
















