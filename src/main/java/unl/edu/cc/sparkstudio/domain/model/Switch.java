package unl.edu.cc.sparkstudio.domain.model;

/**
 * @Autor Grupo3
 */
public class Switch extends Componente {
    private boolean estado;

    public Switch() {

    }

    public Switch(boolean estado) {
        super();
        this.estado = estado;
    }

    public void cambiarEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }

    @Override
    public void setEstado(boolean estado) {
        cambiarEstado(estado);
    }

    @Override
    public String toString() {
        return "Switch{" +
                "estado=" + estado +
                '}';
    }
}