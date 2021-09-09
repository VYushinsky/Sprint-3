package ru.sber.nio

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.*
class Grep {
    /**
     * Реализовать простой аналог утилиты grep с использованием калссов из пакета java.nio.
     * Метод должен выполнить поиск подстроки subString во всех файлах каталога logs.
     * Каталог logs размещен в данном проекте (io/logs) и внутри содержит другие каталоги.
     * Результатом работы метода должен быть файл в каталоге io(на том же уровне, что и каталог logs), с названием result.txt.
     * Формат содержимого файла result.txt следующий:
     * имя файла, в котором найдена подстрока : номер строки в файле : содержимое найденной строки
     * Результирующий файл должен содержать данные о найденной подстроке во всех файлах.
     * Пример для подстроки "22/Jan/2001:14:27:46":
     * 22-01-2001-1.log : 3 : 192.168.1.1 - - [22/Jan/2001:14:27:46 +0000] "POST /files HTTP/1.1" 200 - "-"
     */
    fun find(subString: String) {
        val path = "io/logs/result.txt"
        val path1 = Paths.get("io\\logs")

        val result = File(path)
        result.delete()
        result.createNewFile()

        result.outputStream().use {fos -> Files.walk(path1).filter{it.toString().endsWith(".log")}
            .forEach { logf -> logf.useLines { it.filter { it.contains(subString) }
                .forEach { w -> fos.write(("${logf.name} : ${logf.useLines {it.indexOf(w) + 1}} : $w \n").toByteArray())}
                }
            }
        }
    }
}
fun main(){
    val p1 = Grep()
    p1.find("66.54.0.1")
}
