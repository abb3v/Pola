package org.poach3r.frontend.functions.arrays

import org.poach3r.frontend.Interpreter

class Set(
    override val arity: Int = 3
) : ArrayFunc {
    override fun call(
        interpreter: Interpreter,
        arguments: List<Any>
    ): Any {
        val list = getList(arguments[0])
        val index = getIndex(list, arguments[1])

        list[index] =
            if (arguments[0] is String)
                arguments[2].toString()
            else
                arguments[2]

        return getResult(list, arguments[0], interpreter)
    }
}