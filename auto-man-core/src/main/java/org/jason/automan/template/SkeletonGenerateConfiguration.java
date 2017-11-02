package org.jason.automan.template;

import org.jason.automan.bean.FileCategory;
import org.jason.automan.bean.FileType;

/**
 * Created by Jason.Xia on 16/10/13.
 */
public enum SkeletonGenerateConfiguration {

    CORE_SERVICE("service", "-core", "/core/", FileType.DIR, FileCategory.CODE),
    FACADE_IMPL_INTERACTION("interaction", "-facade-impl", "/facade/impl/", FileType.DIR, FileCategory.CODE);

    public String dirName;
    public String modulePath;
    public String targetFilePath;
    public FileType fileType;
    public FileCategory fileCategory;

    SkeletonGenerateConfiguration(String dirName, String modulePath, String targetFilePath, FileType fileType,
                                  FileCategory fileCategory) {
        this.dirName = dirName;
        this.modulePath = modulePath;
        this.targetFilePath = targetFilePath;
        this.fileType = fileType;
        this.fileCategory = fileCategory;
    }
}
