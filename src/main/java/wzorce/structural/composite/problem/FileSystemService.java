package wzorce.structural.composite.problem;

class FileSystemService {

    public int calculateTotalSize(Folder root) {
        int totalSize = 0;
        for (Folder folder : root.getFolders()) {
            totalSize += calculateTotalSize(folder);
        }
        for (File file : root.getFiles()) {
            totalSize += file.getSize();
        }
        return totalSize;
    }
}
