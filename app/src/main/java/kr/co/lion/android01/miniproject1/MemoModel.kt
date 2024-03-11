package kr.co.lion.android01.miniproject1

import androidx.room.Entity
import androidx.room.PrimaryKey

//테이블을 생성한다
@Entity(tableName = "MemoTable")

//주 생성자에 정의한 프러퍼티들이 컬럼으로 만들어 진다
data class MemoModel (

    @PrimaryKey(autoGenerate = true)
    var memoIdx:Int = 0,
    var title:String = "",
    var date:String = "",
    var contents:String = ""
)