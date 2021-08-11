package com.care.banking.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.care.banking.ThreadExecutor

@Database(entities = arrayOf(Users::class, Transaction::class), version = 3, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun trasactionDao(): TransactionDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "word_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

                INSTANCE = instance

                instance
            }

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(INSTANCE!!)
            }
        }

        private fun populateDatabase(db: UserDatabase) {
            val userDao = db.userDao()
            ThreadExecutor.getInstance()?.diskIO?.execute(Runnable {
                kotlin.run {
                    userDao.insert(
                        Users(
                            "Agam Jyot Singh",
                            40000.00f,
                            "SPRK12345",
                            1452036541,
                            "6523012457",
                            "singhagamjyot@gmail.com",
                            "New Delhi"
                        )
                    )
                    userDao.insert(
                        Users(
                            "Harpreet Singh",
                            45000.00f,
                            "SPRK12345",
                            1452036542,
                            "1111111111",
                            "harpreets1602@gmail.com",
                            "New Delhi"
                        )
                    )
                    userDao.insert(
                        Users(
                            "Taranjot Singh",
                            50000.00f,
                            "SPRK12345",
                            1452036543,
                            "2222222222",
                            "taranjot11111@gmail.com",
                            "New Delhi"
                        )
                    )
                    userDao.insert(
                        Users(
                            "Anant Jain",
                            55000.00f,
                            "SPRK12345",
                            1452036544,
                            "3333333333",
                            "jain_anant@gmail.com",
                            "Ghaziabad"
                        )
                    )
                    userDao.insert(
                        Users(
                            "Ashmeet Singh",
                            60000.00f,
                            "SPRK12345",
                            1452036545,
                            "4444444444",
                            "ashmeets@gmail.com",
                            "New Delhi"
                        )
                    )
                    userDao.insert(
                        Users(
                            "Harshit Vishvakarma",
                            65000.00f,
                            "SPRK12345",
                            1452036546,
                            "5555555555",
                            "vharshit11@gmail.com",
                            "Varanasi"
                        )
                    )
                    userDao.insert(
                        Users(
                            "Ananya Nigam",
                            70000.00f,
                            "SPRK12345",
                            1452036547,
                            "6666666666",
                            "ananya@gmail.com",
                            "Kanpur"
                        )
                    )
                    userDao.insert(
                        Users(
                            "Raghav Dhingra",
                            75000.00f,
                            "SPRK12345",
                            1452036548,
                            "7777777777",
                            "raghavdhingra@gmail.com",
                            "Panipat"
                        )
                    )
                    userDao.insert(
                        Users(
                            "Kushdeep Walia",
                            80000.00f,
                            "SPRK12345",
                            1452036549,
                            "8888888888",
                            "skushdeep@gmail.com",
                            "New Delhi"
                        )
                    )
                    userDao.insert(
                        Users(
                            "Vaibhav Nangia",
                            85000.00f,
                            "SPRK12345",
                            1452036550,
                            "9999999999",
                            "nangiavaibhav@gmail.com",
                            "New Delhi"
                        )
                    )
                }
            })
        }
    }
}