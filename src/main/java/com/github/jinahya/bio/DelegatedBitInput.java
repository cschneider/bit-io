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


package com.github.jinahya.bio;


import java.io.IOException;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class DelegatedBitInput extends AbstractBitInput {


    /**
     * Create a new instance with specified delegate.
     *
     * @param delegate the delegate
     */
    public DelegatedBitInput(final ByteInput delegate) {

        super();

        this.delegate = delegate;
    }


    /**
     * {@inheritDoc} The {@code readUnsignedByte()} method of
     * {@code DelegatedBitInput} class returns the value of
     * <pre>delegate.readUnsignedByte()</pre>. Override this method if
     * {@link #delegate} is supposed to be lazily initialized.
     *
     * @return {@inheritDoc}
     *
     * @throws IOException {@inheritDoc}
     */
    @Override
    public int readUnsignedByte() throws IOException {

        return delegate.readUnsignedByte();
    }


    /**
     * The delegate on which {@link #readUnsignedByte()} is invoked.
     */
    protected ByteInput delegate;


}
