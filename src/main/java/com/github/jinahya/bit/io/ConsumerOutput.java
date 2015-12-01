/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


package com.github.jinahya.bit.io;


import java.io.IOException;
import java.util.function.Consumer;


/**
 * A {@link ByteOutput} implementation uses a {@link Consumer} instance.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class ConsumerOutput extends AbstractByteOutput<Consumer<Byte>> {


    /**
     * Creates a new instance on the top of given consumer.
     *
     * @param consumer the consumer or {@code null} if it's supposed to be
     * lazily initialized and set
     */
    public ConsumerOutput(final Consumer<Byte> consumer) {

        super(consumer);
    }


    @Override
    public void writeUnsignedByte(final int value) throws IOException {

        target.accept((byte) value);
    }


}

