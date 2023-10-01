package br.com.fiap.userms.service;

import br.com.fiap.userms.entity.User;
import br.com.fiap.userms.exceptions.DatabaseException;
import br.com.fiap.userms.exceptions.UserNotFoundException;
import br.com.fiap.userms.records.UserRecord;
import br.com.fiap.userms.repository.AddressRepository;
import br.com.fiap.userms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AddressRepository addressRepository;

    @Transactional
    public User create(UserRecord userRecord) {
        log.info("START - [USER] - create");
        try {
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
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            log.info("FINAL - [USER] - create");
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        log.info("START - [USER] - findById - PARAMETER[{}]", id);
        try {
            return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        } finally {
            log.info("FINAL - [ADDRESS] - findById - PARAMETER[{}]", id);
        }

    }

    @Transactional(readOnly = true)
    public Page<UserRecord> findAll(Pageable pageable) {
        log.info("START - [USER] - findAll");
        try {
            return new PageImpl<>(userRepository.findAll(pageable).stream()
                    .map(user -> new UserRecord(
                            user.getName(),
                            user.getLastName(),
                            user.getBirthDate(),
                            user.getPhoneNumber(),
                            user.getGender(),
                            user.getAddress()))
                    .toList());
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            log.info("FINAL - [USER] - findAll");
        }

    }

    @Transactional
    public void deleteById(Long id) {

        log.info("START - [USER] - delete - PARAMETER[{}}]", id);

        try {
            findById(id);
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            log.info("FINAL - [USER] - delete - PARAMETER[{}}]", id);
        }

    }

    public User update(Long userId, UserRecord userRecord) {
        try {
            User existingUser = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));

            // Atualize os campos usando lambda e stream
            BeanUtils.copyProperties(userRecord, existingUser, "id", "registrationDate");

            return userRepository.save(existingUser);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            log.info("FINAL - [USER] - update - PARAMETER[{}}]", userId);
        }

    }


}