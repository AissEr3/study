package chapter3;

import java.util.HashMap;
import java.util.Scanner;

public class Test_3_1_1 {
    public static void main(String[] args) throws Exception{
        HashMap<String,Double> map = new HashMap<>();
        String[] str = {"A+","A","A-","B+","B","B-","C+","C","C-","D","F"};
        Double[] doubles = {4.33,4.00,3.76,3.33,3.00,2.67,2.33,2.00,1.67,1.00,0.00};
        if(str.length == doubles.length){
            int n = str.length;
            for(int i = 0; i < n; i++){
                map.put(str[i],doubles[i]);
            }
        }
        Scanner scan = new Scanner(System.in);
        String test = scan.nextLine();
        String[] input = test.split(" ");
        int count = 0;
        for(String s : input) {
            Double score = map.get(s);
            if(score == null){
                 throw new StackOverflowError("输入的值不存在");
            }
            count += map.get(s);
        }
        System.out.println(count);
    }
}
