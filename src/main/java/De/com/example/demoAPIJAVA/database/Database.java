package De.com.example.demoAPIJAVA.database;

import De.com.example.demoAPIJAVA.Repository.ProductRepository;
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


}
