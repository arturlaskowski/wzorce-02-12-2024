package wzorce.structural.composite.solution;

class FileSystemService {

    public int calculateTotalSize(FileSystemComponent fileSystemComponent) {
        return fileSystemComponent.getSize();
    }
}
