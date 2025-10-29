package foodmanament.example.Service;

import foodmanament.example.Entity.User;
import foodmanament.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ➕ Add a new user
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // 🔍 Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 🔍 Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // ✏️ Update user
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    user.setContactNo(updatedUser.getContactNo());
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    // ❌ Delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
