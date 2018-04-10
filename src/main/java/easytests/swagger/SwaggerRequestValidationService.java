package easytests.swagger;

import com.atlassian.oai.validator.SwaggerRequestResponseValidator;
import com.atlassian.oai.validator.model.Request;
import com.atlassian.oai.validator.model.Response;
import com.atlassian.oai.validator.model.SimpleRequest;
import com.atlassian.oai.validator.model.SimpleResponse;
import com.atlassian.oai.validator.report.ValidationReport;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * @author atlassian
 */
class SwaggerRequestValidationService {

    private static final String MESSAGE_REQUEST_PATH_MISSING = "validation.request.path.missing";

    private final SwaggerRequestResponseValidator requestValidator;

    SwaggerRequestValidationService(final EncodedResource restInterface) throws IOException {
        this(SwaggerRequestResponseValidator
                .createFor(readReader(restInterface.getReader()))
                .build());
    }

    SwaggerRequestValidationService(final SwaggerRequestResponseValidator requestValidator) {
        Objects.requireNonNull(requestValidator, "A request validator is required.");
        this.requestValidator = requestValidator;
    }

    /**
     * @param servletRequest the {@link HttpServletRequest}
     * @return the build {@link Request} created out of given {@link HttpServletRequest}
     * @throws IOException if the request body can't be read
     */
    Request buildRequest(final HttpServletRequest servletRequest) throws IOException {
        Objects.requireNonNull(servletRequest, "A request is required.");
        final Request.Method method = Request.Method.valueOf(servletRequest.getMethod());
        final String requestUri = getCompleteRequestUri(servletRequest);
        final UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(requestUri)
                .build();
        final String path = uriComponents.getPath();
        final SimpleRequest.Builder builder = new SimpleRequest.Builder(method, path);
        final String body = readReader(servletRequest.getReader());
        // The content length of a request does not need to be set. It might by "-1" and
        // there is still a body. Only in conjunction with an empty / unset body it was
        // really empty.
        if (servletRequest.getContentLength() >= 0 || (body != null && !body.isEmpty())) {
            builder.withBody(body);
        }
        for (final Map.Entry<String, List<String>> entry : uriComponents.getQueryParams().entrySet()) {
            builder.withQueryParam(entry.getKey(), entry.getValue());
        }
        for (final String headerName : Collections.list(servletRequest.getHeaderNames())) {
            builder.withHeader(headerName, Collections.list(servletRequest.getHeaders(headerName)));
        }
        return builder.build();
    }

    /**
     * @param servletResponse the {@link ContentCachingResponseWrapper}
     * @return the build {@link Response} created out of given {@link ContentCachingResponseWrapper}
     * @throws IOException if the response body can't be write
     */
    Response buildResponse(final ContentCachingResponseWrapper servletResponse) throws IOException {
        final SimpleResponse.Builder builder = new SimpleResponse.Builder(servletResponse.getStatus());

        builder.withBody(new String(servletResponse.getContentAsByteArray()));

        for (final String headerName : servletResponse.getHeaderNames()) {
            builder.withHeader(headerName, new ArrayList(servletResponse.getHeaders(headerName)));
        }

        return builder.build();
    }

    /**
     * @param request the {@link Request} to validate against the Swagger schema
     * @return the {@link ValidationReport} for the validated {@link Request}
     */
    ValidationReport validateRequest(final Request request) {
        return requestValidator.validateRequest(request);
    }

    ValidationReport validateResponse(@Nonnull String path, @Nonnull Request.Method method, final Response response) {
        return requestValidator.validateResponse(path, method, response);
    }


    /**
     * @param validationReport the {@link ValidationReport}
     * @return {@code true} if the validated request is defined in the Swagger schema, otherwise {@code false}
     */
    boolean isDefinedSwaggerRequest(final ValidationReport validationReport) {
        for (final ValidationReport.Message message : validationReport.getMessages()) {
            if (MESSAGE_REQUEST_PATH_MISSING.equals(message.getKey())) {
                return false;
            }
        }
        return true;
    }

    private static String readReader(final Reader reader) throws IOException {
        return IOUtils.toString(reader);
    }

    private static String getCompleteRequestUri(final HttpServletRequest servletRequest) {
        if (StringUtils.isBlank(servletRequest.getQueryString())) {
            return servletRequest.getRequestURI();
        }
        return servletRequest.getRequestURI() + "?" + servletRequest.getQueryString();
    }
}
