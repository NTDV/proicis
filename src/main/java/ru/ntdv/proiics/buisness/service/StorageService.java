package ru.ntdv.proiics.buisness.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    Path getRootPath();

    void init() throws FileNotFoundException;

    void store(MultipartFile file, String fileName) throws FileSystemException;

    void store(StringBuilder data, String originalFileName, String filename) throws FileSystemException;

    Stream<Path> loadAll() throws FileSystemException;

    Path load(String filename);

    Resource loadAsResource(String filename) throws FileNotFoundException;

    void deleteAll();

}