package br.com.citrinstar.searchFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Veiculo {
    @JsonAlias("Valor")
    private Double valor;
    @JsonAlias("Marca")
    private String marca;
    @JsonAlias("Modelo")
    private String modelo;
    @JsonAlias("AnoModelo")
    private int anoModelo;
    @JsonAlias("Combustivel")
    private String combustivel;
    @JsonAlias("CodigoFipe")
    private String codigoFipe;

    public Double getValor() {
        return valor;
    }

    //JsonSetter indica que esse método deve ser usado durante a desserialização do campo valor
    @JsonSetter("Valor")
    public void setValor(String valor) {
        try{
            NumberFormat format = NumberFormat.getNumberInstance(new Locale("pt","BR"));
            Number number = format.parse(valor.replace("R$","").trim());
            this.valor = number.doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException("Formato inválido para valor: " + valor, e);
        }
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        this.codigoFipe = codigoFipe;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "valor=" + valor +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anoModelo=" + anoModelo +
                ", combustivel='" + combustivel + '\'' +
                ", codigoFipe='" + codigoFipe + '\'' +
                '}';
    }
}
