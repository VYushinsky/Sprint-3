package ru.sber.oop

data class User(val name: String, val age: Long) {
    lateinit var city: String
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is User){
            return false
        }else return name == other.name && age == other.age && city == other.city

    }
}


fun main() {
    val user1 = User("Alex", 13)
    user1.city  = "Omsk"
    val user2 = user1.copy(name = "Alice")
    val user3 = user1.copy()
    user3.city  = "Omsk"
    print(user3.equals(user1))
}

/*Создайте user2, изменив имя и используя функцию copy()
Измените город user1 на 'Omsk' и создайте копию user1 - user3, только с городом 'Tomsk'.
Сравните user1 и user3, используя функцию equals(). Исправьте class User так, чтобы функция equals выдавала правильный ответ.
 */