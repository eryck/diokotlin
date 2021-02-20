package collections

class Repositorio<T> {
    private val map = mutableMapOf<String, T>()

    fun creat(id: String, value: T){
        map[id] = value
    }

    fun findById(id: String)= map[id]
}