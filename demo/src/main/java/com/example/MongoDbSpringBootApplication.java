package com.example;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//import com.example.repository.ItemRepository;
//import com.example.model.GroceryItem;
//import com.example.service.GroceryItemService;


@SpringBootApplication
@EnableMongoRepositories
public class MongoDbSpringBootApplication implements CommandLineRunner {

    //@Autowired
    //private ItemRepository groceryItemRepo;

    //@Autowired
    //private GroceryItemService GroceryItemService;

    public static void main(String[] args) {
        SpringApplication.run(MongoDbSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Aquí puedes agregar cualquier lógica que desees ejecutar al arranque de la aplicación
        // Por ejemplo, puedes usar groceryItemRepo para interactuar con la base de datos

        // Ejemplo de uso (descomenta o modifica según tus necesidades):
        // System.out.println("Listando items de la tienda de comestibles:");
        // groceryItemRepo.findAll().forEach(System.out::println);
        /*
        // Crear un nuevo objeto GroceryItem
        GroceryItem item = new GroceryItem();
        item.setName("Manzanas");
        item.setQuantity(10);
        item.setCategory("Frutas");

        // Guardar el objeto en la base de datos
        groceryItemRepo.save(item);

        // Opcional: Mostrar un mensaje de confirmación o el objeto guardado
        System.out.println("Item guardado: " + item);
        */
        //GroceryItemService.addOrUpdateGroceryItem();
        //GroceryItemService.printAllGroceryItems();
    }
}