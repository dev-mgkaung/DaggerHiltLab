package mk.learner.daggerhiltlab.datas.source.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MovieLocalDataSource internal constructor(
    private  val movieDao : MovieDao,
    private  val  ioDispatcher: CoroutineDispatcher = Dispatchers.IO
)