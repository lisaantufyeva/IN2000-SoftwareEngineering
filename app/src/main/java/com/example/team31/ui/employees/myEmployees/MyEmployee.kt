package com.example.team31.ui.employees.myEmployees

import android.os.Parcel
import android.os.Parcelable
import android.provider.ContactsContract
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Ansatt(var ansattId:String?, val navn:String?,val email:String?, val passord: String?, val rolle:String?, val adminId: String?):Serializable {
    constructor() : this("","1","e","e","e", "e")
}

/*

data class Ansatt(
    val navn: String?,
    val email: String?,
    val rolle: String?
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(navn)
        parcel.writeString(email)
        parcel.writeString(rolle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ansatt> {
        override fun createFromParcel(parcel: Parcel): Ansatt {
            return Ansatt(parcel)
        }

        override fun newArray(size: Int): Array<Ansatt?> {
            return arrayOfNulls(size)
        }
    }
}
*/