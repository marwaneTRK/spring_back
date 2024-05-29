package com.example.demo.repository;

import com.example.demo.entity.Tache;
import com.example.demo.enums.StatusEnum.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {
    Tache findByStatus(Status status);
}
