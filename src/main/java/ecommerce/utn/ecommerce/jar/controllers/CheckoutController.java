package ecommerce.utn.ecommerce.jar.controllers;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import ecommerce.utn.ecommerce.jar.dto.PreferenceItem;
import ecommerce.utn.ecommerce.jar.dto.ProductoMPDTO;
import ecommerce.utn.ecommerce.jar.models.Productos;
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
            List<PreferenceItemRequest> items = new ArrayList<>();
            // Aquí configura la preferencia de acuerdo a tus necesidades // Puedes proporcionar la preferencia aquí
            for (ProductoMPDTO producto:preferenceItem.getProductosList()) {
                PreferenceItemRequest itemRequest =
                        PreferenceItemRequest.builder()
                                .id("1234")
                                .title(producto.getNombre())
                                .description("")
                                .pictureUrl(producto.getImagen())
                                .categoryId(producto.getCategoria())
                                .quantity(producto.getQuantity())
                                .currencyId("ARS")
                                .unitPrice(new BigDecimal(producto.getPrecio()))
                                .build();
                items.add(itemRequest);
                System.out.println(itemRequest);
            }
            PreferenceBackUrlsRequest backUrls =
// ...
                    PreferenceBackUrlsRequest.builder()
                            .success("https://ecommerce-prueba-production.up.railway.app/")
                            .pending("https://ecommerce-prueba-production.up.railway.app/")
                            .failure("")
                            .build();
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items).backUrls(backUrls).autoReturn("approved").build();
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);



            PreferenceRequest request = PreferenceRequest.builder().backUrls(backUrls).build();
// ...

            System.out.println(preference.getId());

            return preference.getId();
        } catch (MPException | MPApiException e) {
            // Manejo de excepciones
            e.printStackTrace();
            return "Error al crear la preferencia.";
        }
    }
}
