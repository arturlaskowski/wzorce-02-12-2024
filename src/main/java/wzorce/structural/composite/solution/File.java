package wzorce.structural.composite.solution;

class File extends FileSystemComponent {

    private final int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}