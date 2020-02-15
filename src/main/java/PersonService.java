import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PersonService {
    public JSONArray getPersonsFromFile() throws IOException, ParseException {
        byte[] arrayTo = Files.readAllBytes(Paths.get("/Users/pro/Desktop/test221.txt"));
        String s = new String(arrayTo);
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(s);
        return json;
    }

    public void addPersonToFile(String firstName, String lastName) throws IOException, ParseException {
        JSONArray jsonArray = getPersonsFromFile();
        if (ifPersonExists(jsonArray, firstName)) {
            System.out.println("Такой человек уже есть");
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(firstName, lastName);
            jsonArray.add(jsonObject);
            String array = jsonArray.toString();
            putPersonsToFile(array);
        }
    }

    public boolean ifPersonExists(JSONArray jsonArray, String firsName) throws IOException, ParseException {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if (jsonObject.get(firsName) != null) {
                return true;
            }
        }
        return false;
    }

    public void putPersonsToFile(String persons) throws IOException {
        FileWriter writer = new FileWriter("/Users/pro/Desktop/test221.txt");
        writer.write(persons);
        writer.close();
    }

    public void removeUserFromFile(String firstName) throws IOException, ParseException {
        JSONArray jsonArray = getPersonsFromFile();
        if (ifPersonExists(jsonArray, firstName)) {
            System.out.println(" Нужный пользователь найден ");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                if (jsonObject.get(firstName) != null) {
                    jsonArray.remove(i);
                    putPersonsToFile(jsonArray.toString());
                }
            }
        } else {
            System.out.println("Даннный пльзователь не существует");
        }
    }

    public void editUserFromFile(String editFirstName, String editLasttName) throws IOException, ParseException {
        removeUserFromFile(editFirstName);
        addPersonToFile(editFirstName, editLasttName);
    }

    public void printUsers() throws IOException, ParseException {
        JSONArray jsonArray = getPersonsFromFile();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            System.out.println(jsonObject.toString());
        }
    }

    private void shlyapka() {
        System.out.println("Шляпу на буче ночил?");
    }
}

















