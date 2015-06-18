package adt

open class Name

data class FirstName(val name: String) : Name()

data class FullName(val firstName: String, val middleName: String, val lastName: String) : Name()

data class VkWomanName(val firstName: String, val nickName: String, val lastName: String, val maidenName: String) : Name()

fun printName(name: Name) {

    when (name) {
        is FirstName -> println(name.name)
        is FullName -> println("${name.lastName} ${name.firstName} ${name.middleName}")
        is VkWomanName -> {
            val (firstName, nickName, lastName, maidenName) = name
            println("$firstName $nickName $lastName ($maidenName)")
        }
    }

}

fun main(args: Array<String>) {
    printName(FirstName("Aleksey"))
}
