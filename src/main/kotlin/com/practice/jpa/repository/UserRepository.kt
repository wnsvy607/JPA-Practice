package com.practice.jpa.repository

import com.practice.jpa.entity.User
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @Cacheable(cacheNames = ["User"], cacheManager = "localCacheManager")
    override fun findAll(): MutableList<User>
}
