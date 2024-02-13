import java.io.*;
import java.util.*;

/*
* Klasa ObjectPlus jest klasą bazową, która umożliwia zarządzanie ekstensjami obiektów z poziomu samej klasy.
* Wszystkie metody są statyczne, co oznacza, że można ich używać bez tworzenia obiektów klasy ObjectPlus.
* Dzięki temu, każda klasa dziedzicząca po klasie ObjectPlus może korzystać z funkcjonalności ekstensji.
*/
public class ObjectPlus implements Serializable {
    //Ścieżka do pliku .ser (trwałość)
    private final static String saveFilePath = "save.ser";

    // Mapa, gdzie kluczem jest klasa, a wartością lista obiektów
    private static Map<Class, List<ObjectPlus>> allExtents = new Hashtable<>();

    /**
     * Konstruktor
     */
    public ObjectPlus() {
        List<ObjectPlus> extent = null; //  będzie przechowywać listę wszystkich obiektów danej klasy
        Class theClass = this.getClass(); // Pobiera informację o klasie obiektu, którą każdy obiekt dziedziczy po klasie Object
        if (allExtents.containsKey(theClass)) {
            // Jeśli ekstensja już istnieje, to przypisuje jej wartość do zmiennej extent
            extent = allExtents.get(theClass);
        } else {
            // Jeśli ekstensja nie istnieje, to tworzy nową pustą listę i dodaje ją do zmiennej allExtents
            extent = new ArrayList();
            allExtents.put(theClass, extent);
        }
        // Dodaje aktualny obiekt do listy extent
        extent.add(this);
    }

    /*
    * W ten sposób, każdy nowo utworzony obiekt danej klasy jest automatycznie dodawany do odpowiedniej ekstensji,
    * dzięki czemu można później łatwo zarządzać wszystkimi obiektami danej klasy.
    */

    // Zapisuje wszystkie ekstensje do pliku .ser
    public static void writeExtents() throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(saveFilePath))) {
            output.writeObject(allExtents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Odczytuje wszystkie ekstensje z pliku .ser
    public static void readExtents() throws IOException, ClassNotFoundException {
        try (ObjectInputStream output = new ObjectInputStream(new FileInputStream(saveFilePath))) {
            allExtents = (Hashtable) output.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Służy do "pokazania" ekstensji
    public static void showExtent(Class theClass) throws Exception {
        List<ObjectPlus> extent = null;

        // Sprawdza, czy istnieje ekstensja dla danej klasy obiektów
        if (allExtents.containsKey(theClass)) {
            // Jeśli ekstensja istnieje, to wyświetla wszystkie obiekty z tej ekstensji
            extent = allExtents.get(theClass);
        } else {
            // Jeśli ekstensja nie istnieje, to zgłasza wyjątek z informacją o nieznanej klasie obiektów
            throw new Exception("Unknown class " + theClass);
        }
        System.out.println("Extent of the class: " + theClass.getSimpleName());
        for (Object obj : extent) {
            System.out.println(obj);
        }
    }

    // Wyciąganie z ekstensji interesującego nas elementu
    public static List<ObjectPlus> getFromExtent(Class theClass) throws Exception {
        List<ObjectPlus> extent = null;

        // Sprawdza, czy istnieje ekstensja dla danej klasy obiektów
        if (allExtents.containsKey(theClass)) {
            // Jeśli ekstensja istnieje, to wyświetla wszystkie obiekty z tej ekstensji
            extent = allExtents.get(theClass);
        } else {
            // Jeśli ekstensja nie istnieje, to zgłasza wyjątek z informacją o nieznanej klasie obiektów
            throw new Exception("Unknown class " + theClass);
        }
        return extent;
    }

    // Tworzy plik (o ile go nie ma) i zapisuje do niego ekstensje
    public static void save() throws IOException {
        File file = new File(saveFilePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeExtents();
    }

    public static void load() throws ValidationException, IOException, ClassNotFoundException {
        File file = new File(saveFilePath);
        if (!file.exists()) {
            throw new ValidationException("Nie odnaleziono pliku");
        }
        readExtents();
    }
}
