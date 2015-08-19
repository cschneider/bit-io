/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package com.github.jinahya.bit.io;


import java.io.IOException;


/**
 * An interface for reading arbitrary length of bits.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface BitInput {


    /**
     * Reads a 1-bit boolean value. This method reads {@code true} for
     * {@code 0b1} and {@code false} for {@code 0b0}.
     *
     * @return {@code true} for {@code 0b1}, {@code false} for {@code 0b0}
     *
     * @throws IOException if an I/O error occurs.
     */
    boolean readBoolean() throws IOException;


    /**
     * Reads an unsigned int value.
     *
     * @param length the number of bits for the value; between 1 (inclusive) and
     * 32 (exclusive).
     *
     * @return the unsigned int value
     *
     * @throws IOException if an I/O error occurs
     */
    int readUnsignedInt(int length) throws IOException;


    /**
     * Reads a signed int value.
     *
     * @param length the number of bits for the value; between 1 (exclusive) and
     * 32 (inclusive).
     *
     * @return a signed int value.
     *
     * @throws IOException if an I/O error occurs.
     */
    int readInt(int length) throws IOException;


//    /**
//     * Reads a 32-bit int value converting to {@link Float#intBitsToFloat(int)}.
//     *
//     * @return a float value.
//     *
//     * @throws IOException if an I/O error occurs
//     *
//     * @see Float#intBitsToFloat(int)
//     */
//    float readFloat32() throws IOException;
    /**
     * Reads an unsigned long value.
     *
     * @param length the number of bits for the value; between 1 (inclusive) and
     * 64 (exclusive).
     *
     * @return an unsigned long value.
     *
     * @throws IllegalArgumentException if {@code length} is not valid
     * @throws IOException if an I/O error occurs
     */
    long readUnsignedLong(int length) throws IOException;


    /**
     * Reads a signed long value.
     *
     * @param length the number of bits for the value; between 1 (exclusive) and
     * 64 (inclusive).
     *
     * @return a signed long value
     *
     * @throws IllegalArgumentException if {@code length} is not valid
     * @throws IOException if an I/O error occurs.
     */
    long readLong(int length) throws IOException;


//    /**
//     * Reads a 64-bit double value.
//     *
//     * @return a 64-bit double value
//     *
//     * @throws IOException if an I/O error occurs.
//     */
//    double readDouble64() throws IOException;
    /**
     * Reads a byte array.
     *
     * @param scale the length scale between {@code 0x01} (inclusive) and
     * {@code 0x10} (inclusive).
     * @param range the number of bits for each byte between {@value 0x01}
     * (inclusive) and {@value 0x08} (inclusive).
     *
     * @return a byte array.
     *
     * @throws IllegalArgumentException either {@code scale} or {@code range} is
     * not valid.
     * @throws IOException if an I/O error occurs.
     */
    byte[] readBytes(int scale, int range) throws IOException;


    /**
     * Reads a string. This method reads a byte array via
     * {@link #readBytes(int, int)} with {@code scale} of {@code 0x10} and
     * {@code range} of {@code 0x08} and returns the output string created by
     * {@link String#String(byte[], java.lang.String)} with the byte array and
     * given {@code charsetName}.
     *
     * @param charsetName the character set name to encode output string.
     *
     * @return a string value.
     *
     * @throws IOException if an I/O error occurs.
     *
     * @see #readBytes(int, int)
     * @see String#String(byte[], java.lang.String)
     */
    String readString(String charsetName) throws IOException;


    /**
     * Reads a {@code US-ASCII} encoded string. This method reads a byte array
     * via {@link #readBytes(int, int)} with {@code scale} of {@code 0x10} and
     * {@code range} of {@code 0x07} and returns the output string created by
     * {@link String#String(byte[], java.lang.String)} with the byte array and
     * {@code US-ASCII}.
     *
     * @return a {@code US-ASCII} encoded string.
     *
     * @throws IOException if an I/O error occurs.
     *
     * @see #readBytes(int, int)
     * @see String#String(byte[], java.lang.String)
     */
    String readAscii() throws IOException;


    /**
     * Aligns to given number of bytes.
     *
     * @param length the number of bytes to align; between 1 (inclusive) and
     * {@value java.lang.Short#MAX_VALUE} (inclusive).
     *
     * @return the number of bits discarded for alignment
     *
     * @throws IOException if an I/O error occurs.
     */
    int align(int length) throws IOException;


//    /**
//     * Returns the number of bytes read from the underlying byte input so far.
//     *
//     * @return the number of bytes read so far.
//     */
//    long getCount();
}
