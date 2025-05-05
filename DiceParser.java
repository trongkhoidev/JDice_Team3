import java.util.*;
<<<<<<< HEAD

public class DiceParser {
    /* this is a helper class to manage the input "stream" */
    private static class StringStream {
        StringBuffer buff;

        public StringStream(String s) {
            buff = new StringBuffer(s);
        }
=======
import java.util.logging.*;

/*
JDice: Java Dice Rolling Program
Copyright (C) 2006 Andrew D. Hilton  (adhilton@cis.upenn.edu)

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.
>>>>>>> main

        private void munchWhiteSpace() {
            int index = 0;
            char curr;
            while (index < buff.length()) {
                curr = buff.charAt(index);
                if (!Character.isWhitespace(curr))
                    break;
                index++;
            }
            buff = buff.delete(0, index);
        }

<<<<<<< HEAD
        public boolean isEmpty() {
            munchWhiteSpace();
            return buff.toString().equals("");
        }

        public Integer getInt() {
            return readInt();
        }

        public Integer readInt() {
            int index = 0;
            char curr;
            munchWhiteSpace();
            while (index < buff.length()) {
                curr = buff.charAt(index);
                if (!Character.isDigit(curr))
                    break;
                index++;
            }
            try {
                Integer ans;
                ans = Integer.parseInt(buff.substring(0, index));
                buff = buff.delete(0, index);
                return ans;
            } catch (Exception e) {
                return null;
            }
        }

        public Integer readSgnInt() {
            munchWhiteSpace();
            StringStream state = save();
            if (checkAndEat("+")) {
                Integer ans = readInt();
                if (ans != null)
                    return ans;
                restore(state);
                return null;
            }
            if (checkAndEat("-")) {
                Integer ans = readInt();
                if (ans != null)
                    return -ans;
                restore(state);
                return null;
            }
            return readInt();
        }

        public boolean checkAndEat(String s) {
            munchWhiteSpace();
            if (buff.indexOf(s) == 0) {
                buff = buff.delete(0, s.length());
                return true;
            }
            return false;
        }

        public StringStream save() {
            return new StringStream(buff.toString());
        }

        public void restore(StringStream ss) {
            this.buff = new StringBuffer(ss.buff);
        }

        public String toString() {
            return buff.toString();
        }
    }

    public static Vector<DieRoll> parseRoll(String s) {
        StringStream ss = new StringStream(s.toLowerCase());
        Vector<DieRoll> v = parseRollInner(ss, new Vector<DieRoll>());
        if (ss.isEmpty())
            return v;
        return null;
=======
You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

public class DiceParser {
    private static final Logger logger = Logger.getLogger(DiceParser.class.getName());

    /* this is a helper class to manage the input "stream"*/
    private static class StringStream {
        StringBuffer buff;

        public StringStream(String s) {
            buff = new StringBuffer(s);
        }

        private void munchWhiteSpace() {
            int index = 0;
            char curr;
            while (index < buff.length()) {
                curr = buff.charAt(index);
                if (!Character.isWhitespace(curr))
                    break;
                index++;
            }
            buff = buff.delete(0, index);
        }

        public boolean isEmpty() {
            munchWhiteSpace();
            return buff.toString().equals("");
        }

        public Integer getInt() {
            return readInt();
        }

        public Integer readInt() {
            int index = 0;
            char curr;
            munchWhiteSpace();
            while (index < buff.length()) {
                curr = buff.charAt(index);
                if (!Character.isDigit(curr))
                    break;
                index++;
            }
            try {
                Integer ans;
                ans = Integer.parseInt(buff.substring(0, index));
                buff = buff.delete(0, index);
                return ans;
            } catch (Exception e) {
                return null;
            }
        }

        public Integer readSgnInt() {
            munchWhiteSpace();
            StringStream state = save();
            if (checkAndEat("+")) {
                Integer ans = readInt();
                if (ans != null)
                    return ans;
                restore(state);
                return null;
            }
            if (checkAndEat("-")) {
                Integer ans = readInt();
                if (ans != null)
                    return -ans;
                restore(state);
                return null;
            }
            return readInt();
        }

        public boolean checkAndEat(String s) {
            munchWhiteSpace();
            if (buff.indexOf(s) == 0) {
                buff = buff.delete(0, s.length());
                return true;
            }
            return false;
        }

        public StringStream save() {
            return new StringStream(buff.toString());
        }

        public void restore(StringStream ss) {
            this.buff = new StringBuffer(ss.buff);
        }

        public String toString() {
            return buff.toString();
        }
    }

    /**
     * Phân tích cú pháp chuỗi nhập vào thành một danh sách các đối tượng DieRoll.
     * 
     * @param s Chuỗi nhập vào cần phân tích cú pháp
     * @return Một vector chứa các đối tượng DieRoll hoặc null nếu không thể phân tích
     */
    public static Vector<DieRoll> parseRoll(String s) {
        logger.info("Bắt đầu phân tích cú pháp cho chuỗi: " + s); // Log đầu vào

        // Kiểm tra tính hợp lệ của chuỗi
        if (!isValidDiceInput(s)) {
            logger.warning("Chuỗi nhập vào không hợp lệ: " + s);
            return null;
        }

        StringStream ss = new StringStream(s.toLowerCase());
        Vector<DieRoll> v = parseRollInner(ss, new Vector<DieRoll>());
        if (ss.isEmpty()) {
            logger.info("Phân tích thành công với kết quả: " + v);
            return v;
        } else {
            logger.warning("Không thể phân tích chuỗi: " + s);
            return null;
        }
>>>>>>> main
    }

