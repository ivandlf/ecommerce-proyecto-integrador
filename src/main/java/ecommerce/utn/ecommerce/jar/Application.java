package ecommerce.utn.ecommerce.jar;
// SDK de Mercado Pago
import com.mercadopago.MercadoPagoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		MercadoPagoConfig.setAccessToken("PROD_ACCESS_TOKEN");
		SpringApplication.run(Application.class, args);
	}

}
