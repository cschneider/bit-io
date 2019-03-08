package com.github.jinahya.bit.io;

/*-
 * #%L
 * bit-io
 * %%
 * Copyright (C) 2014 - 2019 Jinahya, Inc.
 * %%
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
 * #L%
 */

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/**
 * A {@link ByteOutput} uses an instance of {@link ByteBuffer} as its {@link #target}.
 *
 * @param <T> byte buffer type parameter.
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see BufferByteInput
 */
public class BufferByteOutput<T extends ByteBuffer> extends AbstractByteOutput<T> {

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"Duplicates"})
    public static BufferByteOutput<ByteBuffer> of(final int capacity, final WritableByteChannel channel) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity(" + capacity + ") <= 0");
        }
        if (channel == null) {
            throw new NullPointerException("channel is null");
        }
        return new BufferByteOutput<ByteBuffer>(null) {
            @Override
            public void write(final int value) throws IOException {
                if (target == null) {
                    target = ByteBuffer.allocate(capacity); // position: zero, limit: capacity
                }
                if (!target.hasRemaining()) { // no space to put
                    target.flip(); // limit -> position, position -> zero
                    do {
                        channel.write(target);
                    } while (target.position() == 0);
                    target.compact();
                }
                super.write(value);
            }
        };
    }

    public static int flush(final BufferByteOutput<?> output, final WritableByteChannel channel) throws IOException {
        if (output == null) {
            throw new NullPointerException("output is null");
        }
        if (channel == null) {
            throw new NullPointerException("channel is null");
        }
        // @todo: should we check channel.isOpen?
        int written = 0;
        final ByteBuffer target = output.target;
        if (target != null) {
            for (target.flip(); target.hasRemaining(); ) {
                written += channel.write(target);
            }
            target.clear(); // position -> zero, limit -> capacity
        }
        return written;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance built on top of given {@code ByteBuffer}.
     *
     * @param target the {@code ByteBuffer} to which bytes are written; {@code null} if it's supposed to be lazily
     *               initialized and set.
     */
    public BufferByteOutput(final T target) {
        super(target);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc} The {@code write(int)} method of {@code BufferByteOutput} class invokes {@link
     * ByteBuffer#put(byte)}, on what {@link #getTarget()} gives, with given {@code value}. Override this method if the
     * {@link #target} is supposed to be lazily initialized or adjusted.
     *
     * @param value {@inheritDoc}
     * @throws IOException {@inheritDoc}
     * @see #target
     * @see ByteBuffer#put(byte)
     */
    @Override
    public void write(final int value) throws IOException {
        getTarget().put((byte) value);
    }

    // ---------------------------------------------------------------------------------------------------------- target

    /**
     * {@inheritDoc}
     *
     * @param target {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public BufferByteOutput<T> target(final T target) {
        return (BufferByteOutput<T>) super.target(target);
    }
}
