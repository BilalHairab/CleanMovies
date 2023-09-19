package com.bilal.domain.usecases;

import com.bilal.domain.entities.DataHolder
import com.bilal.domain.repo.BaseRepository


/**
 * Created by Bilal Hairab on 15/09/2023.
 */
abstract class BaseUseCase<in Params, T : Any>(private val repository: BaseRepository) {

    abstract suspend operator fun invoke(params: Params): DataHolder<T>
}