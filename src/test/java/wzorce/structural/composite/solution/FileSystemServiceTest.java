package wzorce.structural.composite.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileSystemServiceTest {

    private final FileSystemService fileSystemService = new FileSystemService();

    @Test
    void calculateFolderSize() {
        FileSystemComponent file1 = new File("Document1.txt", 1200);
        FileSystemComponent file2 = new File("Document2.txt", 800);

        // Tworzenie folderu i dodawanie plików
        Folder folder1 = new Folder("My Documents");
        folder1.addComponent(file1);
        folder1.addComponent(file2);

        // Tworzenie folderu głównego i dodanie podfolderu oraz dodatkowego pliku
        Folder rootFolder = new Folder("Root Folder");
        rootFolder.addComponent(folder1);
        rootFolder.addComponent(new File("Readme.md", 500));

        assertEquals(2500, rootFolder.getSize());
    }

    @Test
    void calculateFileSize() {
        File file = new File("Document1.txt", 1200);

        var totalSize = fileSystemService.calculateTotalSize(file);

        assertEquals(1200, totalSize);
    }
}