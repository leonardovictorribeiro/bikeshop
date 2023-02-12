package dev.leonardovictor.bikeshop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.leonardovictor.bikeshop.model.Tool;
import dev.leonardovictor.bikeshop.service.ToolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tools/")
@Tag(name = "Tool Controller", description = "This REST controller provide services to manage tools in the Oficina application") 
public class ToolController {
    @Autowired
    private ToolService toolService;

    @GetMapping(headers = "X-API-VERSION=v1")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides all tools available in the Oficina application")
    public Iterable<Tool> getAllTools() {
        return toolService.getTools();
    }

    @GetMapping(headers = "X-API-VERSION=v1", path =  "{id}")
    @Operation(summary = "Provides tool details for the supplied tool id from the Oficina application")
    public Tool getToolById(@PathVariable("id") long toolId) {
        return toolService.getToolById(toolId);
    }

    @GetMapping(headers = "X-API-VERSION=v1", path = "/availability/{status}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides tool details for the supplied tool availability from the Oficina application") 
    public Iterable<Tool> getToolByAvailability(@PathVariable("status") String availability) {
        return toolService.getToolsByAvailability(availability);
    }

    @PostMapping(headers = "X-API-VERSION=v1")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Creates a new tool in the Oficina application") 
    public Tool createTool(@Valid @RequestBody Tool tool) {
        return toolService.createTool(tool);
    }

    @PutMapping(headers = "X-API-VERSION=v1", path = "{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Updates the tool details in the Oficina application for the supplied tool id") 
    public Tool updateTool(@PathVariable("id") long toolId, @RequestBody Tool tool) {
        return toolService.updateTool(toolId, tool);
    }

    @DeleteMapping(headers = "X-API-VERSION=v1", path = "{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes the tool details for the supplied tool id from the Oficina application") 
    void deleteToolById(@PathVariable("id") long toolId) {
        toolService.deleteToolById(toolId);
    }

    @DeleteMapping(headers = "X-API-VERSION=v1")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes all tools from the Oficina application")
    void deleteTools() {
        toolService.deleteTools();
    }
}
