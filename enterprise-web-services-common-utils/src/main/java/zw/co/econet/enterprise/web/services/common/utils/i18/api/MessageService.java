package zw.co.econet.enterprise.web.services.common.utils.i18.api;

import java.util.Locale;

public interface MessageService {
    String getMessage(String propertyName, String[] placeholders, Locale locale);
    String getMessage(String propertyName, Locale locale);
}
