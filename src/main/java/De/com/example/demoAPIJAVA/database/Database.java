package De.com.example.demoAPIJAVA.database;

import De.com.example.demoAPIJAVA.Repository.CustomerRepository;
import De.com.example.demoAPIJAVA.Repository.ProductRepository;
import De.com.example.demoAPIJAVA.models.Customer;
import De.com.example.demoAPIJAVA.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
        private static Logger logger = LoggerFactory.getLogger(Database.class);
        @Bean
        CommandLineRunner initDatabase(ProductRepository productRepository){
           return new CommandLineRunner() {
               @Override
               public void run(String... args) throws Exception {
                   Product productA = new Product(1l,"mac IOS",2000,"");
                   Product productB = new Product(2l,"mac book",2500,"");
                   /*logger.info("insert database"+ productRepository.save(productA));
                   logger.info("insert database"+ productRepository.save(productB));*/
               }
           };
        }
        @Bean
        CommandLineRunner initDatabaseCustomer(CustomerRepository CustomerRepository){
            return new CommandLineRunner() {
                @Override
                public void run(String... args) throws Exception {
                    Customer CustomerA = new Customer("Vo Hoang Duong","548712","Duongne@gmail.com");
                    Customer CustomerB = new Customer("Tran Van C","456789","Cus2@gmail.com");
                       /*logger.info("insert database"+ CustomerRepository.save(CustomerA));
                       logger.info("insert database"+ CustomerRepository.save(CustomerB));*/
                }
            };
    }


}
