package br.com.fiap.userms.service;

import br.com.fiap.userms.entity.User;
import br.com.fiap.userms.exceptions.UserNotFoundException;
import br.com.fiap.userms.records.UserRecord;
import br.com.fiap.userms.repository.AddressRepository;
import br.com.fiap.userms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AddressRepository addressRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRecord userRecord) {
        addressRepository.save(userRecord.address());
        User user = User.builder()
                .name(userRecord.name())
                .lastName(userRecord.lastName())
                .birthDate(userRecord.birthDate())
                .phoneNumber(userRecord.phoneNumber())
                .gender(userRecord.gender())
                .address(userRecord.address())
                .build();
        return userRepository.save(user);
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}