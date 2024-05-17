import database.BDVeiculos;
import database.VeicExistException;
import gui.MainFrame;
import motorized.Carga;
import motorized.Passeio;
import motorized.VelocException;
import utils.Leitura;

import javax.swing.*;
import java.util.Set;

public class Teste {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
//    public static void main(String[] args) {
//        var db = new BDVeiculos();
//        var l = new Leitura();
//        var skipMenu = false;
//        var opt = 0;
//        for (;;) {
//            if (!skipMenu) {
//                System.out.print("""
//                        Sistema de Gestão de Veículos - Menu Inicial
//                        1. Cadastrar Veiculo de Passeio
//                        2. Cadastrar Veiculo de Carga
//                        3. Imprimir Todos os Veiculos de Passeio
//                        4. Imprimir Todos os Veiculos de Carga
//                        5. Imprimir Veiculo de Passeio pela Placa
//                        6. Imprimir Veiculo de Carga pela Placa
//                        7. Excluir Veículo de Passeio pela Placa
//                        8. Excluir Veículo de Carga pela Placa
//                        9. Sair do Sistema
//                        """);
//                opt = l.readOpt(1, 9);
//            }
//            switch (opt) {
//                case 1, 2 -> {
//                    var v = opt == 1 ? new Passeio() : new Carga();
//                    var s = l.entDados("Digite a placa: ");
//                    try {
//                        db.existsByPlate(s);
//                    } catch (VeicExistException e) {
//                        skipMenu = false;
//                        break;
//                    }
//                    v.setPlaca(s);
//                    v.setMarca(l.entDados("Digite a marca: "));
//                    v.setModelo(l.entDados("Digite o modelo: "));
//                    v.setCor(l.entDados("Digite a cor: "));
//                    try {
//                        v.setVelocMax(l.readFloat("Digite a velocidade máxima: "));
//                    } catch (VelocException e) {
//                        try {
//                            v.setVelocMax(v instanceof Passeio ? 100 : 90);
//                        } catch (VelocException ignored) {}
//                    }
//                    v.setQtdRodas(l.readInt("Digite a quantidade de rodas: "));
//                    v.getMotor().setQtdPist(l.readInt("Digite a quantidade de pistões do motor: "));
//                    v.getMotor().setPotencia(l.readInt("Digite o valor da potência do motor: "));
//                    if (v instanceof Passeio p) {
//                        p.setQtdPassageiros(l.readInt("Digite a quantidade de passageiros: "));
//                    } else {
//                        Carga c = (Carga) v;
//                        c.setCargaMax(l.readInt("Digite o valor da carga máxima: "));
//                        c.setTara(l.readInt("Digite o valor da tara: "));
//                    }
//                    db.save(v);
//                    var type = opt == 1 ? "passeio" : "carga";
//                    System.out.println("Deseja cadastrar mais um veículo de " + type + "? (S/N)");
//                    var sopt = l.readOpt(Set.of("s", "n", "S", "N"));
//                    skipMenu = sopt.equals("s") || sopt.equals("S");
//                }
//                case 3, 4 -> {
//                    if ((opt == 3 && db.pcount() == 0)
//                        || (opt == 4 && db.ccount() == 0)) {
//                        var s = opt == 3 ? "passeio" : "carga";
//                        System.out.println("Nenhum veículo de " + s + " cadastrado no sistema.");
//                        break;
//                    }
//                    var veics = opt == 3 ? db.pfindAll() : db.cfindAll();
//                    for (var v : veics) {
//                        System.out.println(v);
//                    }
//                }
//                case 5, 6 -> {
//                    var p = l.entDados("Digite a placa para a busca: ");
//                    var v = opt == 5 ? db.pfindByPlate(p) : db.cfindByPlate(p);
//                    v.ifPresentOrElse(System.out::println,
//                            () -> System.out.println("Placa inserida não foi encontrada."));
//                }
//                case 7, 8 -> {
//                    var p = l.entDados("Digite a placa para a remoção: ");
//                    var v = opt == 7 ? db.pfindByPlate(p) : db.cfindByPlate(p);
//                    v.ifPresentOrElse(db::delete,
//                            () -> System.out.println("Placa inserida não foi encontrada."));
//                    v.ifPresent(__ -> System.out.println("Veículo removido com sucesso."));
//                }
//                case 9 -> System.exit(0);
//            }
//            if (!skipMenu)
//                System.out.print("\n");
//        }
//    }
}