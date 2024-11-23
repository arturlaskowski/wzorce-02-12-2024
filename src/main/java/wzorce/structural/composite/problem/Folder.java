package wzorce.structural.composite.problem;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class Folder {

    private final String name;
    private final List<File> files = new ArrayList<>();
    private final List<Folder> folders = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void addFolder(Folder folder) {
        folders.add(folder);
    }
}