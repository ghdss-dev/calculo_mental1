package cbersoftware.dev.calculo_mental.model;

import java.util.Random;

public class Calcular_mental_model {

    private int dificuldade;
    private int valor1;
    private int valor2;
    private int operacao;
    protected int resultado;

    public Calcular_mental_model(int dificuldade) {

        Random rand = new Random();

        this.operacao = rand.nextInt(3);

        this.dificuldade = dificuldade;

        if(dificuldade == 1) {

            this.valor1 = rand.nextInt(10);
            this.valor2 = rand.nextInt(10);

        } else if (dificuldade == 2) {

            this.valor1 = rand.nextInt(100);
            this.valor2 = rand.nextInt(100);

        } else if (dificuldade == 3) {

            this.valor1 = rand.nextInt(1000);
            this.valor2 = rand.nextInt(1000);

        } else if (dificuldade == 4) {

            this.valor1 = rand.nextInt(10000);
            this.valor2 = rand.nextInt(10000);

        } else {

            this.valor1 = rand.nextInt(100000);
            this.valor2 = rand.nextInt(100000);
        }
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public int getValor1() {
        return valor1;
    }

    public int getValor2() {
        return valor2;
    }

    public int getOperacao() {
        return operacao;
    }

    public int getResultado() {
        return resultado;
    }
}
