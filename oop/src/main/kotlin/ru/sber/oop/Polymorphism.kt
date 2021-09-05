package ru.sber.oop

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = (0 .. 50).random()

    fun attack(opponent: Fightable): Int
}

class Player(val name: String, val isBlessed: Boolean, override val powerType: String, override var healthPoints: Int): Fightable {

    override fun attack(opponent: Fightable): Int {
        if (isBlessed) {
            opponent.healthPoints = opponent.healthPoints - damageRoll * 2
            return opponent.healthPoints
        } else {
            opponent.healthPoints = opponent.healthPoints - damageRoll
            return opponent.healthPoints
        }
    }
}

abstract class Monster(val name: String, val description: String, override val powerType: String, override var healthPoints: Int): Fightable{
    override fun attack(opponent: Fightable): Int {
        opponent.healthPoints = opponent.healthPoints - damageRoll
        return opponent.healthPoints
    }
}

class Goblin(name: String, description: String, powerType: String, healthPoints: Int): Monster(name, description, powerType,
    healthPoints){
    override val damageRoll: Int
        get() = super.damageRoll / 2
}
/*Добавьте метод чтения по умолчанию для поля damageRoll, которое возвращает радномное число.

Реализуйте класс Player, имплементирующий интерфейс ru.sber.oop.Fightable с дополнительным полем name (строка) и isBlessed.
attack уменьшает здоровье оппоненту на damageRoll, если isBlessed = false, и удвоенный damageRoll, если isBlessed = true.
Результат функции attack - количество урона, которое нанес объект класса Player.

Реализуйте абстрактный класс Monster, имплементирующий интерфейс ru.sber.oop.Fightable со строковыми полями name и description.
Логика функции attack, такая же как и в предыдущем пункте, только без учета флага isBlessed (которого у нас нет).

Реализуйте наследника класса Monster - класс Goblin. Переопределите в нем метод чтения damageRoll (допустим, он в два раза меньше сгененрированного рандомного значения).

Добавьте в класс ru.sber.oop.Room поле типа Monster и инициализируйте его экземпляром класса Goblin.

Добавьте функцию-расширение к классу Monster, getSalutation() - которое выдает приветствтие монстра и вызовите ее в функции load() класса ru.sber.oop.Room.*/

