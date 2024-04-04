package com.tredence.tredence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopperDto {
    @Id
    private String shopperId;
}
