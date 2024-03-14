package db.repository.user

import db.chargehub.User
import db.networking.request.CreateUserRequest

interface UserRepository {

    fun create(user: CreateUserRequest)

    fun getAll() : List<User>

}