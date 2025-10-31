
package com.example.parking.service;

import com.example.parking.model.ModelEntity;
import com.example.parking.repository.ModelRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ModelService {
    private final ModelRepository repo;
    public ModelService(ModelRepository repo){ this.repo = repo; }
    public List<ModelEntity> all(){ return repo.findAll(); }
    public ModelEntity save(ModelEntity m){ return repo.save(m); }
    public ModelEntity find(Long id){ return repo.findById(id).orElse(null); }
    public void delete(Long id){ repo.deleteById(id); }
}
