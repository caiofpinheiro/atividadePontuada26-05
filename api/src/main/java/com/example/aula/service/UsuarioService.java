package com.example.aula.service;

import com.example.aula.exception.UsuarioJaCadastradoException;
import com.example.aula.model.Usuario;
import com.example.aula.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario salvar(@Valid Usuario usuario) {
        if (usuario.getId() != null && usuarioRepository.findById(usuario.getId()).isPresent()) {
            throw new UsuarioJaCadastradoException("Usuário já cadastrado.");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(@Valid Usuario usuario) {
        Usuario usuarioAtualizar = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        usuarioAtualizar.setNome(usuario.getNome());
        usuarioAtualizar.setSexo(usuario.getSexo());
        usuarioAtualizar.setIdade(usuario.getIdade());
        usuarioAtualizar.setPeso(usuario.getPeso());
        usuarioAtualizar.setAltura(usuario.getAltura());
        usuarioAtualizar.setPosicao(usuario.getPosicao());
        usuarioAtualizar.setNumeroDaCamisa(usuario.getNumeroDaCamisa());

        return usuarioRepository.save(usuarioAtualizar);
    }

    public Usuario findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }
}