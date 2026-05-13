package com.educandoweb.course.services;

import com.educandoweb.course.dto.UserPutRequest;
import com.educandoweb.course.dto.UserRequest;
import com.educandoweb.course.dto.UserResponse;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.infra.exceptions.DatabaseException;
import com.educandoweb.course.infra.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserResponse> findAll(){
        return repository.findAll().stream().map(UserResponse::new).toList();
    }

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(UserRequest user){
        return repository.save(new User(null, user.name(), user.email(), user.phone(), user.password()));
    }

    public void delete(Long id){
         try {
             repository.deleteById(id);
         }catch (EmptyResultDataAccessException e){
             throw new ResourceNotFoundException(id);
         }catch (DataIntegrityViolationException e){
             throw new DatabaseException(e.getMessage());
         }

    }

    public User update(UserPutRequest obj){
        try {
            var entity = repository.getReferenceById(obj.id());
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(obj.id());
        }

    }

    private void updateData(User entity, UserPutRequest obj) {
        if(obj.name() != null){
            entity.setName(obj.name());
        }

        if(obj.email() != null){
            entity.setEmail(obj.email());
        }

        if (obj.phone() != null){
            entity.setPhone(obj.phone());
        }

        if (obj.password() != null){
            entity.setPassword(obj.password());
        }

    }
}
