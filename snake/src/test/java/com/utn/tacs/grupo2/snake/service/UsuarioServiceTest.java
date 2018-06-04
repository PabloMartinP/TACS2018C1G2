package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.SnakeApplicationTest;
import com.utn.tacs.grupo2.snake.builder.UsuarioBuilder;
import com.utn.tacs.grupo2.snake.domain.Rol;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.repository.UsuarioRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithUserDetails;

public class UsuarioServiceTest extends SnakeApplicationTest {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final static Long USUARIO_ID = 1L;

    @Test
    @WithUserDetails(value = "admin")
    public void obtenerTodos_conUsuarioAdmin_retornaListaDeUsuario() throws IOException {
        List<Usuario> usuarios = usuarioService.obtenerTodos();

        assertThat(usuarios).isNotNull();
        assertThat(usuarios.isEmpty()).isFalse();
        assertThat(usuarios.size()).isEqualTo(usuarioRepository.count());
    }

    @Test(expected = AccessDeniedException.class)
    @WithUserDetails(value = "chester")
    public void obtenerTodos_conUsuarioComun_lanzaAccessDeniedException() throws IOException {
        usuarioService.obtenerTodos();
    }

    @Test
    @WithUserDetails(value = "chester")
    public void obtener_conUsuarioIdExistente_retornaUsuario() {
        Usuario usuario = usuarioService.obtener(USUARIO_ID);

        assertThat(usuario).isNotNull();
        assertThat(usuario.getId()).isNotNull();
        assertThat(usuario.getRol()).isEqualTo(Rol.ROLE_USER);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void obtener_conUsuarioIdInexistente_lanzaIllegalArgumentException() {
        usuarioService.obtener(Long.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "chester")
    public void obtener_conUsuarioIdNulo_lanzaIllegalArgumentException() {
        usuarioService.obtener(null);
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void obtener_conUsuarioNoLogeado_lanzaAuthenticationCredentialsNotFoundException() {
        usuarioService.obtener(USUARIO_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    @WithUserDetails(value = "homer")
    public void obtener_conUsuarioSinPermisos_lanzaIllegalArgumentException() {
        usuarioService.obtener(USUARIO_ID);
    }

    @Test
    public void guardar_conUsuarioNuevo_guardaElUsuario() {
        Long cantidadUsuariosAntes = usuarioRepository.count();
        Usuario usuario = new UsuarioBuilder()
                .conId(null)
                .conUsername("Homer")
                .conPassword("123456")
                .conUltimoAcceso(null)
                .build();

        usuarioService.guardar(usuario);

        Long cantidadUsuariosDespues = usuarioRepository.count();
        assertThat(usuario.getId()).isNotNull();
        assertThat(usuario.getPassword()).isNotNull().isNotEqualTo("123456");
        assertThat(usuario.getTelegramId()).isNotNull().isEqualTo(1234L);
        assertThat(usuario.getRol()).isEqualTo(Rol.ROLE_USER);
        assertThat(cantidadUsuariosDespues).isEqualTo(cantidadUsuariosAntes + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void guardar_conNombreDeUsuarioExistente_lanzaIllegalArgumentException() {
        Usuario usuario = UsuarioBuilder
                .tipico()
                .build();

        usuarioService.guardar(usuario);
    }

    @Test
    public void actualizarUltimoAcceso_conUsuarioLogeado_actualizaElUltimoAcceso() {
        Usuario usuario = usuarioRepository.findByUsername("chester");
        LocalDateTime anteriorIngreso = usuario.getUltimoAcceso();
        
        usuarioService.actualizarUltimoAcceso(usuario);

        assertThat(usuario.getUltimoAcceso()).isNotEqualTo(anteriorIngreso);
    }
}
