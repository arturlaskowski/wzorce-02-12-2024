package wzorce.structural.composite.problem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileSystemServiceTest {

    private final FileSystemService fileSystemService = new FileSystemService();

    @Test
    void calculateFolderSize() {
        File file1 = new File("Document1.txt", 1200);
        File file2 = new File("Document2.txt", 800);

        // Tworzenie folderu i dodawanie plików
        Folder folder1 = new Folder("My Documents");
        folder1.addFile(file1);
        folder1.addFile(file2);

        // Tworzenie folderu głównego i dodanie podfolderu oraz dodatkowego pliku
        Folder rootFolder = new Folder("Root Folder");
        rootFolder.addFolder(folder1);
        rootFolder.addFile(new File("Readme.md", 500));

        var totalSize = fileSystemService.calculateTotalSize(rootFolder);

        Assertions.assertEquals(2500, totalSize);
    }

    @Test
    void calculateFileSize() {
        File file = new File("Document1.txt", 1200);

        // Dla tego serwisu nie powinno mieć znaczenia, czy to plik, czy folder.
        //  var totalSize = fileSystemService.calculateTotalSize(file);

        //  assertEquals(1200, totalSize);
    }
}