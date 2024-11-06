package cbersoftware.dev.calculo_mental.apoio;

import cbersoftware.dev.calculo_mental.model.Calcular_mental_model;

public class Util_calculo_mental extends Calcular_mental_model {

    public Util_calculo_mental(int dificuldade) {
        super(dificuldade);
    }

    @Override
    public String toString() {

       String op = "";

       switch (this.getOperacao()) {

           case 0:
               op = "Somar";
               break;

           case 1:
               op = "Diminuir";
               break;

           case 2:
               op = "Multiplicar";
               break;

           default:
               op = "Operação desconhecida";
       }

       return "Valor 1: " + this.getValor1() +
               "\nValor 2: " + this.getValor2() +
               "\nDificuldade: " + this.getDificuldade() +
               "\nOperação: " + op;
    }

    public boolean validarResposta(int resposta) {

        switch (this.getOperacao()) {

            case 0:
                return  somar(resposta);

            case 1:
                return diminuir(resposta);

            case 2:
                return multiplicar(resposta);

            default:
                return false;
        }
    }

    public boolean somar(int resposta) {

        this.resultado = this.getValor1() + this.getValor2();
        return  resposta == this.getResultado();
    }

    public boolean diminuir(int resposta) {

        this.resultado = this.getValor1() - this.getValor2();
        return  resposta == this.getResultado();
    }

    public boolean multiplicar(int resposta) {

        this.resultado = this.getValor1() * this.getValor2();
        return  resposta == this.getResultado();
    }
}
