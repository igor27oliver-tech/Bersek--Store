package DTO;

import com.example.demo2.model.Sacola;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PayResponse {
 private SacolaResponse sacolaResponse;
 private int valorpago;
 private  WayToPay wayToPay;
}
