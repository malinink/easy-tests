package easytests.swagger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/**
 * @author atlassian
 * A {@link javax.servlet.http.HttpServletRequestWrapper} those {@link ServletInputStream} is
 * cached and can be reset and read again as often as necessary.
 */
public class ResettableRequestServletWrapper extends HttpServletRequestWrapper {
    private ServletInputStream servletInputStream;

    private BufferedReader reader;

    ResettableRequestServletWrapper(final HttpServletRequest request) {
        super(request);
    }

    /**
     * Will rewind the {@link ServletInputStream} to its beginning so it can be
     * read again.
     * <p>
     * Additionally the {@link #getReader()} will be removed if it was used. It will be
     * created again on its next usage.
     * <p>
     * Before resetting the stream the first time it has to be read entirely. The
     * reset will only provide a view from position 0 to the already read bytes from
     * that stream.
     * If the stream wasn't read at all the content will be empty.
     */
    public void resetInputStream() throws IOException {
        if (servletInputStream == null) {
            this.servletInputStream = new CachedServletInputStream(new byte[0]);
        } else if (servletInputStream instanceof WrappedOriginalServletInputStream) {
            final byte[] bytes = ((WrappedOriginalServletInputStream) servletInputStream)
                    .byteArrayOutputStream.toByteArray();
            this.servletInputStream = new CachedServletInputStream(bytes);
        } else if (servletInputStream instanceof CachedServletInputStream) {
            // just reset the already cached stream
            this.servletInputStream.reset();
        }
        this.reader = null;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (servletInputStream == null) {
            this.servletInputStream = new WrappedOriginalServletInputStream(
                    super.getInputStream(),
                    super.getContentLength()
            );
        }
        return this.servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (this.reader == null) {
            final String encoding = super.getCharacterEncoding();
            final InputStreamReader streamReader = encoding == null
                    ? new InputStreamReader(this.getInputStream())
                    : new InputStreamReader(this.getInputStream(), encoding);
            this.reader = new BufferedReader(streamReader);
        }
        return this.reader;
    }

    /**
     * A {@link ServletInputStream} wrapping the original request and saving all read bytes.
     */
    private static final class WrappedOriginalServletInputStream extends ServletInputStream {

        private final ServletInputStream originalServletInputStream;

        private final ByteArrayOutputStream byteArrayOutputStream;

        private WrappedOriginalServletInputStream(
                final ServletInputStream originalServletInputStream,
                final int contentLength
        ) {
            this.originalServletInputStream = originalServletInputStream;
            this.byteArrayOutputStream = new ByteArrayOutputStream(contentLength > 0 ? contentLength : 1024);
        }

        @Override
        public boolean isFinished() {
            return originalServletInputStream.isFinished();
        }

        @Override
        public boolean isReady() {
            return originalServletInputStream.isReady();
        }

        @Override
        public void setReadListener(final ReadListener readListener) {
            originalServletInputStream.setReadListener(readListener);
        }

        @Override
        public int read() throws IOException {
            final int value = originalServletInputStream.read();
            if (value != -1) {
                byteArrayOutputStream.write(value);
            }
            return value;
        }

        @Override
        public int read(final byte[] b, final int off, final int len) throws IOException {
            final int result = originalServletInputStream.read(b, off, len);
            if (result > 0) {
                byteArrayOutputStream.write(b, off, result);
            }
            return result;
        }
    }

    /**
     * A {@link ServletInputStream} backed by a non-blocking {@link java.io.InputStream}
     * which can be reset freely.
     */
    private static final class CachedServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream inputStream;

        private CachedServletInputStream(final byte[] bytes) {
            this.inputStream = new ByteArrayInputStream(bytes);
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public int read(final byte[] b, final int off, final int len) throws IOException {
            return inputStream.read(b, off, len);
        }

        @Override
        public boolean isFinished() {
            return inputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(final ReadListener readListener) {
            throw new IllegalStateException("Can't set ReadListener on CachedServletInputStream.");
        }

        @Override
        public void reset() {
            inputStream.reset();
        }
    }
}
