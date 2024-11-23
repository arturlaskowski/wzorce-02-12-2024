package wzorce.structural.composite.solution;

import lombok.Getter;

@Getter
abstract class FileSystemComponent {

    protected String name;

    FileSystemComponent(String name) {
        this.name = name;
    }

    abstract int getSize();
}
