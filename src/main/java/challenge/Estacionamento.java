package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Estacionamento {

    private static final int MAX_PONTOS = 20;
    private static final int IDADE_MIN = 18;
    private static final int SENIOR = 55;
    private static final int MAX_VAGAS = 10;

    private Carro[] estacionados = new Carro[10];

    public int carrosEstacionados;

    public void estacionar(Carro carro) {

        valida(carro);
        if (carrosEstacionados < MAX_VAGAS) {
            estacionaCarro(carro);
        } else {
            removerCarroApto();
            estacionaCarro(carro);
        }
        carrosEstacionados += carrosEstacionados < MAX_VAGAS ? 1 : 0;
    }

    private void estacionaCarro(Carro carro) {
        for (int i = 0; i < estacionados.length; i++) {
            if(estacionados[i] == null) {
                estacionados[i] = carro;
                return;
            }
        }
    }

    private void removerCarroApto() {
        for (int i = 0; i < estacionados.length; i++) {
            if (estacionados[i] != null && estacionados[i].getMotorista().getIdade() < SENIOR) {
                estacionados[i] = null;
                carrosEstacionados--;
                return;
            }
        }
        throw new EstacionamentoException("Não há ninguem apto a sair, lamtamos mas não podera estacionar.");
    }

    public int carrosEstacionados() {
        return carrosEstacionados;
    }

    public boolean carroEstacionado(Carro carro) {
        for (Carro car : estacionados) {
            if (car != null && car.equals(carro))
                return true;
        }
        return false;
    }

    private void valida(Carro carro) {
        if (carro.getMotorista() == null)
            throw new EstacionamentoException("Carros autonomos não são permitidos.");
        if (carro.getMotorista().getIdade() < IDADE_MIN)
            throw new EstacionamentoException("Não é permitido motorista menor de idade.");
        if (carro.getMotorista().getPontos() >= MAX_PONTOS)
            throw new EstacionamentoException("Não é permitido motorista menor de idade.");
    }
}