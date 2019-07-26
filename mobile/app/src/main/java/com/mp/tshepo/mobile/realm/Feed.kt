package com.mp.tshepo.mobile.realm

import com.mp.tshepo.mobile.R
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by tshep on 14/05/2018.
 */
@RealmClass
open class Feed : RealmObject() {

    @PrimaryKey
    var id: Long = 0;
    var title: String = "Function";
    var output: String = R.string.calc_details.toString();
    var timeStamp: String = "5 ekseni";

}