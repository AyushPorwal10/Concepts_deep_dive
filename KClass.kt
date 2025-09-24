The KClass object gives you the power of reflection, the ability for a program to examine its own structure at runtime.
MyClass::class -> Kotlin's KClass
MyClass::class.java -> Java's Class



import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty


fun main() {
    
    inspectClass(Person::class)
}

// This function takes any KClass and prints its details
fun <T : Any> inspectClass(cls: KClass<T>) {
    
    println("Inspecting class: '${cls.simpleName}'")
    println("Members found: ${cls.members.size}\n")

    for (member in cls.members) {
        val memberType = when (member) {
            is KFunction<*> -> "Function"
            is KProperty<*> -> "Property"
            else -> "Other"
        }
        
        println(
            "- Name: ${member.name}, " +
            "Type: $memberType, " +
            "Returns: ${member.returnType}"
        )
    }
}

data class Person(val name: String, var age: Int) {
    fun greet() {
        println("Hello, my name is $name.")
    }

    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}