    private static Vector<DieRoll> parseRollInner(StringStream ss, Vector<DieRoll> v) {
        Vector<DieRoll> r = parseXDice(ss);
        if (r == null) {
            return null;
        }
        v.addAll(r);
        if (ss.checkAndEat(";")) {
            return parseRollInner(ss, v);
        }
        return v;
    }

    private static Vector<DieRoll> parseXDice(StringStream ss) {
        StringStream saved = ss.save();
        Integer x = ss.getInt();
<<<<<<< HEAD
        int num;
        if (x == null) {
            num = 1;
        } else {
            if (ss.checkAndEat("x")) {
                num = x;
            } else {
                num = 1;
                ss.restore(saved);
            }
        }
=======
        int num = (x == null) ? 1 : x;

        if (ss.checkAndEat("x")) {
            num = x;
        } else {
            num = 1;
            ss.restore(saved);
        }

>>>>>>> main
        DieRoll dr = parseDice(ss);
        if (dr == null) {
            return null;
        }
<<<<<<< HEAD
=======

>>>>>>> main
        Vector<DieRoll> ans = new Vector<DieRoll>();
        for (int i = 0; i < num; i++) {
            ans.add(dr);
        }
        return ans;
<<<<<<< HEAD
=======
    }

    /**
     * Phương thức này phân tích cú pháp cho một viên xúc xắc (bao gồm bonus và dtail).
     * 
     * @param ss Chuỗi đầu vào cần phân tích
     * @return Một đối tượng DieRoll chứa thông tin về số lượng xúc xắc, số mặt và bonus
     */
    private static DieRoll parseDice(StringStream ss) {
        return parseDTail(parseDiceInner(ss), ss);
    }

    private static DieRoll parseDiceInner(StringStream ss) {
        Integer diceCount = ss.getInt(); // Đổi tên biến từ 'num' thành 'diceCount'
        int sides = 0;
        int rolls = (diceCount == null) ? 1 : diceCount;

        if (ss.checkAndEat("d")) {
            sides = ss.getInt(); // Kiểm tra nếu có 'd' và lấy số mặt xúc xắc
        } else {
            return null;
        }

        // Đọc bonus (nếu có)
        int bonus = ss.readSgnInt() != null ? ss.readSgnInt() : 0;

        return new DieRoll(rolls, sides, bonus);
    }

    private static DieRoll parseDTail(DieRoll r1, StringStream ss) {
        if (r1 == null)
            return null;
        if (ss.checkAndEat("&")) {
            DieRoll d2 = parseDice(ss);
            return parseDTail(new DiceSum(r1, d2), ss);
        } else {
            return r1;
        }
    }

	private static void test(String s) {
		Vector<DieRoll> v = parseRoll(s);
		if (v == null) {
			logger.warning("Failure: " + s);  // Sử dụng logger.warning thay vì System.out.println cho thất bại
		} else {
			logger.info("Results for " + s + ":"); // Sử dụng logger.info thay vì System.out.println cho kết quả thành công
			for (DieRoll dr : v) {
				logger.info(dr.toString() + ": " + dr.makeRoll()); // Log kết quả mỗi lần roll xúc xắc
			}
		}
	}
	

    public static void main(String[] args) {
        test("d6");
        test("2d6");
        test("d6+5");
        test("4X3d8-5");
        test("12d10+5 & 4d6+2");
        test("d6 ; 2d4+3");
        test("4d6+3 ; 8d12 -15 ; 9d10 & 3d6 & 4d12 +17");
        test("4d6 + xyzzy");
        test("hi");
        test("4d4d4");
>>>>>>> main
    }

    private static DieRoll parseDice(StringStream ss) {
        return parseDTail(parseDiceInner(ss), ss);
    }

    private static DieRoll parseDiceInner(StringStream ss) {
        Integer num = ss.getInt();
        int dsides;
        int ndice;
        if (num == null) {
            ndice = 1;
        } else {
            ndice = num;
        }
        if (ss.checkAndEat("d")) {
            num = ss.getInt();
            if (num == null)
                return null;
            dsides = num;
        } else {
            return null;
        }
        num = ss.readSgnInt();
        int bonus;
        if (num == null)
            bonus = 0;
        else
            bonus = num;
        return new DieRoll(ndice, dsides, bonus);
    }

    private static DieRoll parseDTail(DieRoll r1, StringStream ss) {
        if (r1 == null)
            return null;
        if (ss.checkAndEat("&")) {
            DieRoll d2 = parseDice(ss);
            return parseDTail(new DiceSum(r1, d2), ss);
        } else {
            return r1;
        }
    }

    private static void test(String s) {
        Vector<DieRoll> v = parseRoll(s);
        int i;
        if (v == null)
            System.out.println("Failure: " + s);
        else {
            System.out.println("Results for " + s + ":");
            for (i = 0; i < v.size(); i++) {
                DieRoll dr = v.get(i);
                System.out.print(v.get(i));
                System.out.print(": ");
                System.out.println(dr.makeRoll());
            }
        }
    }

    public static void main(String[] args) {
        test("d6");
        test("2d6");
        test("d6+5");
        test("4X3d8-5");
        test("12d10+5 & 4d6+2");
        test("d6 ; 2d4+3");
        test("4d6+3 ; 8d12 -15 ; 9d10 & 3d6 & 4d12 +17");
        test("4d6 + xyzzy");
        test("hi");
        test("4d4d4");
    }
}
arrow