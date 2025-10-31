
package com.example.parking.service;

import com.example.parking.model.Automobile;
import com.example.parking.model.User;
import com.example.parking.repository.AutomobileRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AutomobileService {
    private final AutomobileRepository repo;
    public AutomobileService(AutomobileRepository repo){ this.repo = repo; }
    public List<Automobile> all(){ return repo.findAll(); }
    public List<Automobile> byOwner(User u){ return repo.findByOwner(u); }
    public Automobile save(Automobile a){ return repo.save(a); }
    public void delete(Long id){ repo.deleteById(id); }
    public Automobile find(Long id){ return repo.findById(id).orElse(null); }
}
