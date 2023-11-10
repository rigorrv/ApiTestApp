fun main() {
    val unique = intArrayOf(3, 9, 3, 7, 9, 2, 1, 2, 1)
    findUnique(unique)
    println(palindrome("civic"))
    println(primes(100))
    println(findPrime(5))
    fibonacci(30)
    fizzBuzz(50)
}

fun fizzBuzz(num: Int) {
    println()
    for (i in 1..num) {
        when {
            i % 5 == 0 && i % 3 == 0 -> print("FizzBuzz, ")
            i % 3 == 0 -> print("Fizz, ")
            i % 5 == 0 -> print("Buzz, ")
            else -> print("$i, ")
        }
    }
}

fun fibonacci(num: Int) {
    var t1 = 0
    var t2 = 1
    for (i in 0 until num) {
        print("$t1, ")
        val sum = t1 + t2
        t1 = t2
        t2 = sum
    }
}

fun findPrime(num: Int): Boolean {
    if (num <= 1) {
        return false
    }
    for (i in 2 until num) {
        if (num % i == 0) {
            return false
        }
    }
    return true
}

fun primes(num: Int) = (2 until num).filter { i -> (2 until i).none { i % it == 0 } }

fun palindrome(s: String): Boolean {
    var start = 0
    var end = s.length - 1
    while (start < end) {
        if (s[start] != s[end]) {
            return false
        }
        start++
        end--
    }
    return true
}

fun findUnique(unique: IntArray) {
    unique.toList().groupingBy { it }.eachCount().toSortedMap().forEach {
        if (it.value == 1) {
            println("${it.key} <= unique")
        } else {
            println(it.key)
        }
    }
}
