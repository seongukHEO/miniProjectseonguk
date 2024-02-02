package kr.co.lion.android01.miniproject1

import android.os.Parcel
import android.os.Parcelable
import androidx.activity.result.contract.ActivityResultContracts


class MemoClass(var title:String?, var contect: String?, var currentTime:String?):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(contect)
        parcel.writeString(currentTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MemoClass> {
        override fun createFromParcel(parcel: Parcel): MemoClass {
            return MemoClass(parcel)
        }

        override fun newArray(size: Int): Array<MemoClass?> {
            return arrayOfNulls(size)
        }
    }
}