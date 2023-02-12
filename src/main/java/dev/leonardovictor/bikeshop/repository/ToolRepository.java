package dev.leonardovictor.bikeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.leonardovictor.bikeshop.model.Tool;

public interface ToolRepository extends JpaRepository<Tool, Long> {

    Iterable<Tool> findAllByAvailability(String availability);
    
}
