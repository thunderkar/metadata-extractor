package com.drew.imaging.avi;

import com.drew.imaging.riff.RiffProcessingException;
import com.drew.imaging.riff.RiffReader;
import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.avi.AviRiffHandler;
import com.drew.metadata.file.FileSystemMetadataReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Obtains metadata from AVI files.
 *
 * @author Payton Garland
 */
public class AviMetadataReader
{
    @NotNull
    public static Metadata readMetadata(@NotNull File file) throws IOException, RiffProcessingException
    {
        InputStream inputStream = new FileInputStream(file);
        Metadata metadata;
        try {
            metadata = readMetadata(inputStream);
        } finally {
            inputStream.close();
        }
        new FileSystemMetadataReader().read(file, metadata);
        return metadata;
    }

    @NotNull
    public static Metadata readMetadata(@NotNull InputStream inputStream) throws IOException, RiffProcessingException
    {
        Metadata metadata = new Metadata();
        new RiffReader().processRiff(new StreamReader(inputStream), new AviRiffHandler(metadata));
        return metadata;
    }
}