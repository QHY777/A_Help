package TipsOrTools;

import org.junit.Test;

public class FilesRenameTest {

    @Test
    public void renameNum() {
    }

    @Test
    public void name3() {
    }

    @Test
    public void renameFromNum() {
    }

    @Test
    public void renameFromNumToOtherDir() {
    }

    @Test
    public void replaceName() {
    }

    @Test
    public void replaceAll() {
    }

    @Test
    public void deleteAll() {
        String[] target={"XunACG-地址发布站.url","XunACG-更多免费资源下载.url","更多免费资源下载.txt"};
        new FilesRename().deleteAllEqualFile("G:\\test",target);
    }
    @Test
    public void deleteAllDir() {
        String[] target={"更多优质资源（务必查看）"};
        new FilesRename().deleteAllEqualDir("G:\\test",target);
    }

    @Test
    public void main() {
    }
}
