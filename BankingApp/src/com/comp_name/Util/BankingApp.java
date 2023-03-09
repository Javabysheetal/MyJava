package com.comp_name.Util;

import java.sql.*;
import java.util.*;

public class BankingApp {

	private static Connection connection = null;
	private static PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null;
	private static ResultSet rs = null;
	private static Scanner scan = null;

	public static void main(String[] args) {

		String option = null;
		scan = new Scanner(System.in);

		do {
			// while(true)
			// {

			System.out.println("*********MENU***********");
			System.out.println("1.Check Balance");
			System.out.println("2.Deposit");
			System.out.println("3.Withdraw");
			System.out.println("4.Exit");

			System.out.println("Enter your choice==>");
			int ch = scan.nextInt();

			try {

				connection = JDBCUtil.getConnection();

			switch (ch)
			{
				case 1:
					System.out.println("Enter Account Number==>");
					int accno = scan.nextInt();

					int amount = checkBalance(accno);
					System.out.println("The available balance is::" + amount);
					break;
				case 2:
					System.out.println("Enter amount to be deposited==>");
					amount = scan.nextInt();

					String status = deposite(amount);

					if (status == "success")
						System.out.println("Amount Deposited successfully");
					else
						System.out.println("Amount Deposited not successfully");
					break;
				case 3:
					scan = new Scanner(System.in);
					System.out.println("Enter amount to withdraw==>");
					amount = scan.nextInt();

					status = withdraw(amount);

					if (status == "success")
						System.out.println("Amount withdrawn successfully");
					else
						System.out.println("Amount withdraw not successful");
					break;
				case 4:
						System.exit(0);
				default:
					System.out.println("Wrong Choice!!!Try Again.....");
					

				}// switch ends
				System.out.println("Do u want to continue(yes/No)");
				option = scan.next();

			} // try ends
			catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {

					JDBCUtil.cleanUp(connection, pstmt, rs);
					//System.out.println("closing resources....");
				} catch (SQLException se) {
					se.printStackTrace();
				}

			}

		} while (option.equalsIgnoreCase("yes"));
	}

	private static String withdraw(int amount) throws SQLException {

		System.out.println("Enter account number==>");
		int accno = scan.nextInt();

		String q2 = "update account set balance=balance-" + amount + " where accno=?";

		if (connection != null)
			pstmt2 = connection.prepareStatement(q2);

		if (pstmt2 != null)
			pstmt2.setInt(1, accno);

		int rowCount = pstmt2.executeUpdate();
		String status;
		if (rowCount > 0)
			return status = "success";
		else
			return status = "failure";

	}

	private static String deposite(int amount) throws SQLException {

		System.out.println("Enter account number==>");
		int accno = scan.nextInt();

		String q1 = "update account set balance=balance+" + amount + " where accno=?";

		if (connection != null)
			pstmt1 = connection.prepareStatement(q1);

		if (pstmt1 != null)
			pstmt1.setInt(1, accno);

		int rowCount = pstmt1.executeUpdate();

		String status;
		if (rowCount > 0)
			return status = "success";
		// System.out.println("Amount Deposited successfully");
		else
			return status = "failure";
		// System.out.println("Account number not exits");

	}

	private static int checkBalance(int accno) throws SQLException {

		String q = "select balance from account where accno=?";

		if (connection != null)
			pstmt = connection.prepareStatement(q);

		pstmt.setInt(1, accno);

		if (pstmt != null)
			rs = pstmt.executeQuery();

		int balance = 0;
		if (rs != null) {
			while (rs.next()) {
				balance = rs.getInt(1);
			}
		}

		return balance;
	}

}
