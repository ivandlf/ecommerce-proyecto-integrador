package ecommerce.utn.ecommerce.jar.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceItem {

    private String name;
    private Integer quantity;
    private Float price;
}
