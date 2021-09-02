package ru.sber.oop

open class Room(val name: String, val size: Int) {

    protected open val dangerLevel = 5

    fun description() = "Room: $name"

    open fun load() = "Nothing much to see here..."

    constructor() : this(name = "name", size = 100)

    var Gena :  ru.sber.oop.Monster = Goblin("Gena", "GoblinTheCrocodile", "Tears", 111)
}

class TownSquare() : Room("Town Square", 1000){

    override val dangerLevel: Int = super.dangerLevel - 3

    final override fun load() = "He is on the tower!"
}


/*Создайте подкласс класса ru.sber.oop.Room - TownSquare c именем "Town Square" и размером 1000.
Переопределите в новом классе функцию load() (придумайте строку для загрузки).
Переопределите dangerLevel в TownSquare, так чтобы сделать уровень угрозы на 3 пункта меньше среднего.
В классе ru.sber.oop.Room предоставить доступ к этой переменной только для наследников.
Запретите возможность переопределения функции load() в классе TownSquare.
Создайте в классе ru.sber.oop.Room вторичный конструктор, который бы инициализировал имя и задавал размер по умолчанию 100.
Добавьте в класс ru.sber.oop.Room поле типа Monster и инициализируйте его экземпляром класса Goblin.*/