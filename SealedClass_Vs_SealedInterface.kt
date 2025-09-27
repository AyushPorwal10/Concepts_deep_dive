Sealed classes can have constructors with parameters, while sealed interfaces cannot.
Sealed classes can have abstract methods and properties, while sealed interfaces can only have abstract methods.

Sealed classes can be extended by classes, objects, and other sealed classes, while sealed interfaces can only be implemented by classes and objects.
Using above feature power -> 

  sealed class AuthState {
    object LoggedOut : AuthState()  // by object

    sealed class LoggedIn(val user: User) : AuthState() { // by other sealed classes
        class Verified(user: User) : LoggedIn(user)

        class NeedsTwoFactorAuth(user: User, val token: String) : LoggedIn(user) // by class
    }
}

Sealed classes are often used in conjunction with when expressions to provide exhaustive pattern matching.


