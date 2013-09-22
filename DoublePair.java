/*
 * CS 61C Fall 2013 Project 1
 *
 * DoublePair.java is a class which stores two doubles and 
 * implements the Writable interface. It can be used as a 
 * custom value for Hadoop. To use this as a key, you can
 * choose to implement the WritableComparable interface,
 * although that is not necessary for credit.
 */

import java.io.DataInput;
import java.io.DataOutput;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;


public class DoublePair implements WritableComparable<DoublePair> {
    // Declare any variables here
	private	double num1;
	private double num2;
    /**
     * Constructs a DoublePair with both doubles set to zero.
     */
    public DoublePair() {
        // YOUR CODE HERE
	num1 = 0;
	num2 = 0;
    }

    /**
     * Constructs a DoublePair containing double1 and double2.
     */ 
    public DoublePair(double double1, double double2) {
	num1 = double1;
	num2 = double2;
    }

    /**
     * Returns the value of the first double.
     */
    public double getDouble1() {
        // YOUR CODE HERE
       	return num1; 
    }

    /**
     * Returns the value of the second double.
     */
    public double getDouble2() {
        // YOUR CODE HERE
       	return num2;
    }

    /**
     * Sets the first double to val.
     */
    public void setDouble1(double val) {
        // YOUR CODE HERE
		num1 = val;
    }

    /**
     * Sets the second double to val.
     */
    public void setDouble2(double val) {
        // YOUR CODE HERE
		num2 = val;
    }

    /**
     * write() is required for implementing Writable.
     */
    public void write(DataOutput out) throws IOException {
        // YOUR CODE HERE
		out.writeDouble(num1);
		out.writeDouble(num2);
    }

    /**
     * readFields() is required for implementing Writable.
     */
    public void readFields(DataInput in) throws IOException {
        // YOUR CODE HERE
		num1 = in.readDouble();
		num2 = in.readDouble();
    }

  	/** 
	*  compareTo() is required for the Writeable comparable interface
	   compares to DoublePair objects. 
		first compares num1
		if num1 are equal, compares num2
		if num2 are equal returns 0. By conventon, comparison are this - other
*/
	public int compareTo(DoublePair other){
		if(num1-other.num1 < 0)
			return -1;
		if(num1-other.num1 > 0)
			return 1;
		if(num2-other.num2 < 0)
			return -1;
		if(num2-other.num2 > 0)
			return 1;
		return 0;
	}
	
	/**
		Returns a hashCode for this double pair
	*/
	public int hashCode(){
		int hash = 17;
		hash = hash*29 + new Double(num1).hashCode();
		hash = hash*31 + new Double(num2).hashCode();
		return hash;
	}

	public static void main(String[] args) {
       		DoublePair blank = new DoublePair();
       		DoublePair first = new DoublePair(3.5, 4);
        	DoublePair second = new DoublePair(5,6.1);
        	DoublePair third = new DoublePair(3.5,8);

        System.out.println();

        System.out.println("Testing comparison:");
        System.out.println("first compareTo second should output -1. Result is " + 
            (first.compareTo(second)== -1 ? "CORRECT." : "INCORRECT."));
        System.out.println("second compareTo first should output 1. Result is " +
            (second.compareTo(first)== 1 ? "CORRECT." : "INCORRECT."));
        System.out.println("first compareTo third should output -1. Result is " + 
            (first.compareTo(third)== -1 ? "CORRECT." : "INCORRECT."));
        System.out.println("third compareTo first should output 1. Result is " +
            (third.compareTo(first)== 1 ? "CORRECT." : "INCORRECT."));
        System.out.println("first compareTo blank should output 1. Result is " + 
            (first.compareTo(blank)== 1 ? "CORRECT." : "INCORRECT."));
    }

}
