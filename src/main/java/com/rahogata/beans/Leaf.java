package com.rahogata.beans;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A leaf bean.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leaf {

    private int id;
    @NotBlank(message = "Name can not be blank.")
    private String name;
    @NotBlank(message = "Color can not be blank.")
    private String color;
}
