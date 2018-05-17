package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.SnakeApplicationTests;
import com.utn.tacs.grupo2.snake.vo.CotizacionMonedaVo;
import java.io.IOException;
import java.math.BigDecimal;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

public class MonedaServiceTest extends SnakeApplicationTests {

    @Autowired
    private MonedaService monedaService;

    @Test
    public void obtenerCotizacion_conMonedaExistente_retornaCotizacionMonedaVo() throws IOException {
        String cotizacionBitcoinResponse = obtenerContenidoArchivo("jsons/response_cotizacionBitcoin.json");
        String monedaNombre = "bitcoin";
        mockRestServiceServer.expect(requestTo("https://api.coinmarketcap.com/v1/ticker/" + monedaNombre))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(cotizacionBitcoinResponse, MediaType.APPLICATION_JSON));

        CotizacionMonedaVo cotizacionMonedaVo = monedaService.obtenerCotizacion(monedaNombre);

        assertThat(cotizacionMonedaVo).isNotNull();
        assertThat(cotizacionMonedaVo.getMonedaNombre()).isEqualTo("bitcoin");
        assertThat(cotizacionMonedaVo.getSymbol()).isEqualTo("BTC");
        assertThat(cotizacionMonedaVo.getCotizacionDolar()).isEqualByComparingTo(new BigDecimal("1000"));
    }

    @Test(expected = HttpClientErrorException.class)
    public void obtenerCotizacion_conMonedaNombreInexistente_lanzaHttpClientErrorException() throws IOException {
        String monedaNombre = "inexistente";
        mockRestServiceServer
                .expect(requestTo("https://api.coinmarketcap.com/v1/ticker/" + monedaNombre))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));

        monedaService.obtenerCotizacion(monedaNombre);
    }

    @Test(expected = HttpServerErrorException.class)
    public void obtenerCotizacion_conApiCaida_lanzaHttpServerErrorException() throws IOException {
        String monedaNombre = "bitcoin";
        mockRestServiceServer
                .expect(requestTo("https://api.coinmarketcap.com/v1/ticker/" + monedaNombre))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withServerError());

        monedaService.obtenerCotizacion(monedaNombre);
    }

}
