package me.cpiqueras.rpsgame.service

import me.cpiqueras.rpsgame.model.User
import me.cpiqueras.rpsgame.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) : UserService {
    @Transactional
    override fun saveUser(user: User): User {
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    @Transactional(readOnly = true)
    override fun getUserById(id: Long): User? = userRepository.findById(id).orElse(null)

    @Transactional(readOnly = true)
    override fun getUserByUsername(username: String): User? = userRepository.findByUsername(username)
}