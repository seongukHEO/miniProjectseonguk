package kr.co.lion.android01.miniproject1

import android.os.Parcel
import android.os.Parcelable

class MemoClass2(var newTitle:String?, var newContent:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(newTitle)
        parcel.writeString(newContent)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MemoClass2> {
        override fun createFromParcel(parcel: Parcel): MemoClass2 {
            return MemoClass2(parcel)
        }

        override fun newArray(size: Int): Array<MemoClass2?> {
            return arrayOfNulls(size)
        }
    }
}