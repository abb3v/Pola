package org.poach3r.frontend.functions

import org.poach3r.errors.ReturnError
import org.poach3r.frontend.Interpreter
import org.poach3r.frontend.PCallable

class Return(
    override val arity: Int = 1
) : PCallable {
    override fun call(
        interpreter: Interpreter,
        arguments: List<Any>
    ): Any {
        throw ReturnError(arguments[0])
    }
}