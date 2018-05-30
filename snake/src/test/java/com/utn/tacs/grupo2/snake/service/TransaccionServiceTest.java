package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.SnakeApplicationTest;
import com.utn.tacs.grupo2.snake.builder.TransaccionBuilder;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithUserDetails;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

public class TransaccionServiceTest extends SnakeApplicationTest {

    //autowired para que spring nos provea una instancia del service
    @Autowired
    private TransaccionService transaccionService;

    private final static Long USUARIO_ID = 1L;

    /**
     * Nomenclatura de los test nombreMetodo_contexto_retorno ()
     */
    @Test
    @WithUserDetails(value = "chester")
    public void registrar_conTransaccionCompraValida_retornaTransaccion() throws IOException {
        //set up
        String cotizacionBitcoinResponse = obtenerContenidoArchivo("jsons/response_cotizacionBitcoin.json");
        String monedaNombre = "bitcoin";

        Transaccion transaccion = TransaccionBuilder
                .compraTipica()
                .conId(null)
                .conMonedaNombre(monedaNombre)
                .conBilletera(null)
                .conCantidad(BigDecimal.ONE)
                .build();

        mockRestServiceServer.expect(requestTo("https://api.coinmarketcap.com/v1/ticker/" + monedaNombre))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(cotizacionBitcoinResponse, MediaType.APPLICATION_JSON));

        // ejercitamos
        transaccionService.registrar(transaccion, USUARIO_ID);

