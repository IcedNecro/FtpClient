package org.lyuda.ftp.core;

import java.util.Calendar;

/**
 * Generic adapter for FileNavigator, that provides an abstraction
 * for file in file system
 * @param <E> - class that represents a type of file descriptor in specific file
 * system
 */
public class FileNode<E> {
    
    private E file;
    private FileType type;
    private Calendar time; 
    private String name;
    private Long size;
    
    /**
     * 
     * @param file
     * @param isDirrectory 
     */
    public FileNode(E file, boolean isDirrectory) {
        if(isDirrectory)
            this.type = FileType.DIRRECTORY;
        else 
            this.type = FileType.FILE;
        this.file = file;
    }
    
    public FileType getType(FileType type) {
        return this.type;
    }

    public E getFile() {
        return this.file;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return this.name+" "+this.type.name();
    }
    
    public static enum FileType {
      DIRRECTORY,
      FILE,
    };

    @Override
    public boolean equals(Object o) {
        if(this.file!=null)
            return this.file.equals(o);
        return false;
    }

    
}