
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class BinaryStdOut {
	private static BufferedOutputStream out;// output stream (standard output)
	private static int buffer; // 8-bit buffer of bits to write
	static OutputStream inu;
    private FileOutputStream out1;

	private static int n; // number of bits remaining in buffer
	private static boolean isInitialized; // has BinaryStdOut been called for first time?

	BinaryStdOut(FileOutputStream fos) throws FileNotFoundException {
		inu = fos;
		out1 = fos ;
		initialize();
	}

	  void initialize() {
		out = new BufferedOutputStream(inu);

		buffer = 0;
		n = 0;
		isInitialized = true;
	}

	private  void writeBit(boolean bit) {
		if (!isInitialized)
			initialize();

		// add bit to buffer
		buffer <<= 1;
		if (bit)
			buffer |= 1;

		// if buffer is full (8 bits), write out as a single byte
		n++;
		if (n == 8)
			clearBuffer();
	}

	private  void writeByte(int x) {
		if (!isInitialized)
			initialize();

		assert x >= 0 && x < 256;

		if (n == 0) {
			try {
				out.write(x);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		for (int i = 0; i < 8; i++) {
			boolean bit = ((x >>> (8 - i - 1)) & 1) == 1;
			writeBit(bit);
		}
	}

	  void clearBuffer() {
		if (!isInitialized)
			initialize();

		if (n == 0)
			return;
		if (n > 0)
			buffer <<= (8 - n);
		try {
			out.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		n = 0;
		buffer = 0;
	}

	public  void flush() {
		clearBuffer();
		try {
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public  void close() {
		flush();
		try {
			out.close();
			isInitialized = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public  void write(boolean x) {
		writeBit(x);
	}

	public  void write(byte x) {
		writeByte(x+128 & 0xff);
	}

	public  void write(int x) {
		writeByte((x >>> 24) & 0xff);
		writeByte((x >>> 16) & 0xff);
		writeByte((x >>> 8) & 0xff);
		writeByte((x >>> 0) & 0xff);
	}

	public   void write(int x, int r) {
		if (r == 32) {
			write(x);
			return;
		}
		if (r < 1 || r > 32)
			throw new IllegalArgumentException("Illegal value for r = " + r);
		if (x < 0 || x >= (1 << r))
			throw new IllegalArgumentException("Illegal " + r + "-bit char = " + x);
		for (int i = 0; i < r; i++) {
			boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
			writeBit(bit);
		}
	}

	public  void write(double x) {
		write(Double.doubleToRawLongBits(x));
	}

	public  void write(long x) {
		writeByte((int) ((x >>> 56) & 0xff));
		writeByte((int) ((x >>> 48) & 0xff));
		writeByte((int) ((x >>> 40) & 0xff));
		writeByte((int) ((x >>> 32) & 0xff));
		writeByte((int) ((x >>> 24) & 0xff));
		writeByte((int) ((x >>> 16) & 0xff));
		writeByte((int) ((x >>> 8) & 0xff));
		writeByte((int) ((x >>> 0) & 0xff));
	}

	public  void write(float x) {
		write(Float.floatToRawIntBits(x));
	}

	public  void write(short x) {
		writeByte((x >>> 8) & 0xff);
		writeByte((x >>> 0) & 0xff);
	}

	public  void write(char x) {
		if (x < 0 || x >= 256)
			throw new IllegalArgumentException("Illegal 8-bit char = " + x);
		writeByte(x);
	}

	public   void write(char x, int r) {
		if (r == 8) {
			write(x);
			return;
		}
		if (r < 1 || r > 16)
			throw new IllegalArgumentException("Illegal value for r = " + r);
		if (x >= (1 << r))
			throw new IllegalArgumentException("Illegal " + r + "-bit char = " + x);
		for (int i = 0; i < r; i++) {
			boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
			writeBit(bit);
		}
	}

	public  void write(String s) {
		for (int i = 0; i < s.length(); i++)
			write(s.charAt(i));
	}

	public  void write(String s, int r) {
		for (int i = 0; i < s.length(); i++)
			write(s.charAt(i), r);
	}
	
	public void writeH(StringBuilder header) throws IOException {
	      for (int i =0 ;i<header.length() ; i++)
	      {
	    	  write((byte)header.charAt(i));
	      }
	    }

}