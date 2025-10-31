
package com.example.parking;

import com.example.parking.model.*;
import com.example.parking.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final ModelRepository modelRepo;
    private final AutomobileRepository autoRepo;
    private final PasswordEncoder encoder;

    public DataInitializer(RoleRepository roleRepo, UserRepository userRepo, ModelRepository modelRepo, AutomobileRepository autoRepo, PasswordEncoder encoder){
        this.roleRepo = roleRepo; this.userRepo = userRepo; this.modelRepo = modelRepo; this.autoRepo = autoRepo; this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleRepo.findByName("READ_ONLY").isEmpty()) roleRepo.save(new Role(null, "READ_ONLY"));
        if(roleRepo.findByName("USER").isEmpty()) roleRepo.save(new Role(null, "USER"));
        if(roleRepo.findByName("ADMIN").isEmpty()) roleRepo.save(new Role(null, "ADMIN"));

        var adminRole = roleRepo.findByName("ADMIN").get();
        var userRole = roleRepo.findByName("USER").get();
        var readRole = roleRepo.findByName("READ_ONLY").get();

        if(userRepo.findByUsername("admin").isEmpty()){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin"));
            admin.getRoles().add(adminRole);
            admin.getRoles().add(userRole);
            userRepo.save(admin);
        }

        if(userRepo.findByUsername("user").isEmpty()){
            User u = new User();
            u.setUsername("user");
            u.setPassword(encoder.encode("user"));
            u.getRoles().add(userRole);
            userRepo.save(u);
        }

        if(userRepo.findByUsername("guest").isEmpty()){
            User g = new User();
            g.setUsername("guest");
            g.setPassword(encoder.encode("guest"));
            g.getRoles().add(readRole);
            userRepo.save(g);
        }

        if(modelRepo.count()==0){
            modelRepo.save(new ModelEntity(null, "Toyota Camry", 3000000.0));
            modelRepo.save(new ModelEntity(null, "BMW X5", 5500000.0));
            modelRepo.save(new ModelEntity(null, "Kia Rio", 1200000.0));
            modelRepo.save(new ModelEntity(null, "Audi A6", 4800000.0));
            modelRepo.save(new ModelEntity(null, "Lada Vesta", 1100000.0));
        }

        if(autoRepo.count()==0){
            User admin = userRepo.findByUsername("admin").orElse(null);
            User user = userRepo.findByUsername("user").orElse(null);
            User guest = userRepo.findByUsername("guest").orElse(null);
            var camry = modelRepo.findAll().stream().filter(m->m.getName().contains("Camry")).findFirst().orElse(null);
            var x5 = modelRepo.findAll().stream().filter(m->m.getName().contains("X5")).findFirst().orElse(null);
            var rio = modelRepo.findAll().stream().filter(m->m.getName().contains("Rio")).findFirst().orElse(null);
            var a6 = modelRepo.findAll().stream().filter(m->m.getName().contains("A6")).findFirst().orElse(null);
            var vesta = modelRepo.findAll().stream().filter(m->m.getName().contains("Vesta")).findFirst().orElse(null);

            if(camry!=null) autoRepo.save(new Automobile(null, camry, admin));
            if(x5!=null) autoRepo.save(new Automobile(null, x5, admin));
            if(rio!=null) autoRepo.save(new Automobile(null, rio, user));
            if(a6!=null) autoRepo.save(new Automobile(null, a6, admin));
            if(vesta!=null) autoRepo.save(new Automobile(null, vesta, guest));
        }
    }
}
