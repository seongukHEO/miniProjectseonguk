package kr.co.lion.android01.miniproject1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//entities : entitiy들을 지정한다. 지정한 entitiy하나당 하나의 테이블이 생성된다
@Database(entities = [MemoModel::class], version = 1)
abstract class MemoDataBase : RoomDatabase() {

    //dao를 지정한다
    abstract fun memoDao() : MemoDao

    companion object{
        //데이터 베이스 객체를 담을 변수
        var memoDataBase:MemoDataBase? = null

        @Synchronized
        fun getInstance(context: Context):MemoDataBase? {
            if (memoDataBase == null){
                synchronized(MemoDataBase::class.java){
                    memoDataBase = Room.databaseBuilder(
                        context.applicationContext, MemoDataBase::class.java, "memo.db"
                    ).build()
                }
            }
            return memoDataBase
        }

    }
}