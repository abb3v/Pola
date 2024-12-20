package org.poach3r.frontend.classes

import frontend.functions.standardLibrary.Print
import org.poach3r.frontend.PCallable
import org.poach3r.frontend.functions.io.Println
import org.poach3r.frontend.functions.io.Readln
import kotlin.String

class IO(
    override val name: String = "IO",
    override val methods: HashMap<String, PCallable> = hashMapOf(
        "print" to Print(),
        "println" to Println(),
        "readln" to Readln(),
        "file" to File()
    ),
    override val arity: Int = 0,
    override val superclass: PClass? = null,
) : PNativeClass