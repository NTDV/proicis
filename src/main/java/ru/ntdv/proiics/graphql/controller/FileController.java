package ru.ntdv.proiics.graphql.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.ntdv.proiics.buisness.model.File;
import ru.ntdv.proiics.buisness.service.StorageService;
import ru.ntdv.proiics.crud.contract.FileAccessPolicy;
import ru.ntdv.proiics.crud.model.UserRole;
import ru.ntdv.proiics.crud.service.CredentialsService;
import ru.ntdv.proiics.crud.service.FileService;
import ru.ntdv.proiics.crud.service.UserService;
import ru.ntdv.proiics.graphql.input.CredentialsInput;
import ru.ntdv.proiics.graphql.input.UserInput;
import ru.ntdv.proiics.graphql.model.FileUploadResult;
import ru.ntdv.proiics.graphql.model.User;
import ru.ntdv.proiics.security.LoginManager;
import ru.ntdv.proiics.security.PasswordManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystemException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

@Controller
public class FileController {
    private final StorageService storageService;
    private final UserService userService;
    private final CredentialsService credentialsService;
    private final FileService fileService;

    @Autowired
    public FileController(final FileService fileService, final CredentialsService credentialsService, final UserService userService, final StorageService storageService) {
        this.fileService = fileService;
        this.credentialsService = credentialsService;
        this.userService = userService;
        this.storageService = storageService;
    }

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @MutationMapping
    public FileUploadResult uploadFile(@Argument final MultipartFile file, @Argument final long owner, @Argument final List<FileAccessPolicy> policy) throws FileUploadException {
        logger.info("Upload file: name={}", file.getOriginalFilename());

        try {
            final var fileObject = fileService.save(new ru.ntdv.proiics.crud.model.File(file.getOriginalFilename(), userService.getUserById(owner), new HashSet<>(policy)));
            storageService.store(file, fileObject.getId().toString());
            return new FileUploadResult(fileObject.getId());
        } catch (final FileSystemException e) {
            throw new FileUploadException("Can not upload file", e);
        }
    }

    @MutationMapping
    @Secured({"ROLE_Administrator", "ROLE_Moderator"})
    public FileUploadResult uploadParticipants(@Argument final MultipartFile file) throws CsvValidationException {
        final var owner = credentialsService.loadUserByUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).getUser();
        final var data = new LinkedList<Triple<CredentialsInput, UserInput, UserRole.Role>>();
        final var out = new StringBuilder();

        try (final CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                final String firstName = lineInArray[0];
                final String secondName = lineInArray[1];
                final String thirdName = lineInArray[2];
                final String vk = lineInArray[3];
                final String tg = lineInArray[4];
                final String group = lineInArray[5];

                final String rawLogin = LoginManager.generateLogin(firstName, secondName, thirdName);
                final String login = rawLogin + credentialsService.getLatestPostfix(rawLogin);
                final String password = PasswordManager.generateRandomPassword();

                final var credentials = new CredentialsInput(login, password);
                final var user = new UserInput(firstName, secondName, thirdName, vk, tg, group, null);

                out.append(login).append(",").append(password).append("\n");
                data.add(new ImmutableTriple<>(credentials, user, UserRole.Role.Participant));
            }
        } catch (final IndexOutOfBoundsException | CsvValidationException | IOException e) {
            logger.error("CSV parsing error", e);
            throw new CsvValidationException("CSV parsing error");
        }

        try {
            userService.registerAll(data);
            final var name = "Register_participants_" + LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + ".csv";
            final var fileObject = fileService.save(new ru.ntdv.proiics.crud.model.File(name, owner, Set.of(FileAccessPolicy.Administrators, FileAccessPolicy.Moderators)));
            storageService.store(out, name, fileObject.getId().toString());
            return new FileUploadResult(fileObject.getId());
        } catch (final FileSystemException e) {
            logger.error("Result saving error", e);
            throw new CsvValidationException("Can not save results of operation");
        }
    }

    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("file_name") String fileName, final HttpServletResponse response) {
        final var file = new File(storageService.getRootPath(), fileService.getById(UUID.fromString(fileName)));
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getOriginalName());
        final var user = credentialsService.loadUserByUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).getUser();
        return file.getBy(new User(user));
    }
}
