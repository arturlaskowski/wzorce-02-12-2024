package wzorce.structural.composite.problem;

import lombok.Getter;

@Getter
class File {

    private final String name;
    private final int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

}