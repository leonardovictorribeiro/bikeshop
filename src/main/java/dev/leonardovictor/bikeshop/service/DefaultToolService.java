package dev.leonardovictor.bikeshop.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.leonardovictor.bikeshop.exception.ToolNotFoundException;
import dev.leonardovictor.bikeshop.model.Tool;
import dev.leonardovictor.bikeshop.repository.ToolRepository;

@Service
public class DefaultToolService implements ToolService {
    @Autowired
    private ToolRepository toolRepository;

    @Override
    public Tool createTool(Tool tool) {
        tool.setCreatedAt(LocalDateTime.now());
        tool.setUpdatedAt(LocalDateTime.now());
        return toolRepository.save(tool);
    }

    @Override
    public Tool getToolById(long toolId) {
        return toolRepository.findById(toolId).orElseThrow(() -> new 
        ToolNotFoundException(toolId));
    }

    @Override
    public Iterable<Tool> getToolsByAvailability(String availability) {
        return toolRepository.findAllByAvailability(availability);
    }

    @Override
    public Iterable<Tool> getTools() {
        return toolRepository.findAll();
    }

    @Override
    public Tool updateTool(long toolId, Tool tool) {
        Tool existingTool = toolRepository.findById(toolId).orElseThrow(() -> new ToolNotFoundException(toolId));
        existingTool.setName(tool.getName());
        existingTool.setBrand(tool.getBrand());
        existingTool.setAvailability(tool.getAvailability());
        existingTool.setVersion(existingTool.getVersion() + 1);
        existingTool.setUpdatedAt(LocalDateTime.now());
        return toolRepository.save(existingTool);
    }

    @Override
    public void deleteToolById(long toolId) {
        toolRepository.findById(toolId).orElseThrow(() -> new 
        ToolNotFoundException(toolId));
        toolRepository.deleteById(toolId);
    }

    @Override
    public void deleteTools() {
        toolRepository.deleteAll();
    }
}
