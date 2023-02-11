package dev.leonardovictor.bikeshop.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tools")
@SequenceGenerator(name = "tool_id_seq", sequenceName = "tool_id_seq", allocationSize = 1)
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_id_seq")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Brand is mandatory")
    @Column(name = "brand")
    private String brand;

    @NotNull(message = "Availability is mandatory")
    @Enumerated(EnumType.STRING)
    private Availability availability = Availability.UNAVAILABLE;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "version")
    private int version = 1;
}
