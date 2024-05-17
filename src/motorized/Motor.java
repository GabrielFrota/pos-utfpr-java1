package motorized;

import utils.Utils;

public class Motor {
    private int qtdPist = 0;
    private int potencia = 0;

    @Override
    public String toString() {
        return Utils.prettyPrint(this);
    }

    public int getQtdPist() {
        return qtdPist;
    }

    public final void setQtdPist(int qtdPist) {
        this.qtdPist = qtdPist;
    }

    public int getPotencia() {
        return potencia;
    }

    public final void setPotencia(int potencia) {
        this.potencia = potencia;
    }
}