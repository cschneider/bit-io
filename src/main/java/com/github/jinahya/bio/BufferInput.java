/*
 * Copyright 2013 <a href="mailto:onacit@gmail.com">Jin Kwon</a>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.github.jinahya.bio;


import java.io.IOException;
import java.nio.ByteBuffer;


/**
 * A {@link ByteInput} implementation for {@link ByteBuffer}s.
 */
public class BufferInput extends AbstractByteInput<ByteBuffer> {


    /**
     * Creates a new instance built on top of the specified byte buffer.
     *
     * @param source {@inheritDoc}
     */
    public BufferInput(final ByteBuffer source) {

        super(source);
    }


    @Override
    protected ByteBuffer requireValidSource() {

        final ByteBuffer source = super.requireValidSource();

        if (!source.hasRemaining()) {
            throw new IllegalStateException(
                "The underlying byte buffer has no remaining");
        }

        return source;
    }


    /**
     * {@inheritDoc} The {@link #readUnsignedByte()} method of
     * {@code BufferInput} class returns
     * <pre>{@link #requireValidSource()}.get() &amp; 0xFF</pre>.
     *
     * @return {@inheritDoc }
     *
     * @throws IOException {@inheritDoc }
     *
     * @see #requireValidSource()
     * @see ByteBuffer#get()
     */
    @Override
    public int readUnsignedByte() throws IOException {

        return requireValidSource().get() & 0xFF;
    }


}
