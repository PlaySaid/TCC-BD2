package co.edu.udec.domain.model;

import java.sql.Date;

public class Usuario {
        private int id;
        private String nombre;
        private String usuario;
        private String password;
        private int idRol;
        private Date fechaCreacion;
        private boolean activo;

        public Usuario() {
        }

        public Usuario(int id, String nombre, String usuario, String password, int idRol, Date fechaCreacion, boolean activo) {
            this.id = id;
            this.nombre = nombre;
            this.usuario = usuario;
            this.password = password;
            this.idRol = idRol;
            this.fechaCreacion = fechaCreacion;
            this.activo = activo;
        }

        public int getIdUsuario() {
            return id;
        }

        public void setIdUsuario(int idUsuario) {
            this.id = idUsuario;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getIdRol() {
            return idRol;
        }

        public void setIdRol(int idRol) {
            this.idRol = idRol;
        }

        public Date getFechaCreacion() {
            return fechaCreacion;
        }

        public void setFechaCreacion(Date fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
        }

        public boolean isActivo() {
            return activo;
        }

        public void setActivo(boolean activo) {
            this.activo = activo;

    }
}