        // validamos
        assertThat(transaccion).isNotNull();
        assertThat(transaccion.getId()).isNotNull();
        assertThat(transaccion.getCotizacion()).isNotNull();
        assertThat(transaccion.getCotizacion()).isEqualTo(new BigDecimal("1000"));
        assertThat(transaccion.getBilletera().getCantidad()).isEqualByComparingTo(BigDecimal.valueOf(11L));
        assertThat(transaccion.getBilletera().getDiferencia()).isEqualByComparingTo(new BigDecimal("-1100"));
    }

    @Test
    @WithUserDetails(value = "chester")
    public void registrar_conTransaccionVentaValida_retornaTransaccion() throws IOException {
        String cotizacionBitcoinResponse = obtenerContenidoArchivo("jsons/response_cotizacionBitcoin.json");
        String monedaNombre = "bitcoin";
        Transaccion transaccion = TransaccionBuilder
                .ventaTipica()
                .conId(null)
                .conMonedaNombre(monedaNombre)
                .conBilletera(null)
                .conCantidad(BigDecimal.ONE)
                .build();
        mockRestServiceServer.expect(requestTo("https://api.coinmarketcap.com/v1/ticker/" + monedaNombre))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(cotizacionBitcoinResponse, MediaType.APPLICATION_JSON));

        transaccionService.registrar(transaccion, USUARIO_ID);

        assertThat(transaccion).isNotNull();
        assertThat(transaccion.getId()).isNotNull();
        assertThat(transaccion.getCotizacion()).isNotNull();
        assertThat(transaccion.getCotizacion()).isEqualTo(new BigDecimal("1000"));
        assertThat(transaccion.getBilletera().getCantidad()).isEqualByComparingTo(BigDecimal.valueOf(9L));
        assertThat(transaccion.getBilletera().getDiferencia()).isEqualByComparingTo(new BigDecimal("900"));
    }

    @Test(expected = HttpClientErrorException.class)
    @WithUserDetails(value = "chester")
    public void registrar_conTransaccionConMonedaNombreInexistente_lanzaHttpClientErrorException() throws IOException {
        String monedaNombre = "inexistente";
        Transaccion transaccion = TransaccionBuilder
                .compraTipica()
                .conId(null)
                .conMonedaNombre(monedaNombre)
                .conBilletera(null)
                .conCantidad(BigDecimal.ONE)
                .build();
        mockRestServiceServer
                .expect(requestTo("https://api.coinmarketcap.com/v1/ticker/" + monedaNombre))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));

        transaccionService.registrar(transaccion, USUARIO_ID);
    }

    @Test(expected = HttpServerErrorException.class)
    @WithUserDetails(value = "chester")
    public void registrar_conApiCaida_lanzaHttpServerErrorException() throws IOException {
        String monedaNombre = "bitcoin";
        Transaccion transaccion = TransaccionBuilder
                .compraTipica()
                .conId(null)
                .conMonedaNombre(monedaNombre)
                .conBilletera(null)
                .conCantidad(BigDecimal.ONE)
                .build();
        mockRestServiceServer
                .expect(requestTo("https://api.coinmarketcap.com/v1/ticker/" + monedaNombre))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withServerError());

        transaccionService.registrar(transaccion, USUARIO_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void registrar_conCantidadInvalida_lanzaException() throws IOException {
        String monedaNombre = "bitcoin";
        Transaccion transaccion = TransaccionBuilder
                .compraTipica()
                .conId(null)
                .conMonedaNombre(monedaNombre)
                .conBilletera(null)
                .conCantidad(BigDecimal.valueOf(-1L))
                .build();

        transaccionService.registrar(transaccion, USUARIO_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void registrar_conCantidadCero_lanzaException() throws IOException {
        String monedaNombre = "bitcoin";
        Transaccion transaccion = TransaccionBuilder
                .compraTipica()
                .conId(null)
                .conMonedaNombre(monedaNombre)
                .conBilletera(null)
                .conCantidad(BigDecimal.ZERO)
                .build();

        transaccionService.registrar(transaccion, USUARIO_ID);
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void registrar_conUsuarioNoLogeado_lanzaAuthenticationCredentialsNotFoundException() {
        String monedaNombre = "bitcoin";
        Transaccion transaccion = TransaccionBuilder
                .compraTipica()
                .conId(null)
                .conMonedaNombre(monedaNombre)
                .conBilletera(null)
                .conCantidad(BigDecimal.ONE)
                .build();

        transaccionService.registrar(transaccion, USUARIO_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "homer")
    public void registrar_conUsuarioSinPermisos_lanzaIllegalArgumentException() {
        String monedaNombre = "bitcoin";
        Transaccion transaccion = TransaccionBuilder
                .compraTipica()
                .conId(null)
                .conMonedaNombre(monedaNombre)
                .conBilletera(null)
                .conCantidad(BigDecimal.ONE)
                .build();

        transaccionService.registrar(transaccion, USUARIO_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "admin")
    public void registrar_conUsuarioAdmin_lanzaIllegalArgumentException() {
        String monedaNombre = "bitcoin";
        Transaccion transaccion = TransaccionBuilder
                .compraTipica()
                .conId(null)
                .conMonedaNombre(monedaNombre)
                .conBilletera(null)
                .conCantidad(BigDecimal.ONE)
                .build();

        transaccionService.registrar(transaccion, USUARIO_ID);
    }

    @Test
    @WithUserDetails(value = "chester")
    public void buscarTodas_conUsuarioExistenteYMonedaBitcoin_retornaListaDeTransacciones() {
        String monedaNombre = "bitcoin";
        List<Transaccion> transacciones = transaccionService.buscarTodas(USUARIO_ID, monedaNombre);

        assertThat(transacciones).isNotNull();
        assertThat(transacciones.isEmpty()).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void buscarTodas_conUsuarioInexistenteYMonedaBitcoin_lanzaIllegalArgumentException() {
        String monedaNombre = "bitcoin";
        transaccionService.buscarTodas(Long.MAX_VALUE, monedaNombre);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void buscarTodas_conUsuarioInvalidoYMonedaBitcoin_lanzaIllegalArgumentException() {
        String monedaNombre = "bitcoin";

        transaccionService.buscarTodas(null, monedaNombre);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void buscarTodas_conUsuarioExistenteYMonedaInexistente_lanzaIllegalArgumentException() {
        String monedaNombre = "pesos";

        transaccionService.buscarTodas(USUARIO_ID, monedaNombre);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void buscarTodas_conUsuarioExistenteYMonedaInvalida_lanzaIllegalArgumentException() {
        String monedaNombre = null;

        transaccionService.buscarTodas(USUARIO_ID, monedaNombre);
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void buscarTodas_conUsuarioNoLogeado_lanzaAuthenticationCredentialsNotFoundException() {
        String monedaNombre = "bitcoin";
        transaccionService.buscarTodas(USUARIO_ID, monedaNombre);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "homer")
    public void buscarTodas_conUsuarioSinPermisos_lanzaIllegalArgumentException() {
        String monedaNombre = "bitcoin";
        transaccionService.buscarTodas(USUARIO_ID, monedaNombre);
    }

    @Test
    @WithUserDetails(value = "admin")
    public void buscarTodas_conUsuarioAdministradorYMonedaBitcoin_retornaListaDeTransacciones() {
        String monedaNombre = "bitcoin";
        List<Transaccion> transacciones = transaccionService.buscarTodas(USUARIO_ID, monedaNombre);

        assertThat(transacciones).isNotNull();
        assertThat(transacciones.isEmpty()).isFalse();
    }

    @Test
    @WithUserDetails(value = "admin")
    public void contar_conFechaNula_retornaCantidadTotal() {
        Long cantidad = transaccionService.contar(null);

        assertThat(cantidad).isEqualTo(3L);
    }
    
    @Test
    @WithUserDetails(value = "admin")
    public void contar_conFechaValidaPosteriorA_retornaCantidadAPartirDeEsaFecha() {
        LocalDate fechaDesde = LocalDate.of(2018, Month.APRIL, 9);
                
        Long cantidad = transaccionService.contar(fechaDesde);

        assertThat(cantidad).isEqualTo(2L);
    }

    @Test
    @WithUserDetails(value = "admin")
    public void contar_conFechaInexistente_retornaCero() {
        Long cantidad = transaccionService.contar(LocalDate.MIN);

        assertThat(cantidad).isEqualTo(0);
    }
    
    @Test(expected = AccessDeniedException.class)
    @WithUserDetails(value = "chester")
    public void contar_conUsuarioNoAdministrador_lanzaAccessDeniedException() {
        transaccionService.contar(null);
    }
    
    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void contar_conUsuarioNoAdministrador_lanzaAuthenticationCredentialsNotFoundException() {
        transaccionService.contar(null);
    }
    
}
