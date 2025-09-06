package com.senai.escola.Interface;


import com.senai.escola.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsusarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> buscarUsuarioSenha (String username, String senha);
}
