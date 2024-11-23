package wzorce.structural.composite.solution;

import java.util.ArrayList;
import java.util.List;

class Folder extends FileSystemComponent {

    private final List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}