package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.SnakeApplicationTest;
import com.utn.tacs.grupo2.snake.domain.Billetera;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithUserDetails;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.web.client.HttpServerErrorException;

public class BilleteraServiceTest extends SnakeApplicationTest {

    @Autowired
    private BilleteraService billeteraService;

    private final static Long USUARIO_ID = 1L;

    @Test
    @WithUserDetails(value = "chester")
    public void buscarPorUsuarioId_conUsuarioExistente_retornaPortfolio() {
        List<Billetera> portfolio = billeteraService.buscarPorUsuarioId(USUARIO_ID);

        assertThat(portfolio).isNotNull();
        assertThat(portfolio.isEmpty()).isFalse();
    }

    @Test
    @WithUserDetails(value = "admin")
    public void buscarPorUsuarioId_conUsuarioAdmin_retornaPortfolio() {
        List<Billetera> portfolio = billeteraService.buscarPorUsuarioId(USUARIO_ID);

        assertThat(portfolio).isNotNull();
        assertThat(portfolio.isEmpty()).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void buscarPorUsuarioId_conUsuarioInexistente_lanzaIllegalArgumentException() {
        billeteraService.buscarPorUsuarioId(Long.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void buscarPorUsuarioId_conUsuarioIdNulo_lanzaIllegalArgumentException() {
        billeteraService.buscarPorUsuarioId(null);
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void buscarPorUsuarioId_conUsuarioNoLogeado_lanzaAuthenticationCredentialsNotFoundException() {
        billeteraService.buscarPorUsuarioId(USUARIO_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "homer")
    public void buscarPorUsuarioId_conUsuarioSinPermisos_lanzaIllegalArgumentException() {
        billeteraService.buscarPorUsuarioId(USUARIO_ID);
    }

    @Test
    @WithUserDetails(value = "chester")
    public void obtenerDiferencia_conDiferenciaActualYCotizacionActual_retornaGanaciaOPerdida() throws IOException {
        String cotizacionBitcoinResponse = obtenerContenidoArchivo("jsons/response_cotizacionBitcoin.json");
        String monedaNombre = "bitcoin";
        mockRestServiceServer.expect(requestTo("https://api.coinmarketcap.com/v1/ticker/" + monedaNombre))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(cotizacionBitcoinResponse, MediaType.APPLICATION_JSON));

        BigDecimal diferenciaActual = billeteraService.obtenerDiferencia(USUARIO_ID, monedaNombre);

        assertThat(diferenciaActual).isNotNull();
        assertThat(diferenciaActual).isEqualByComparingTo(new BigDecimal("9900"));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void obtenerDiferencia_conUsuarioAdminDiferenciaActualYCotizacionActual_retornaGanaciaOPerdida() throws IOException {
        String cotizacionBitcoinResponse = obtenerContenidoArchivo("jsons/response_cotizacionBitcoin.json");
        String monedaNombre = "bitcoin";
        mockRestServiceServer.expect(requestTo("https://api.coinmarketcap.com/v1/ticker/" + monedaNombre))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(cotizacionBitcoinResponse, MediaType.APPLICATION_JSON));

        BigDecimal diferenciaActual = billeteraService.obtenerDiferencia(USUARIO_ID, monedaNombre);

        assertThat(diferenciaActual).isNotNull();
        assertThat(diferenciaActual).isEqualByComparingTo(new BigDecimal("9900"));
    }

    @Test(expected = HttpServerErrorException.class)
    @WithUserDetails(value = "chester")
    public void obtenerDiferencia_conApiCaida_lanzaHttpServerErrorException() throws IOException {
        String monedaNombre = "bitcoin";
        mockRestServiceServer
                .expect(requestTo("https://api.coinmarketcap.com/v1/ticker/" + monedaNombre))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withServerError());

        billeteraService.obtenerDiferencia(USUARIO_ID, monedaNombre);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void obtenerDiferencia_conMonedaNombreInexistente_lanzaIllegalArgumentException() throws IOException {
        String monedaNombre = "inexistente";

        billeteraService.obtenerDiferencia(USUARIO_ID, monedaNombre);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void obtenerDiferencia_conUsuarioIdInexistente_lanzaIllegalArgumentException() {
        String monedaNombre = "bitcoin";

        billeteraService.obtenerDiferencia(Long.MAX_VALUE, monedaNombre);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void obtenerDiferencia_conUsuarioIdInvalido_lanzaIllegalArgumentException() {
        String monedaNombre = "bitcoin";

        billeteraService.obtenerDiferencia(null, monedaNombre);
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void obtenerDiferencia_conUsuarioNoLogeado_lanzaAuthenticationCredentialsNotFoundException() {
        String monedaNombre = "bitcoin";

        billeteraService.obtenerDiferencia(USUARIO_ID, monedaNombre);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "homer")
    public void obtenerDiferencia_conUsuarioSinPermisos_lanzaIllegalArgumentException() {
        String monedaNombre = "bitcoin";

        billeteraService.obtenerDiferencia(USUARIO_ID, monedaNombre);
    }

}
