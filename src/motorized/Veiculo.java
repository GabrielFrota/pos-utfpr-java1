package motorized;

import utils.Utils;

import java.util.Set;

public abstract class Veiculo {
    private String placa = " ";
    private String marca = " ";
    private String modelo = " ";
    private String cor = " ";
    private float velocMax = 0;
    private int qtdRodas = 0;
    private Motor motor = new Motor();

    public abstract float calcVel(float velocMax);

    @Override
    public String toString() {
        return Utils.prettyPrint(this);
    }

    protected int sumValsFromFields(Set<Class<?>> typesToSum) {
        int sum = 0;
        for (Class<?> c = getClass(); !c.equals(Object.class); c = c.getSuperclass()) {
            var fields = c.getDeclaredFields();
            for (var f : fields) {
                f.setAccessible(true);
                if (typesToSum.contains(f.getType())) {
                    var t = f.getType();
                    try {
                        if (t.equals(String.class)) {
                            sum += ((String)f.get(this)).length();
                        }
                        else if (t.equals(int.class)) {
                            sum += (Integer)f.get(this);
                        }
                        else if (t.equals(float.class)) {
                            sum += Math.round((Float)f.get(this));
                        }
                    } catch (IllegalAccessException ignored) {}
                }
            }
        }
        return sum;
    }

    public String getPlaca() {
        return placa;
    }

    public final void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public final void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public final void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public final void setCor(String cor) {
        this.cor = cor;
    }

    public float getVelocMax() {
        return velocMax;
    }

    public final void setVelocMax(float velocMax) throws VelocException {
        if (velocMax < 80 || velocMax > 110)
            throw new VelocException();
        this.velocMax = velocMax;
    }

    public int getQtdRodas() {
        return qtdRodas;
    }

    public final void setQtdRodas(int qtdRodas) {
        this.qtdRodas = qtdRodas;
    }

    public Motor getMotor() {
        return motor;
    }

    public final void setMotor(Motor motor) {
        this.motor = motor;
    }
}