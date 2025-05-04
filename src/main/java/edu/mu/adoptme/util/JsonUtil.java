package edu.mu.adoptme.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.mu.adoptme.model.*;
import edu.mu.adoptme.model.adapter.ExoticAnimal;
import edu.mu.adoptme.model.adapter.ExoticAnimalAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Utility class for loading and saving pets using JSON.
 */
public class JsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Loads regular pets from a JSON file and returns them as Pet objects.
     */
    public static List<Pet> loadRegularPets(String resourceName) {
        try (InputStream inputStream = JsonUtil.class.getClassLoader().getResourceAsStream(resourceName);
             InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8)) {

            Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
            List<Map<String, Object>> petData = gson.fromJson(reader, listType);
            List<Pet> pets = new ArrayList<>();

            for (Map<String, Object> data : petData) {
                String type = (String) data.get("type");
                String name = (String) data.get("name");
                String species = (String) data.get("species");
                int age = ((Number) data.get("age")).intValue();
                boolean adopted = Boolean.TRUE.equals(data.get("adopted"));

                Pet pet = null;
                switch (type) {
                    case "Dog":
                        pet = new Dog(name, species, age);
                        break;
                    case "Cat":
                        pet = new Cat(name, species, age);
                        break;
                    case "Rabbit":
                        pet = new Rabbit(name, species, age);
                        break;
                    default:
                        // Unknown type, skip it
                        continue;
                }

                if (adopted) {
                    pet.adopt();
                }

                pets.add(pet);
            }

            return pets;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Loads exotic pets from a JSON file and adapts them into Pet objects.
     */
    public static List<Pet> loadExoticPets(String resourceName) {
        try (InputStream inputStream = JsonUtil.class.getClassLoader().getResourceAsStream(resourceName);
             InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8)) {

            Type listType = new TypeToken<List<ExoticAnimal>>() {}.getType();
            List<ExoticAnimal> exoticAnimals = gson.fromJson(reader, listType);

            List<Pet> adapted = new ArrayList<>();
            for (ExoticAnimal ea : exoticAnimals) {
                adapted.add(new ExoticAnimalAdapter(ea));
            }

            return adapted;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Saves the list of pets to a new time stamped JSON file.
     */
    public static void savePetsToJson(List<Pet> pets) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = timestamp + "_pets.json";

        List<Map<String, Object>> output = new ArrayList<>();
        for (Pet pet : pets) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("name", pet.getName());
            obj.put("species", pet.getSpecies());
            obj.put("age", pet.getAge());
            obj.put("adopted", pet.isAdopted());
            obj.put("type", pet.getType());
            output.add(obj);
        }

        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(output, writer);
            System.out.println("Pets saved to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}