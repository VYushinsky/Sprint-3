package ru.sber.io

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
/**
 * Метод, который архивирует файл logfile.log в архив logfile.zip.
 * Архив должен располагаться в том же каталоге, что и исходной файл.
 */

class Archivator {

    fun zipLogfile() {
        try{
            val fis = FileInputStream("io/logfile.log")
            val zos = ZipOutputStream(FileOutputStream("io/logfile.zip"))
            val entry = ZipEntry("io/logfile.log")
            var buffer = ByteArray(fis.available())

            fis.use { fis.read(buffer) }

            zos.use {
                zos.putNextEntry(entry)
                zos.write(buffer)
                zos.closeEntry()
            }

        } catch(ex : Exception){
            System.out.println(ex.message)
           }
    }


    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохранить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {
        try{
            val zis = ZipInputStream(FileInputStream("io/logfile.zip"))
            val fos = FileOutputStream("io/unzippedLogfile.log")
            var buffer = ByteArray(zis.available())

            zis.use { zis.nextEntry
                buffer = zis.readBytes()
                zis.closeEntry()
            }

            fos.use { fos.write(buffer) }

        } catch(ex : Exception){
            System.out.println(ex.message)
        }
    }
}




