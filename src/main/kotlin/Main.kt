package org.poach3r

import org.poach3r.backend.Parser
import org.poach3r.backend.Scanner
import org.poach3r.errors.ArgError
import org.poach3r.errors.PError
import org.poach3r.frontend.Interpreter
import org.poach3r.frontend.Resolver
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    Main.main(args)
}

object Main {
    val scanner = Scanner()
    val parser = Parser()
    val interpreter = Interpreter()
    val resolver = Resolver(interpreter)
    var config: Config? = null

    fun main(args: Array<String>) {
        try {
            config = Config.of(args)
        } catch(e: ArgError) {
            System.err.println(e.message)
            exitProcess(1)
        }

        if (config!!.file == null)
            shell()
        else
            file(config!!.file!!)
    }

    fun file(source: File) {
        interpret(source.readLines().joinToString("\n"))
    }

    fun shell() {
        while (true) {
            print("$ ")
            interpret(readln())
        }
    }

    private fun interpret(source: String) {
        try {
            val statements = parser.parse(scanner.scanTokens(source))
            resolver.resolve(statements)
            interpreter.interpret(statements)
        } catch (e: PError) {
            System.err.println(e.message)

            if (config!!.printStackTrace)
                e.printStackTrace()
        } catch (e: Error) {
            e.printStackTrace()
            println() // HACK without these printlns the prompt is on the line with the error message, dunno why
        }
    }
}