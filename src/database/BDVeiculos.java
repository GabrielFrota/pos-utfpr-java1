package database;

import motorized.Carga;
import motorized.Passeio;
import motorized.Veiculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BDVeiculos {
    private final List<Passeio> listaPasseio = new ArrayList<>();
    private final List<Carga> listaCarga = new ArrayList<>();

    public int count(Class<? extends Veiculo> type) {
        if (type == Passeio.class) {
            return listaPasseio.size();
        } else if (type == Carga.class) {
            return listaCarga.size();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void delete(Veiculo v) {
        if (v instanceof Passeio p) {
            listaPasseio.remove(p);
        } else if (v instanceof Carga c) {
            listaCarga.remove(c);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void existsByPlate(String plate) throws VeicExistException {
        for (var p : listaPasseio) {
            if (p.getPlaca().equals(plate))
                throw new VeicExistException();
        }
        for (var c : listaCarga) {
            if (c.getPlaca().equals(plate))
                throw new VeicExistException();
        }
    }

    public void save(Veiculo v) {
        if (v instanceof Passeio p) {
            listaPasseio.add(p);
        } else if (v instanceof Carga c) {
            listaCarga.add(c);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public List<? extends Veiculo> findAll(Class<? extends Veiculo> type) {
        if (type == Passeio.class) {
            return Collections.unmodifiableList(listaPasseio);
        } else if (type == Carga.class) {
            return Collections.unmodifiableList(listaCarga);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Optional<? extends Veiculo> findByPlate(Class<? extends Veiculo> type, String plate) {
        if (type == Passeio.class) {
            for (var p : listaPasseio) {
                if (p.getPlaca().equals(plate))
                    return Optional.of(p);
            }
            return Optional.empty();
        } else if (type == Carga.class) {
            for (var c : listaCarga) {
                if (c.getPlaca().equals(plate))
                    return Optional.of(c);
            }
            return Optional.empty();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static final BDVeiculos db = new BDVeiculos();
    
    public static BDVeiculos db() {
        return db;
    }
}
