package ecommerce.utn.ecommerce.jar.controllers;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import ecommerce.utn.ecommerce.jar.dto.PreferenceItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Value("${mercadopago.access_token}") // Lee el token desde la configuración de Spring Boot
    private String accessToken;

    @PostMapping("/create-preference")
    public String createPreference(@RequestBody PreferenceItem preferenceItem) {
        MercadoPagoConfig.setAccessToken(accessToken);

        try {
            // Aquí configura la preferencia de acuerdo a tus necesidades // Puedes proporcionar la preferencia aquí


            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id("1234")
                            .title(preferenceItem.getName())
                            .description("PS5")
                            .pictureUrl("http://picture.com/PS5")
                            .categoryId("games")
                            .quantity(preferenceItem.getQuantity())
                            .currencyId("ARS")
                            .unitPrice(new BigDecimal(preferenceItem.getPrice()))
                            .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items).build();
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);


            // Genera el código de Checkout Pro
            String checkoutProCode = preference.getInitPoint();

            return checkoutProCode;
        } catch (MPException | MPApiException e) {
            // Manejo de excepciones
            e.printStackTrace();
            return "Error al crear la preferencia.";
        }
    }
}
