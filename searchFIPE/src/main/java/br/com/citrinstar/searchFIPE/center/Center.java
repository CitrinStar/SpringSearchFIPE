package br.com.citrinstar.searchFIPE.center;

import br.com.citrinstar.searchFIPE.model.*;
import br.com.citrinstar.searchFIPE.service.ChamaServico;
import br.com.citrinstar.searchFIPE.service.ConverteDados;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Center {

    private static final String prefixoURL = "https://parallelum.com.br/fipe/api/v1/";
    private static ChamaServico servico = new ChamaServico();
    private static ConverteDados conversor = new ConverteDados();
    private static String response;
    private static Scanner leitor = new Scanner(System.in);
    private static String tipoVeiculo;
    private static String codigoMarca;
    private static String codigoModelo;
    private static List<Ano> anos = new ArrayList<>();
    private static List<Veiculo> veiculos = new ArrayList<>();

    public static void realizarChamadas(){

        selecionarTipoVeiculo();
        selecionaMarcaVeiculo();
        selecionarCodigoModeloVeiculo();
        getIntervaloAnosDoModelo();
        exibeListaVeiculosSelecionados();
    }

    private static void selecionarTipoVeiculo(){
        System.out.println("Digite o tipo de veículo: Carros | Caminhões | Motos");
        tipoVeiculo = leitor.nextLine();
    }
    private static void selecionaMarcaVeiculo(){
        response = servico.obterDados(prefixoURL + tipoVeiculo.toLowerCase() + "/marcas");

        System.out.println("Essas são as marcas disponíveis: ");
        List<Marca> marcas = new ArrayList<>();
        marcas.addAll(conversor.converterDadosLista(response, Marca.class));
        marcas.forEach(marca -> System.out.println(
                        "Código: " + marca.codigo() + " | Nome: " + marca.nome()
                ));

        System.out.println("\nDigite o código da marca do veículo: ");
        codigoMarca = leitor.nextLine();
    }

   private static void selecionarCodigoModeloVeiculo(){
       response = servico.obterDados(prefixoURL + tipoVeiculo.toLowerCase() + "/marcas" + "/" + codigoMarca + "/modelos");

        List<Modelo> modelos = new ArrayList<>();
        ModelosWrapper modelosWrapper = conversor.converterDados(response, ModelosWrapper.class);
        modelos.addAll(modelosWrapper.modelos());

        System.out.println("Esses são os modelos disponíveis: ");
        modelos.forEach(modelo -> System.out.println(
               "Código: " + modelo.codigo() + " | Nome: " + modelo.nome()
        ));

        System.out.println("\nInforme o nome do modelo que deseja filtrar: ");
        String nomeModelo = leitor.nextLine();

       System.out.println("\nEsse foram os modelos encontrados: ");
       modelos.stream()
               .filter(modelo -> modelo.nome().toLowerCase().contains(nomeModelo.toLowerCase()))
               .forEach(modelo -> System.out.println(
               "Código: " + modelo.codigo() + " | Nome: " + modelo.nome()
               ));

       System.out.println("\nInforme o código do modelo que deseja encontrar: ");
       codigoModelo = leitor.nextLine();
   }
    private static void getIntervaloAnosDoModelo(){
        response = servico.obterDados(prefixoURL + tipoVeiculo.toLowerCase()
                + "/marcas" + "/" + codigoMarca + "/modelos" + "/" + codigoModelo + "/anos");

        anos = conversor.converterDadosLista(response, Ano.class);
        anos = anos.stream()
                .sorted(Comparator.comparing(Ano::codigo))
                .collect(Collectors.toList());
    }

    private static void exibeListaVeiculosSelecionados(){
        Locale brasil = new Locale("pt","BR");
        NumberFormat formatador = NumberFormat.getCurrencyInstance(brasil);

        for(Ano ano : anos ){
            response = servico.obterDados(prefixoURL + tipoVeiculo.toLowerCase()
                    + "/marcas" + "/" + codigoMarca + "/modelos" + "/" + codigoModelo + "/anos/" + ano.codigo());
            veiculos.add(conversor.converterDados(response,Veiculo.class));
        }
        System.out.println("Esses foram os resultados encontrados: ");

        veiculos.forEach(veiculo ->
                System.out.println(
                        "Valor: " + formatador.format(veiculo.getValor()) +
                                " Marca: " + veiculo.getMarca() +
                                " | Modelo: " + veiculo.getModelo() +
                                " | Ano do Modelo: " + veiculo.getAnoModelo() +
                                " | Combustível: " + veiculo.getCombustivel() +
                                " | Código FIPE: " + veiculo.getCodigoFipe()
                )
        );

    }
}
