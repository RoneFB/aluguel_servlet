package br.gov.sp.fatec.projetoweb;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.sp.fatec.projetoweb.dao.AluguelDao;
import br.gov.sp.fatec.projetoweb.entity.Aluguel;
import br.gov.sp.fatec.projetoweb.entity.Cliente;
import br.gov.sp.fatec.projetoweb.entity.Roupa;
import br.gov.sp.fatec.projetoweb.entity.Vendedor;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Vendedor vendedor = new Vendedor();
        vendedor.setNome("Rone Felipe Bento");
        vendedor.setEmail("ronefelipe97@gmail.com");
        vendedor.setSenha("1234");
        
        Vendedor vendedor2 = new Vendedor();
        vendedor2.setNome("Bernadino Josefino");
        vendedor2.setEmail("bernadino@gmail.com");
        vendedor2.setSenha("1234");
        
        Cliente cliente = new Cliente();
        cliente.setNome("Joséfina fina");
        cliente.setEmail("josefina@bol.com");
        cliente.setCpf("12346789");
        cliente.setRua("Av Pico da Bandeira");
        cliente.setTelefone("981565932");
        cliente.setBairro("Altos de Santana");
        cliente.setCidade("São José dos Campos");
        cliente.setNumero(516);
        cliente.setIdade(23);
    
        
        Roupa roupa = new Roupa(); 
        roupa.setTipo("camisa");
        roupa.setTamanho("G");
        roupa.setSexo("F");
        roupa.setPreco(65.99);
        roupa.setCor("Azul");
       
        
        Aluguel aluguel = new Aluguel(cliente, vendedor,LocalDate.of(2020, 06, 02), LocalDate.of(2020, 06, 05), LocalDate.of(2020, 06, 8), "Barra 2 cm");
        Aluguel aluguel2 = new Aluguel(cliente, vendedor2,LocalDate.of(2020, 06, 02), LocalDate.of(2020, 06, 05), LocalDate.of(2020, 06, 8), "Barra 2 cm");
        
       
        aluguel.setRoupas(new HashSet<Roupa>());
        aluguel.getRoupas().add(roupa);
        
        aluguel2.setRoupas(new HashSet<Roupa>());
        aluguel2.getRoupas().add(roupa);
        
        AluguelDao aluguelDao = new AluguelDao();
        aluguelDao.save(aluguel);
        aluguelDao.save(aluguel2);
        
        Aluguel aluConsu = new Aluguel();
        aluConsu = aluguelDao.aluguelCompletebyID("1","2");
        showAluguel(aluConsu);
        
       
        /*for(Aluguel alu:  aluguelDao.filterAluguelByRoupa("Azul", "G"))
        	showAluguel(alu);*/
    }
    
    private static void showAluguel(Aluguel aluguel) {
    	System.out.println("" + aluguel.getId());
    	System.out.println("" + aluguel.getAjuste());
    	System.out.println("" + aluguel.getDataDevolucao());
    	System.out.println("" + aluguel.getDataLocacao());
    	System.out.println("" + aluguel.getDataRetirada());
    
    	showCliente(aluguel.getCliente());
    }
    
    private static void showCliente(Cliente cliente) {
    	System.out.println("" + cliente.getNome());
    	System.out.println("" + cliente.getCpf());
    	System.out.println("" + cliente.getEmail());
    	System.out.println("" + cliente.getBairro());
    	System.out.println("" + cliente.getNumero());
    	System.out.println("" + cliente.getRua());
    	System.out.println("" + cliente.getCidade());
    }
}
