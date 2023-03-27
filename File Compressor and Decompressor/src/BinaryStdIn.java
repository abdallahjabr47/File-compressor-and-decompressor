
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public  class BinaryStdIn {
	private static final int EOF = -1; // end of file

	 static BufferedInputStream in; // input stream
	 static FileInputStream inu;
	 static int buffer; // one character buffer
	 static int n; // number of bits left in buffer
	 static boolean isInitialized; // has BinaryStdIn been called for first time?

	BinaryStdIn(File f) throws FileNotFoundException {
		this.inu = new FileInputStream(f);
		initialize();
		
	}

	 void initialize() {
		in = new BufferedInputStream(inu);
		buffer = 0;
		n = 0;
		fillBuffer();
		isInitialized = true;
	}

	 void fillBuffer() {
		try {
			buffer = in.read();
			n = 8;
		} catch (IOException e) {
			System.out.println("EOF");
			buffer = EOF;
			n = -1;
		}
	}

	void close() {
		if (!isInitialized)
			initialize();
		try {
			in.close();
			isInitialized = false;
		} catch (IOException ioe) {
			throw new IllegalStateException("Could not close BinaryStdIn", ioe);
		}
	}

	 boolean isEmpty() {
		if (!isInitialized)
			initialize();
		
		return buffer == -1;
	}

	public boolean readBoolean() {
		if (isEmpty())
			throw new NoSuchElementException("Reading from empty input stream");
		n--;
		boolean bit = ((buffer >> n) & 1) == 1;
		if (n == 0)
			fillBuffer();
		return bit;
	}

	public char readChar() {
		if (isEmpty())
			throw new NoSuchElementException("Reading from empty input stream");

		if (n == 8) {
			int x = buffer;
			fillBuffer();
			return (char) (x & 0xff);
		}

		int x = buffer;
		x <<= (8 - n);
		int oldN = n;
		fillBuffer();
		if (isEmpty())
			throw new NoSuchElementException("Reading from empty input stream");
		n = oldN;
		x |= (buffer >>> n);
		return (char) (x & 0xff);
	}

	public char readChar(int r) {
		if (r < 1 || r > 16)
			throw new IllegalArgumentException("Illegal value of r = " + r);

		if (r == 8)
			return readChar();

		char x = 0;
		for (int i = 0; i < r; i++) {
			x <<= 1;
			boolean bit = readBoolean();
			if (bit)
				x |= 1;
		}
		return x;
	}

	public String readString() {
		if (isEmpty())
			throw new NoSuchElementException("Reading from empty input stream");

		StringBuilder sb = new StringBuilder();
		while (!isEmpty()) {
			char c = readChar();
			sb.append(c);
		}
		return sb.toString();
	}

	public short readShort() {
		short x = 0;
		for (int i = 0; i < 2; i++) {
			char c = readChar();
			x <<= 8;
			x |= c;
		}
		return x;
	}

	public int readInt() {
		int x = 0;
		for (int i = 0; i < 4 ; i++) {
			char c = readChar();
			x <<= 8;
			x |= c;
		}
		return x;
	}

	public int readInt(int r) {
		if (r < 1 || r > 32)
			throw new IllegalArgumentException("Illegal value of r = " + r);

		if (r == 32)
			return readInt();

		int x = 0;
		for (int i = 0; i < r; i++) {
			x <<= 1;
			boolean bit = readBoolean();
			if (bit)
				x |= 1;
		}
		return x;
	}

	public long readLong() {
		long x = 0;
		for (int i = 0; i < 8; i++) {
			char c = readChar();
			x <<= 8;
			x |= c;
		}
		return x;
	}

	public double readDouble() {
		return Double.longBitsToDouble(readLong());
	}

	public float readFloat() {
		return Float.intBitsToFloat(readInt());
	}

	public byte readByte() {
		char c = readChar();
		return (byte) (c & 0xff);
	}

}
