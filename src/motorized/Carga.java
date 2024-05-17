package motorized;

import java.util.Set;

public final class Carga extends Veiculo implements Calcular {
    private int cargaMax = 0;
    private int tara = 0;

    @Override
    public float calcVel(float velocMax) {
        return velocMax * 100000;
    }

    @Override
    public int calcular() {
        return sumValsFromFields(Set.of(int.class, float.class));
    }

    public int getCargaMax() {
        return cargaMax;
    }

    public void setCargaMax(int cargaMax) {
        this.cargaMax = cargaMax;
    }

    public int getTara() {
        return tara;
    }

    public void setTara(int tara) {
        this.tara = tara;
    }
}
