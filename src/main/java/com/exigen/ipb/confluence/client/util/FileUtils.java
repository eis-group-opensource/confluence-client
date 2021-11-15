/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.util;

import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Utility class to help with file/folder operations
 *
 * @autor esagan on 1/20/2016.
 * @version 1.0
 */
public final class FileUtils {
    private FileUtils(){}

    /**
     * Returns list of files.
     * The method recursively looks for files with required extension starting from rootFolder
     *
     * @param rootFolder folder which will be used as root for file search
     * @param extensions string list of searched file extensions
     * @return List<File> {@link java.io.File File}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    public static List<File> findFilesWithExtensions(final String rootFolder, final List<String> extensions){
        final List<File> foundFiles = Lists.newArrayList();
        try {
            Files.walkFileTree(Paths.get(rootFolder), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileExtension = com.google.common.io.Files.getFileExtension(file.getFileName().toString());
                    if (extensions.contains(fileExtension)) {
                        foundFiles.add(file.toFile());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
            return foundFiles;
        } catch (IOException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

}
