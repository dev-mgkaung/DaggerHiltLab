package mk.learner.daggerhiltlab.utils


data class Results<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Results<T> {
            return Results(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Results<T> {
            return Results(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Results<T> {
            return Results(Status.LOADING, data, null)
        }

    }

}
enum class Status
{
    SUCCESS,
    ERROR,
    LOADING
}