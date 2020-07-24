package vn.chohoa.flower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.chohoa.flower.model.Role;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.repository.RoleRepository;
import vn.chohoa.flower.repository.UserRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class FlowerApplication  {

//implements CommandLineRunner
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    public static void main(String[] args) {

        SpringApplication.run(FlowerApplication.class, args);
    }
//
//    @Autowired
//    UserRepository userRepository ;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        String pass= passwordEncoder.encode("1998");
//        List<Role> roles = Arrays.asList(roleRepository.findOne(1),roleRepository.findOne(2));
//        User u = User.builder().
//                userName("tinh")
//                .password(pass)
//                .roles(roles)
//                .build();
//
//        userRepository.save(u);
//        System.out.println(passwordEncoder.encode("1998"));
//    }
}
