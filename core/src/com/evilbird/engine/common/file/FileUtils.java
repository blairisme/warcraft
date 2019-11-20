/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.file;

import java.io.File;
import java.io.IOException;

public class FileUtils
{
    /**
     * Cleans a directory without deleting it.
     *
     * @param directory directory to clean
     * @throws IOException              in case cleaning is unsuccessful
     * @throws IllegalArgumentException if {@code directory} does not exist or is not a directory
     */
    public static void cleanDirectory(final File directory) throws IOException {
        final File[] files = verifiedListFiles(directory);

        IOException exception = null;
        for (final File file : files) {
            try {
                throw new IOException(); //TODO
                //forceDelete(file);
            } catch (final IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }

    /**
     * Deletes a file, never throwing an exception. If file is a directory, delete it and all sub-directories.
     * <p>
     * The difference between File.delete() and this method are:
     * </p>
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>No exceptions are thrown when a file or directory cannot be deleted.</li>
     * </ul>
     *
     * @param file file or directory to delete, can be {@code null}
     * @return {@code true} if the file or directory was deleted, otherwise
     * {@code false}
     *
     * @since 1.4
     */
    public static boolean deleteQuietly(final File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (final Exception ignored) {
            // ignore
        }

        try {
            return file.delete();
        } catch (final Exception ignored) {
            return false;
        }
    }

//    /**
//     * Deletes a file. If file is a directory, delete it and all sub-directories.
//     * <p>
//     * The difference between File.delete() and this method are:
//     * </p>
//     * <ul>
//     * <li>The directory does not have to be empty.</li>
//     * <li>You get exceptions when a file or directory cannot be delete;
//     * {@link java.io.File#delete()} returns a boolean.</li>
//     * </ul>
//     *
//     * @param file file or directory to delete, must not be {@code null}
//     * @throws NullPointerException  if the directory is {@code null}
//     * @throws FileNotFoundException if the file was not found
//     * @throws IOException           in case deletion is unsuccessful
//     */
//    public static void forceDelete(final File file) throws IOException {
//        final Counters.PathCounters deleteCounters;
//        try {
//            deleteCounters = PathUtils.delete(file.toPath());
//        } catch (IOException e) {
//            throw new IOException("Unable to delete file: " + file, e);
//        }
//
//        if (deleteCounters.getFileCounter().get() < 1 && deleteCounters.getDirectoryCounter().get() < 1) {
//            // didn't find a file to delete.
//            throw new FileNotFoundException("File does not exist: " + file);
//        }
//    }
//
//    /**
//     * Deletes a file or directory. If the path is a directory, delete it and all sub-directories.
//     * <p>
//     * The difference between File.delete() and this method are:
//     * </p>
//     * <ul>
//     * <li>A directory to delete does not have to be empty.</li>
//     * <li>You get exceptions when a file or directory cannot be deleted; {@link java.io.File#delete()} returns a
//     * boolean.
//     * </ul>
//     *
//     * @param path file or directory to delete, must not be {@code null}
//     * @return The visitor used to delete the given directory.
//     * @throws NullPointerException if the directory is {@code null}
//     * @throws IOException if an I/O error is thrown by a visitor method or if an I/O error occurs.
//     */
//    public static PathCounters delete(final Path path) throws IOException {
//        return Files.isDirectory(path) ? deleteDirectory(path) : deleteFile(path);
//    }
//
//    /**
//     * Deletes a directory including sub-directories.
//     *
//     * @param directory directory to delete.
//     * @return The visitor used to delete the given directory.
//     * @throws IOException if an I/O error is thrown by a visitor method.
//     */
//    public static PathCounters deleteDirectory(final Path directory) throws IOException {
//        return visitFileTree(DeletingPathVisitor.withLongCounters(), directory).getPathCounters();
//    }
//
//    /**
//     * Deletes the given file.
//     *
//     * @param file The file to delete.
//     * @return A visitor with path counts set to 1 file, 0 directories, and the size of the deleted file.
//     * @throws IOException if an I/O error occurs.
//     * @throws NotDirectoryException if the file is a directory.
//     */
//    public static PathCounters deleteFile(final Path file) throws IOException {
//        if (Files.isDirectory(file)) {
//            throw new NotDirectoryException(file.toString());
//        }
//        final PathCounters pathCounts = Counters.longPathCounters();
//        final long size = Files.exists(file) ? Files.size(file) : 0;
//        if (Files.deleteIfExists(file)) {
//            pathCounts.getFileCounter().increment();
//            pathCounts.getByteCounter().add(size);
//        }
//        return pathCounts;
//    }

    /**
     * Lists files in a directory, asserting that the supplied directory satisfies exists and is a directory.
     *
     * @param directory The directory to list
     * @return The files in the directory, never null.
     * @throws IOException if an I/O error occurs
     */
    private static File[] verifiedListFiles(final File directory) throws IOException {
        if (!directory.exists()) {
            final String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            final String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        final File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }
        return files;
    }
}
