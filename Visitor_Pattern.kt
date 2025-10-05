The Visitor pattern is a "helper" or a strategy that allows code to be scalable for new operations.

Expression classes (Number, Sum, etc.) as your core, stable foundation. We don't want to touch them. The Visitor pattern provides a clean way to build new functionality on top of that foundation without ever having to modify it.

Want to evaluate expressions? Add an EvaluationVisitor.

Want to print them? Add a PrintVisitor.

Want to count the nodes? Add a NodeCountVisitor

// Helper (We can make it generic also to support multiple types)

sealed class Expression {
    abstract fun accept(visitor: Visitor): Int
}

class Number(val value: Int) : Expression() {
    override fun accept(visitor: Visitor): Int = visitor.visitNumber(this)
}

class Sum(val left: Expression, val right: Expression) : Expression() {
    override fun accept(visitor: Visitor): Int = visitor.visitSum(this)
}

interface Visitor {
    fun visitNumber(number: Number): Int
    fun visitSum(sum: Sum): Int
}



// Now using this to add new Featur named  EvaluationVisitor

class EvaluationVisitor : Visitor {
    override fun visitNumber(number: Number): Int {
        return number.value
    }

   
    override fun visitSum(sum: Sum): Int {
        val leftValue = sum.left.accept(this)  
        val rightValue = sum.right.accept(this)
        return leftValue + rightValue
    }
}

// How it scales
Same we can do for PrintVisitor, and other


// Below code is perfect way to get advantages and disadvantage of Visitor Pattern
fun main() {
    val kotlin = "🙂"
    println(kotlin)
}


interface Expression {
    fun <T> accept(visitor : Visitor<T>) : T
}


interface Visitor<T>{
    fun visitNum(num : Number) : T
    fun visitSum(sum : Sum) : T
    
    // in future if i have to add mul than i have to modify all the implementation of visitor which is hard to do so this is disadvantage
    
//     fun visitMul(mul : Mul) : Int
}

// class Mul(val mul : Int) : Expression{
//     override fun accept(visitor : Visitor) : Int{
//         return visitor.visitMul(this)
//     }
// }

class Sum(val sum : Int) : Expression{
    override fun <T> accept(visitor : Visitor<T>) : T{
        return visitor.visitSum(this)
    }
}

class Number(val num : Int) : Expression {
    override fun <T> accept(visitor : Visitor<T>) : T{
        return visitor.visitNum(this)
    }
}

// similar to PrintVisitor any other can provides its own implementation without changing above code 
// this ensure Open/Close Principle because my code open for extension (new feature) and closed for modifications
class PrintVisitor : Visitor<String>{
    override fun visitNum(num : Number) : String {
        return num.num.toString()
    }
    override fun visitSum(sum : Sum) : String {
        return sum.sum.toString()
    }
}
