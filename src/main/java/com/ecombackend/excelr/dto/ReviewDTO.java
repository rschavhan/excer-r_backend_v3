package com.ecombackend.excelr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
	
    private Long id;
    private String reviewerName;
    private String comment;
    private int rating;

    // Constructors, getters, and setters
}
