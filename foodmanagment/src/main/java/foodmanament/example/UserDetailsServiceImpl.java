package foodmanament.example;

import foodmanament.example.Entity.Donor;
import foodmanament.example.Repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DonorRepository donorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Donor donor = donorRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new User(
                donor.getUsername(),
                donor.getPassword(),
                donor.isEnabled(),
                true, true, true, // accountNonExpired, credentialsNonExpired, accountNonLocked
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + donor.getRole()))
        );
    }
}
