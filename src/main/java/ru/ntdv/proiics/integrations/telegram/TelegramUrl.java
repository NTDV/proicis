package ru.ntdv.proiics.integrations.telegram;

import lombok.Getter;
import ru.ntdv.proiics.crud.model.User;
import ru.ntdv.proiics.integrations.contract.Url;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class TelegramUrl implements Url {
    private static final Pattern pattern = Pattern.compile("((?<id>.+)\\.t.me)|((http://|https://)?t\\.me/|@).+");
    private final URL url;

    public TelegramUrl(String stringUrl) throws IllegalArgumentException {
        url = getUrlFromString(stringUrl);
    }

    public TelegramUrl(final User user) throws IllegalArgumentException {
        this(user.getUrlVkontakte());
    }

    @Override
    public URL getLink() {
        return url;
    }

    @Override
    public String getLinkAsString() {
        return url.toString();
    }

    public static URL getUrlFromString(String stringUrl) throws IllegalArgumentException {
        var matcher = pattern.matcher(stringUrl.trim());
        if (matcher.matches()) {
            try {
                var id = matcher.group("id");
                return new URL("https://t.me/" + id);
            } catch (final MalformedURLException | IllegalArgumentException forwarded1) {
                try {
                    return new URL("https://t.me/" + matcher.group(matcher.groupCount()));
                } catch (final MalformedURLException forwarded2) {
                    try {
                        return new URL("https://t.me/" + stringUrl);
                    } catch (final MalformedURLException forwarded3) {
                        throw new IllegalArgumentException("Неверная ссылка на профиль Телеграм.");
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Неверный формат ссылки.");
        }
    }
}
