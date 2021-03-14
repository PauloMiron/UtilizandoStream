import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Aplicacao {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Informa o caminho do arquivo a ser lido: ");
        String caminho = scanner.nextLine();

        try(BufferedReader br = new BufferedReader(new FileReader(caminho))){

            List<Funcionarios> lista = new ArrayList<>();

            String linha = br.readLine();
            while(linha != null){
                String [] campos = linha.split(",");
                lista.add(new Funcionarios(campos[0],campos[1],Double.parseDouble(campos[2])));
                linha = br.readLine();
            }

            System.out.print("Salario: ");
            Double salario = scanner.nextDouble();

            List<String> emails = lista.stream()
                    .filter(e -> e.getSalario() > salario)
                    .map(e -> e.getEmail()).sorted()
                    .collect(Collectors.toList());

            System.out.println("Emais das pessoas que recebem mais que: R$ " + salario);

            emails.forEach(System.out::println);

            /*
            Utilizado

            lista.removeIf(p -> p.getNome().charAt(0) != 'M');
            double soma=0;
            for (Funcionarios p : lista) {
                soma = soma + p.getSalario();
            }
            System.out.println("Soma dos salarios das pessoas que comecam com M: " + soma);*/

            double soma = lista.stream()
                    .filter(p -> p.getNome().charAt(0) == 'M')
                    .map(p -> p.getSalario())
                    .reduce(0.0,(x,y) -> x + y);

            System.out.println("Soma dos salarios das pessoas que comecam com M: " + soma);


        } catch (IOException e) {
            e.getMessage();
        }


        scanner.close();

    }
}
