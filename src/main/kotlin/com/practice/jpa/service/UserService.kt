package com.practice.jpa.service

import com.practice.jpa.dto.UserSignUpRequest
import com.practice.jpa.dto.UserUpdateRequest
import com.practice.jpa.entity.User
import com.practice.jpa.repository.UserRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class UserService(
    val userRepository: UserRepository,
    val em: EntityManager
) {

    @Transactional
    fun signUp(request: UserSignUpRequest): User {
        return userRepository.save(User(name = request.name, age = request.age));
    }

    @Transactional
    fun update(request: UserUpdateRequest) {
        val user = getById(request.id)
//        em.merge(user)

        user.name = request.name
        user.age = request.age
    }

    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    fun getById(id: Long): User {
        return getAll().find { it.id == id } ?: throw RuntimeException("User not found")
    }

}
