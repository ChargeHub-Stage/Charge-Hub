package db.repository.user

import db.chargehub.User

interface UserRepository {

    fun create(user: User)

    fun getAll() : List<User>

}