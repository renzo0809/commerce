package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El numero de documento no puede ser vacio")
    @Size(min=8,max=8,message = "El tamaño del número de documento es 8")
    @Column(name="numer_id",unique = true,length = 8,nullable = false)
    private String numberId;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name="first_name",nullable = false)
    private String firstName;


    @NotEmpty(message = "El apellido no puede ser vacio")
    @Column(name="last_name",nullable = false)
    private String lastName;

    @NotEmpty(message = "El correo no puede ser vacio")
    @Email(message="No es una direccion de correo bien formado")
    @Column(unique=true,nullable = false)
    private String email;

    @Column(name="photo_url")
    private String photoUrl;
    private String state;

    @NotEmpty(message = "La region no puede ser vacia")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Region region;
}
