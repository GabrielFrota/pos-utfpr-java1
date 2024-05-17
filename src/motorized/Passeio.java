package motorized;

import java.util.Set;

public final class Passeio extends Veiculo implements Calcular {
    private int qtdPassageiros = 0;

    @Override
    public float calcVel(float velocMax) {
        return velocMax * 1000;
    }

    @Override
    public int calcular() {
        return sumValsFromFields(Set.of(String.class));
    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }
}
