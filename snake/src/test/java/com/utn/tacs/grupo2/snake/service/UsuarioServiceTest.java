package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.SnakeApplicationTests;
import com.utn.tacs.grupo2.snake.builder.UsuarioBuilder;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.repository.UsuarioRepository;
import java.io.IOException;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioServiceTest extends SnakeApplicationTests {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final static Long USUARIO_ID = 1L;

    @Test
    public void obtenerTodos_retornaListaDeUsuario() throws IOException {
        List<Usuario> usuarios = usuarioService.obtenerTodos();

        assertThat(usuarios).isNotNull();
        assertThat(usuarios.isEmpty()).isFalse();
        assertThat(usuarios.size()).isEqualTo(usuarioRepository.count());
    }

    @Test
    public void obtener_conUsuarioIdExistente_retornaUsuario() {
        Usuario usuario = usuarioService.obtener(USUARIO_ID);

        assertThat(usuario).isNotNull();
        assertThat(usuario.getId()).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void obtener_conUsuarioIdInexistente_lanzaIllegalArgumentException() {
        usuarioService.obtener(Long.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void obtener_conUsuarioIdNulo_lanzaIllegalArgumentException() {
        usuarioService.obtener(null);
    }

    @Test
    public void guardar_conUsuarioNuevo_guardaElUsuario() {
        Long cantidadUsuariosAntes = usuarioRepository.count();
        Usuario usuario = new UsuarioBuilder()
                .conId(null)
                .conUsername("Homer")
                .conPassword("Simpson")
                .conUltimoAcceso(null)
                .build();

        usuarioService.guardar(usuario);

        Long cantidadUsuariosDespues = usuarioRepository.count();
        assertThat(usuario.getId()).isNotNull();
        assertThat(cantidadUsuariosDespues).isEqualTo(cantidadUsuariosAntes + 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void guardar_conNombreDeUsuarioExistente_lanzaIllegalArgumentException() {
        Usuario usuario = UsuarioBuilder
                .tipico()
                .build();

        usuarioService.guardar(usuario);
    }
    
}
