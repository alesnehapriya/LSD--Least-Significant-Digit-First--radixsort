package LSDAlgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author Sneha Priya Ale
 *
 */
public class LSDAlgo {

	Scanner scan = null;
	int n = 0;// number of Strings
	int l = 0;// number of Strings for input file
	File RandomString, SortedString; // To write to external Text File
	FileWriter file_writer, file_writer_out;
	BufferedWriter buffer_file_writer, bw;
	int asci = 0; // Strong random selected ascii value
	int choice = 0;// Selection of the Menu
	FileInputStream file; // Already Stored FileName
	String file_name, line;
	BufferedReader file_reader = null;
	String[][] arry;// 2-D array storing String Character
	String[] oneDArray;
	String[] pointer = null, temp = null;

	LSDAlgo() {
		scan = new Scanner(System.in);
		System.out.println("Enter your choice:\n1.Enter the File Name.\n2.Create a random String Generator File");
		choice = scan.nextInt();
		if (choice == 2) {
			System.out.println("Enter the number of random Strings you want to generate");
			n = scan.nextInt();
			RandomString = new File("f.txt");
			SortedString = new File("g.txt");

			try {
				if (!RandomString.exists()) {
					RandomString.createNewFile();
				} else {
					RandomString.delete();
					RandomString.createNewFile();
				}
				if (!SortedString.exists()) {
					SortedString.createNewFile();
				} else {
					SortedString.delete();
					SortedString.createNewFile();
				}
				file_writer = new FileWriter(RandomString.getAbsoluteFile());
				file_writer_out = new FileWriter(SortedString.getAbsoluteFile());
				buffer_file_writer = new BufferedWriter(file_writer);
				bw = new BufferedWriter(file_writer_out);
				for (int i = 0; i < n; i++) {
					int rand = (int) (Math.random() * (21)) + 1;
					for (int j = 0; j < rand; j++) {
						buffer_file_writer.write(Character.toString((char) ((Math.random() * (90 - 65)) + 65)));
					}
					buffer_file_writer.newLine();
				}
				file = new FileInputStream("f.txt");
				buffer_file_writer.close();
				file_writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Random Input String Data  has been generated and saved as f.txt.");

		} else {
			System.out.println("Enter the FileName");
			file_name = scan.next();
			try {
				file = new FileInputStream(file_name);
				SortedString = new File("g.txt");
				if (!SortedString.exists()) {
					SortedString.createNewFile();
				} else {
					SortedString.delete();
					SortedString.createNewFile();
				}
				file_reader = new BufferedReader(new InputStreamReader(file));
				file_writer_out = new FileWriter(SortedString.getAbsoluteFile());
				bw = new BufferedWriter(file_writer_out);
				System.out.println("Enter the number of elements in the entered file:");
				n = scan.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			file_reader = new BufferedReader(new InputStreamReader(file));
			arry = new String[n][21];
			pointer = new String[n];

			int x = 0, y = 0;
			/**
			 * Filling the 2D array S with blank Space
			 */
			for (int i = 0; i < n; i++) {
				for (int j = i; j < 21; j++) {
					arry[i][j] = " ";
				}
			}
			while (true) {
				line = file_reader.readLine();
				if (line == null)
					break;
				y = 0;
				/**
				 * Filling the 2D array with Character of each line
				 */
				System.out.println("----------");
				for (String name : line.split("")) {
					arry[x][y] = (String) name;
					System.out.print(arry[x][y] + " ");
					y++;
					// System.out.print(name+" ");
				}
				x++;
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		mode(arry);
		for (int i = 0; i < n; i++) {
			System.out.println(oneDArray[i]);
		}
		LSDRadix(oneDArray);
		for (int i = 0; i < n; i++) {
			System.out.println(oneDArray[i]);
		}

		try {
			for (int i = 0; i < n; i++) {
				bw.write(oneDArray[i]);
				bw.newLine();
			}
			bw.close();
			file_writer_out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void mode(String[][] arr) {
		oneDArray = new String[n];
		for (int i = 0; i < n; i++) {
			oneDArray[i] = "";

			for (int s = 0; s < 21; s++) {
				if (arr[i][s] != null)
					oneDArray[i] = oneDArray[i] + arr[i][s];
				else
					oneDArray[i] = oneDArray[i] + " ";
			}
		}
	}

	public void LSDRadix(String[] pointer) {
		// TODO Auto-generated method stub
		int R = 256;
		int N = pointer.length;
		int W = pointer[0].length();
		temp = new String[N];
		System.out.println("N: " + N + " W: " + W);
		for (int d = W - 1; d >= 0; d--) {
			int[] count = new int[R + 1];
			for (int i = 0; i < N; i++)
				count[pointer[i].charAt(d) + 1]++;
			for (int r = 0; r < R; r++)
				count[r + 1] += count[r];
			for (int i = 0; i < N; i++)
				temp[count[pointer[i].charAt(d)]++] = pointer[i];
			for (int i = 0; i < N; i++)
				pointer[i] = temp[i];

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LSDAlgo();

	}

}
