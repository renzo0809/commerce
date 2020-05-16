package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Product {

    @ApiModelProperty(value="ID del Producto", dataType = "long", example="1", position=1)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value="Nombre del producto", dataType="String",example = "Jamón Ibérico", position=2)
    @NotEmpty(message = "El nombre no debe estar vacío")
    private String name;


    @ApiModelProperty(value="Descripcion del producto", dataType="String", example = "Delicioso", position=3)
    private String description;

    @ApiModelProperty(value="Stock del producto",dataType = "String",example="J30",position=4)
    @Positive(message = "El valor debe ser mayor")
    private Double stock;

    @ApiModelProperty(value="Precio del producto",dataType = "float",example="235.27", position=4)
    private Double price;
    private String status;

    @Column(name="create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @ApiModelProperty(value="Categoria del producto",dataType = "Categoria",position=7)
    @NotNull(message="la categoría no puede ser vacia")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private  Category category;
}
