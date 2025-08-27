package br.com.alura.orgs.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {

    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS `User` (
            `id` TEXT NOT NULL, 
            `name` TEXT NOT NULL, 
            `password` TEXT NOT NULL, PRIMARY KEY(`id`))
            """)
    }
}