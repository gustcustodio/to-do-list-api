package com.gustcustodio.to_do_list_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDTO {

    private Long id;

    @Size(min = 5, max = 30, message = "The title needs to be between 5 and 30 characters")
    @NotBlank(message = "Required field")
    private String title;

    @Size(min = 5, max = 80, message = "The description needs to be between 5 and 80 characters")
    @NotBlank(message = "Required field")
    private String description;

}
