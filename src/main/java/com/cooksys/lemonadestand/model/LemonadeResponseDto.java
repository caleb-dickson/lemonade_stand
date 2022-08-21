package com.cooksys.lemonadestand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LemonadeResponseDto {
    private Long id;
    private double price;
}
