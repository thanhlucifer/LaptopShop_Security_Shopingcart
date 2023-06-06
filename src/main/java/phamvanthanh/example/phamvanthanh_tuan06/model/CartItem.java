package phamvanthanh.example.phamvanthanh_tuan06.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private long productId;
    private String name;
    private double price;
    private double quantity = 1;

}

