package ru.ntdv.proiics.buisness.model;

import com.ibm.icu.text.Transliterator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.access.AccessDeniedException;
import ru.ntdv.proiics.crud.contract.FileAccessPolicy;
import ru.ntdv.proiics.graphql.model.User;
import ru.ntdv.proiics.security.LoginManager;

import java.nio.file.Path;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class File {
    private static final String _cyrillicToLatin = "Russian-Latin/BGN";
    private static final Transliterator cyryllicToLatin = Transliterator.getInstance(_cyrillicToLatin);

    private Path path;
    @Getter
    private String originalName;
    private Long ownerId;
    private Set<FileAccessPolicy> accessPolicy;

    public File(final Path rootPath, final ru.ntdv.proiics.crud.model.File file) {
        this(rootPath.resolve(file.getId().toString()),
                cyryllicToLatin.transform(file.getOriginalName().replace(' ', '_')),
                file.getOwner().getId(),
                file.getAccessPolicy());
    }

    public FileSystemResource getBy(final User user) throws AccessDeniedException {
        if (user == null && accessPolicy.contains(FileAccessPolicy.Public)) return new FileSystemResource(path);
        else if (user == null) throw new AccessDeniedException("File can not be accessed by unregistered user.");
        else if (!user.getId().equals(ownerId) && accessPolicy.contains(FileAccessPolicy.Owner)) throw new AccessDeniedException("File can not by accessed.");
        else return new FileSystemResource(path);
    }
}
