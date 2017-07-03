package com.uzias.runrunpocket.tasks.data.repository.datasource.realm

import com.uzias.runrunpocket.tasks.data.repository.datasource.RealmDatasource
import com.uzias.runrunpocket.tasks.data.repository.datasource.realm.mapper.RealmTaskMapper
import com.uzias.runrunpocket.tasks.data.repository.datasource.realm.model.RealmTask
import com.uzias.runrunpocket.tasks.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Observable
import io.realm.Realm

class RealmDatasourceImpl : RealmDatasource {

    override fun saveTasks(tasks: List<Task>): Observable<List<Task>> {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(RealmTaskMapper.transformFromMutableList(tasks.toMutableList(), true))
        realm.commitTransaction()
        realm.close()
        return Observable.just(tasks)
    }

    override fun updateTask(task: Task): Completable {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val result  = realm.where(RealmTask::class.java)
                .findAll()
        result.forEach { it.working = false}
        realm.copyToRealmOrUpdate(result)
        realm.copyToRealmOrUpdate(RealmTaskMapper.transformFrom(task))
        realm.commitTransaction()
        realm.close()
        return Completable.complete()
    }


    override fun deleteTask(id: Long): Completable {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val realmTask = realm.where(RealmTask::class.java)
                .equalTo("id", id)
                .findAll()
        realmTask.deleteAllFromRealm()
        realm.commitTransaction()
        realm.close()
        return Completable.complete()
    }

    override fun getTasks(): Observable<List<Task>> {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val list = realm.copyFromRealm(realm.where(RealmTask::class.java).findAllSorted("order").toList())
        realm.commitTransaction()
        realm.close()
        return if (list.isEmpty()) Observable.empty()
        else Observable.just(RealmTaskMapper.transformFromMutableList(list))
    }

    override fun deleteAllTasks(): Completable {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val realmTask = realm.where(RealmTask::class.java)
                .findAll()
        realmTask.deleteAllFromRealm()
        realm.commitTransaction()
        realm.close()
        return Completable.complete()
    }

}