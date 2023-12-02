fun main() {
    Counter().let {
        it.increment()
        println(it.getCount())
    }
}
class Counter {
    private var count = 0

    fun increment() {
        synchronized(this) {
            count++
        }
    }

    fun getCount(): Int {
        synchronized(this) {
            return count
        }
    }
}