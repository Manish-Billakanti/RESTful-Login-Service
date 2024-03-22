package com.example.loginDemo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.loginDemo.Model.userData;
import com.example.loginDemo.Repo.userRepo;

@Service
public class userService {

    @Autowired
    userRepo repo;

    public ResponseEntity<List<userData>> getAllUserData(){
        try {
            return new ResponseEntity<>(repo.findAllUsers(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<userData> getUserName(String name){
        
        try {
            userData user = repo.findByUserName(name);    
            if(user != null)
            {
                return new ResponseEntity<>(user,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(new userData(),HttpStatus.NOT_FOUND);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new userData(),HttpStatus.BAD_REQUEST);
       
    }

    public ResponseEntity<userData> createUser(userData user){
        
        try {
            return new ResponseEntity<>(repo.save(user),HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new userData(),HttpStatus.BAD_REQUEST);
    }

    
public ResponseEntity<String> login(String username, String password) {
    String response = null;
    try {
        userData user = repo.findByUserName(username);
            if( (user.getUserName().equals(username)) && (user.getPassword().equals(password) ))
            {
                response = "Login Successfull! Welcome "+user.getUserName();
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else{
                response = "Username/Password is wrong";
                return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
            }  
    } catch (Exception e) {
        e.printStackTrace();
    }
    return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
    
}

   
public ResponseEntity<String> updatePassword(String userName, String newPassword){
    
    try {

        userData u = repo.findByUserName(userName);
        u.setPassword(newPassword);
        repo.save(u);
        return new ResponseEntity<>("Password Updated",HttpStatus.ACCEPTED);

    } catch (Exception e) {
        e.printStackTrace();
    }

    return new ResponseEntity<>("User Not found",HttpStatus.NOT_FOUND);

}


}
