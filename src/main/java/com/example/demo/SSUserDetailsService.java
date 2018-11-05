package com.example.demo;


import com.example.demo.Classes.Role;
import com.example.demo.Classes.User;
import com.example.demo.Repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;
    public SSUserDetailsService(UserRepo userRepo) {

        this.userRepo = userRepo;
    }

     @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{


        try{
            User User = userRepo.findByUsername(username);
            if(User ==null){
                System.out.println("User not found with the provided username"+ User.toString());
                return null;
            }

            System.out.println("user from username "+ User.toString());
            return new org.springframework.security.core.userdetails.User(User.getUsername(),User.getPassword(),getAuthorities(User));

        }catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
     }
     private Set<GrantedAuthority> getAuthorities(User User){
        Set<GrantedAuthority>authorities = new HashSet<GrantedAuthority>();
        for(Role role:User.getRoles()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
         System.out.println("User authorities are "+ authorities.toString());

        return authorities;
     }

}
