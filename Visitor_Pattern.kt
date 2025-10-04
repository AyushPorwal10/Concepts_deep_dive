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
