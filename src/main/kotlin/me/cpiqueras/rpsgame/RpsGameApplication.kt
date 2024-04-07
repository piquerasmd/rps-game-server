package me.cpiqueras.rpsgame

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RpsGameApplication

fun main(args: Array<String>) {
	runApplication<RpsGameApplication>(*args)
}
