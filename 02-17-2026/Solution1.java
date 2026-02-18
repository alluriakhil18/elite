// Write a program that takes an IP address and subnet mask (in CIDR notation, 
// e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

// Input Format:
// ---------------
// A String, IPAddress
// An integer, CIDR

// Output Format:
// ---------------
// Space separated IP addresses, network IP and broadcast IP.


// Sample Input-1:
// -----------------
// 192.168.1.10
// 24

// Sample Output-1:
// ------------------
// 192.168.1.0 192.168.1.255


// Sample Input-2:
// -----------------
// 192.0.2.1
// 24

// Sample Output-2:
// ------------------
// 192.0.2.0 192.0.2.255

import java.util.*;
class Solution1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String ip=sc.nextLine();
        int n=sc.nextInt();
        int range = (int)Math.pow(2,32-n);
        String[] inp=ip.split("\\.");
        int len=inp.length;
        String last = inp[len-1];
        String first = "";
        for(int i=0;i<len-1;i++){
            first+=inp[i]+".";
        }
        int num=Integer.parseInt(last);
        if(range==256){
            System.out.print(first+"0"+" "+first+"255");
            return;
        }
        int tt=0;
        while(range<=256){
            if(num<range) {
                int a=range-1;
                System.out.print(first+tt+" "+first+a);
                return;
            }
            tt=range;
            range+=range;
        }
    }
}