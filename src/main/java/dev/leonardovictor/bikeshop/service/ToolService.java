package dev.leonardovictor.bikeshop.service;

import dev.leonardovictor.bikeshop.model.Tool;

public interface ToolService {
    Tool createTool(Tool tool);
    Tool getToolById(long toolId);
    Iterable<Tool> getToolsByAvailability(String availability);
    Iterable<Tool> getTools();
    Tool updateTool(long toolId, Tool tool);
    void deleteToolById(long toolId);
    void deleteTools();
}
