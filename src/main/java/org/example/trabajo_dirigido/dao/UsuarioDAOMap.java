package org.example.trabajo_dirigido.dao;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.trabajo_dirigido.model.Usuario;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * DAO para la entidad Usuario utilizando un Map en memoria.
 * Anotado con @ApplicationScoped para que CDI gestione una única instancia.
 */
@ApplicationScoped // CDI creará una única instancia de esta clase para toda la aplicación
public class UsuarioDAOMap {

    private final Map<Long, Usuario> usuariosMap = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0); // Para generar IDs únicos

    public UsuarioDAOMap() {
        // Datos iniciales de ejemplo
        add(new Usuario("Alice Wonderland", "alice@example.com"));
        add(new Usuario("Bob The Builder", "bob@example.com"));
        add(new Usuario("Charlie Brown", "charlie@example.com"));
    }

    /**
     * Añade un nuevo usuario. Si el usuario tiene un ID, se intenta actualizar.
     * Si no tiene ID, se le asigna uno nuevo y se añade.
     * @param usuario El usuario a añadir o actualizar.
     * @return El usuario guardado (con ID asignado si era nuevo).
     */
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) { // Nuevo usuario
            usuario.setId(sequence.incrementAndGet());
            usuariosMap.put(usuario.getId(), usuario);
            return usuario;
        } else { // Actualizar usuario existente
            if (usuariosMap.containsKey(usuario.getId())) {
                usuariosMap.put(usuario.getId(), usuario);
                return usuario;
            } else {
                throw new IllegalArgumentException("Usuario con ID " + usuario.getId() + " no encontrado para actualizar.");
            }
        }
    }

    /**
     * Añade un nuevo usuario (método interno para datos iniciales o cuando se sabe que es nuevo).
     * @param usuario El usuario a añadir.
     */
    private void add(Usuario usuario) {
        usuario.setId(sequence.incrementAndGet());
        usuariosMap.put(usuario.getId(), usuario);
    }


    /**
     * Encuentra un usuario por su ID.
     * @param id El ID del usuario.
     * @return Un Optional conteniendo el usuario si se encuentra, o vacío si no.
     */
    public Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(usuariosMap.get(id));
    }

    /**
     * Devuelve todos los usuarios.
     * @return Una colección de todos los usuarios.
     */
    public List<Usuario> findAll() {
        return usuariosMap.values().stream().toList();
    }

    /**
     * Elimina un usuario por su ID.
     * @param id El ID del usuario a eliminar.
     * @return true si el usuario fue eliminado, false si no se encontró.
     */
    public boolean deleteById(Long id) {
        return usuariosMap.remove(id) != null;
    }
}

