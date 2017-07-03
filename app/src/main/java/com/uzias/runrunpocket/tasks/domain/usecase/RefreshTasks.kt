package com.uzias.runrunpocket.tasks.domain.usecase

import com.uzias.runrunpocket.core.domain.UseCase
import com.uzias.runrunpocket.tasks.domain.model.Task
import io.reactivex.Observable

interface RefreshTasks: UseCase<Observable<List<Task>>>