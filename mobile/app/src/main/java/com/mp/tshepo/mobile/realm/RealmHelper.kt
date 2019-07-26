package com.mp.tshepo.mobile.realm

import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.ArrayList

/**
 * Created by tshep on 14/05/2018.
 */

open class RealmHelper() {

    fun initialize(dbName: String): Realm {

        val config = RealmConfiguration.Builder().name(dbName).build()
        return Realm.getInstance(config)
    }


    fun create(realm: Realm, f: Feed, id: Long) {
        realm.beginTransaction()

        val feed = realm.createObject(Feed::class.java, id)
        //feed.id = f.id
        feed.title = f.title
        feed.output = f.output
        feed.timeStamp = f.timeStamp

        realm.commitTransaction()
    }


    fun read(realm: Realm):ArrayList<Feed> {
        val feeds = realm.where(Feed::class.java).findAll()
        val arrFeeds = ArrayList<Feed>()
        for (feed in feeds) {
            arrFeeds.add(feed)
            println(feed.timeStamp)
        }
        return arrFeeds;
    }
}